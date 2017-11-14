package samtaylor.couchto5k.ui

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_finished.*
import samtaylor.couchto5k.R

class FinishedActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finished)
    }

    override fun onResume() {

        super.onResume()

        val weekNumber = intent.extras[EXTRA_WEEK_NUMBER] as Int
        val runNumber = intent.extras[EXTRA_RUN_NUMBER] as Int
        val aborted = intent.extras[EXTRA_ABORTED] as Boolean

        runTitle.text = getString(R.string.run_title_format, weekNumber + 1, runNumber + 1)

        doneButton.setOnClickListener {

            finish()
        }
    }

    companion object {

        val EXTRA_WEEK_NUMBER = "extra.weeknumber"
        val EXTRA_RUN_NUMBER = "extra.runnumber"
        val EXTRA_ABORTED = "extra.aborted"
    }
}