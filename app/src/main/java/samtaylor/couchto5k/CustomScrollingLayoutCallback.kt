package samtaylor.couchto5k

import android.support.v7.widget.RecyclerView
import android.support.wear.widget.WearableLinearLayoutManager
import android.view.View

class CustomScrollingLayoutCallback : WearableLinearLayoutManager.LayoutCallback() {

    private var mProgressToCenter: Float = 0.toFloat()

    override fun onLayoutFinished(child: View, parent: RecyclerView) {

        // Figure out % progress from top to bottom
        val centerOffset = child.height / 2.0f / parent.height
        val yRelativeToCenterOffset = child.y / parent.height + centerOffset

        // Normalize for center
        mProgressToCenter = Math.abs(0.5f - yRelativeToCenterOffset)
        // Adjust to the maximum scale
        mProgressToCenter = Math.min(mProgressToCenter, MAX_ICON_PROGRESS)

        child.scaleX = 1 - mProgressToCenter
        child.scaleY = 1 - mProgressToCenter
    }

    companion object {
        /** How much should we scale the icon at most.  */
        private val MAX_ICON_PROGRESS = 0.65f
    }
}
