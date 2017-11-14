package samtaylor.couchto5k.ui

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_continue.*
import samtaylor.couchto5k.R

class ContinueActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continue)

        val weekNumber = intent.extras[RunActivity.EXTRA_WEEK_NUMBER] as Int
        val runNumber = intent.extras[RunActivity.EXTRA_RUN_NUMBER] as Int

        finishButton.setOnClickListener {

            val intent = Intent(this, FinishedActivity::class.java)
            intent.putExtra(FinishedActivity.EXTRA_WEEK_NUMBER, weekNumber)
            intent.putExtra(FinishedActivity.EXTRA_RUN_NUMBER, runNumber)
            intent.putExtra(FinishedActivity.EXTRA_ABORTED, true)
            startActivity(intent)

            finish()
        }

        continueButton.setOnClickListener {

            val currentStep = intent.extras[EXTRA_CURRENT_STEP] as Int
            val currentStepTime = intent.extras[EXTRA_CURRENT_STEP_TIME] as Int
            val totalTime = intent.extras[EXTRA_TOTAL_TIME] as Int

            val intent = Intent(this, RunActivity::class.java)
            intent.putExtra(EXTRA_CURRENT_STEP, currentStep)
            intent.putExtra(EXTRA_CURRENT_STEP_TIME, currentStepTime)
            intent.putExtra(EXTRA_TOTAL_TIME, totalTime)
            intent.putExtra(RunActivity.EXTRA_WEEK_NUMBER, weekNumber)
            intent.putExtra(RunActivity.EXTRA_RUN_NUMBER, runNumber)
            startActivity(intent)

            finish()
        }
    }

    companion object {

        val EXTRA_CURRENT_STEP = "extra.currentstep"
        val EXTRA_CURRENT_STEP_TIME = "extra.currentsteptime"
        val EXTRA_TOTAL_TIME = "extra.totalTime"
    }
}