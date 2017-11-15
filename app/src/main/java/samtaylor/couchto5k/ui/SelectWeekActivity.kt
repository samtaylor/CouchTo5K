package samtaylor.couchto5k.ui

import android.content.Intent
import android.os.Bundle
import android.support.wear.widget.WearableLinearLayoutManager
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_list.*
import samtaylor.couchto5k.model.ListItem
import samtaylor.couchto5k.model.ListItemAdapter
import samtaylor.couchto5k.R
import samtaylor.couchto5k.data.DataProvider
import samtaylor.couchto5k.data.Persistence

class SelectWeekActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }

    override fun onResume() {

        super.onResume()

        val persistence = Persistence(this)
        val data = Array(DataProvider(this).data.size) { ListItem(getString(R.string.week_format, it + 1), persistence.weekCompleted(it)) }

        recyclerView.layoutManager = WearableLinearLayoutManager(this, CustomScrollingLayoutCallback())
        recyclerView.isEdgeItemsCenteringEnabled = resources.configuration.isScreenRound
        recyclerView.adapter = ListItemAdapter(data) {

            val intent = Intent(this, SelectRunActivity::class.java)
            intent.putExtra(SelectRunActivity.EXTRA_WEEK_NUMBER, it)
            startActivity(intent)
        }
    }
}
