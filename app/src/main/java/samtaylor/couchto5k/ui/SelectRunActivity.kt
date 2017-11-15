package samtaylor.couchto5k.ui

import android.content.Intent
import android.os.Bundle
import android.support.wear.widget.WearableLinearLayoutManager
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_list.*
import samtaylor.couchto5k.CustomScrollingLayoutCallback
import samtaylor.couchto5k.ListItem
import samtaylor.couchto5k.ListItemAdapter
import samtaylor.couchto5k.R
import samtaylor.couchto5k.data.DataProvider
import samtaylor.couchto5k.data.Persistence

class SelectRunActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val weekNumber = intent.extras[EXTRA_WEEK_NUMBER] as Int
        val persistence = Persistence(this)
        val data = Array(DataProvider(this).data[weekNumber].runs.size) { ListItem(getString(R.string.run_format, it + 1), persistence.runCompleted(weekNumber, it)) }

        recyclerView.layoutManager = WearableLinearLayoutManager(this, CustomScrollingLayoutCallback())
        recyclerView.isEdgeItemsCenteringEnabled = resources.configuration.isScreenRound
        recyclerView.adapter = ListItemAdapter(data) {

            val intent = Intent(this, RunDetailsActivity::class.java)
            intent.putExtra(RunDetailsActivity.EXTRA_WEEK_NUMBER, weekNumber)
            intent.putExtra(RunDetailsActivity.EXTRA_RUN_NUMBER, it)
            startActivity(intent)
        }
    }

    companion object {

        val EXTRA_WEEK_NUMBER = "extra.weeknumber"
    }
}
