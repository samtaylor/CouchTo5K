package samtaylor.couchto5k.ui

import android.content.Intent
import android.os.Bundle
import android.support.wear.widget.WearableLinearLayoutManager
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_list.*
import samtaylor.couchto5k.R
import samtaylor.couchto5k.data.DataProvider
import samtaylor.couchto5k.data.Persistence
import samtaylor.couchto5k.model.ListItem
import samtaylor.couchto5k.model.ListItemAdapter

class SelectRunActivity : WearableActivity() {

    private val REQUEST_CODE_RESET = 3001

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        navigationDrawer.controller.peekDrawer()
        navigationDrawer.setAdapter(NavigationDrawerAdapter(getString(R.string.action_reset_week), getDrawable(R.drawable.ic_delete)))
        navigationDrawer.addOnItemSelectedListener {

            val intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra(ConfirmActivity.EXTRA_TITLE, "Are You Sure?")
            intent.putExtra(ConfirmActivity.EXTRA_DESCRIPTION, "This will delete all of your activity for this week.")
            startActivityForResult(intent, REQUEST_CODE_RESET)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_CODE_RESET && resultCode == ConfirmActivity.RESULT_POSITIVE) {

            val weekNumber = intent.extras[EXTRA_WEEK_NUMBER] as Int
            Persistence(this).clearWeek(weekNumber)

            setAdapter()
        }
    }

    override fun onResume() {

        super.onResume()

        recyclerView.layoutManager = WearableLinearLayoutManager(this, CustomScrollingLayoutCallback())
        recyclerView.isEdgeItemsCenteringEnabled = resources.configuration.isScreenRound

        setAdapter()
    }

    private fun setAdapter() {

        val weekNumber = intent.extras[EXTRA_WEEK_NUMBER] as Int
        val persistence = Persistence(this)
        val data = Array(DataProvider(this).data[weekNumber].runs.size) { ListItem(getString(R.string.run_format, it + 1), persistence.runCompleted(weekNumber, it)) }
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
