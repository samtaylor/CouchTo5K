package samtaylor.couchto5k.ui

import android.content.Intent
import android.os.Bundle
import android.support.wear.widget.WearableLinearLayoutManager
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_list.*
import samtaylor.couchto5k.DataAdapter
import samtaylor.couchto5k.R
import samtaylor.couchto5k.data.DataProvider

class SelectWeekActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val data = Array(DataProvider(this).data.size) { getString(R.string.week_format, it + 1) }

        recyclerView.layoutManager = WearableLinearLayoutManager(this)
        recyclerView.isEdgeItemsCenteringEnabled = resources.configuration.isScreenRound
        recyclerView.adapter = DataAdapter(data) {

            val intent = Intent(this, SelectRunActivity::class.java)
            intent.putExtra(SelectRunActivity.EXTRA_WEEK_NUMBER, it)
            startActivity(intent)
        }
    }
}
