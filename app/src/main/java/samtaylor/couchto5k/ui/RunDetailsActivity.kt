package samtaylor.couchto5k.ui

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_run_details.*
import samtaylor.couchto5k.R

class RunDetailsActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_details)
    }

    override fun onResume() {

        super.onResume()

        val weekNumber = intent.extras[EXTRA_WEEK_NUMBER] as Int
        val runNumber = intent.extras[EXTRA_RUN_NUMBER] as Int

        runTitle.text = getString(R.string.run_title_format, weekNumber + 1, runNumber + 1)

        startButton.setOnClickListener {

            val intent = Intent(this, RunActivity::class.java)
            intent.putExtra(RunActivity.EXTRA_WEEK_NUMBER, weekNumber)
            intent.putExtra(RunActivity.EXTRA_RUN_NUMBER, runNumber)
            startActivity(intent)
        }
    }

    companion object {

        val EXTRA_WEEK_NUMBER = "extra.weeknumber"
        val EXTRA_RUN_NUMBER = "extra.runnumber"
    }
}