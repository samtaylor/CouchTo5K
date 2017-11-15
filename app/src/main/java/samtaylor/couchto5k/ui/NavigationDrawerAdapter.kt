package samtaylor.couchto5k.ui

import android.graphics.drawable.Drawable
import android.support.wear.widget.drawer.WearableNavigationDrawerView

class NavigationDrawerAdapter(private val text: String, private val icon: Drawable) : WearableNavigationDrawerView.WearableNavigationDrawerAdapter() {

    override fun getItemText(position: Int) = text

    override fun getItemDrawable(position: Int): Drawable = icon

    override fun getCount() = 1
}