package samtaylor.couchto5k.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.support.wearable.activity.WearableActivity
import android.widget.TextView
import com.github.lzyzsd.circleprogress.DonutProgress
import kotlinx.android.synthetic.main.activity_run.*
import samtaylor.couchto5k.R
import samtaylor.couchto5k.data.DataProvider
import samtaylor.couchto5k.model.Step
import samtaylor.couchto5k.setTime

class RunActivity : WearableActivity() {

    private var timer: Timer? = null

    private var weekNumber = -1
    private var runNumber = -1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run)
        setAmbientEnabled()

        finishButton.setOnClickListener {

            timer?.stop()

            val intent = Intent(this, ContinueActivity::class.java)
            intent.putExtra(ContinueActivity.EXTRA_CURRENT_STEP, timer?.currentStep)
            intent.putExtra(ContinueActivity.EXTRA_CURRENT_STEP_TIME, timer?.currentStepTime)
            intent.putExtra(ContinueActivity.EXTRA_TOTAL_TIME, timer?.totalTime)
            intent.putExtra(EXTRA_WEEK_NUMBER, weekNumber)
            intent.putExtra(EXTRA_RUN_NUMBER, runNumber)
            startActivity(intent)

            finish()
        }
    }

    override fun onResume() {

        super.onResume()

        val buzz: () -> Unit = {

            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val vibrationPattern = longArrayOf(0, 500, 50, 300)
            vibrator.vibrate(vibrationPattern, -1)
        }

        weekNumber = intent.extras[EXTRA_WEEK_NUMBER] as Int
        runNumber = intent.extras[EXTRA_RUN_NUMBER] as Int

        val week = DataProvider(this).data[weekNumber]
        var run = week.runs[runNumber]
        if (run.copyOf != null) {

            run = week.runs[run.copyOf as Int]
        }

        val currentStep = intent.getIntExtra(ContinueActivity.EXTRA_CURRENT_STEP, 0)
        val currentStepTime = intent.getIntExtra(ContinueActivity.EXTRA_CURRENT_STEP_TIME, 0)
        val totalTimeValue = intent.getIntExtra(ContinueActivity.EXTRA_TOTAL_TIME, 0)

        timer = Timer(run.steps!!, progress, activity, intervalTime, totalTime, getString(R.string.activity_walk), getString(R.string.activity_run), buzz, currentStep, currentStepTime, totalTimeValue) {

            val intent = Intent(this, FinishedActivity::class.java)
            intent.putExtra(FinishedActivity.EXTRA_WEEK_NUMBER, weekNumber)
            intent.putExtra(FinishedActivity.EXTRA_RUN_NUMBER, runNumber)
            startActivity(intent)

            finish()
        }
        timer?.start()
    }

    override fun onPause() {

        super.onPause()
        timer?.stop()
    }

    companion object {

        val EXTRA_WEEK_NUMBER = "extra.weeknumber"
        val EXTRA_RUN_NUMBER = "extra.runnumber"
    }

    private class Timer(private val steps: Array<Step>,
                        private val progress: DonutProgress,
                        private val activityLabel: TextView,
                        private val currentStepTimeLabel : TextView,
                        private val totalTimeLabel: TextView,
                        private val walk: String,
                        private val run: String,
                        private val buzz: () -> Unit,
                        var currentStep: Int,
                        var currentStepTime: Int,
                        var totalTime: Int,
                        private val onFinish: () -> Unit) {

        private val handler = Handler()

        init {

            if (totalTime == 0) {

                steps.forEach {

                    totalTime += it.duration
                }
            }
        }

        private val runnable: Runnable = object: Runnable {

            override fun run() {

                totalTime --
                if (currentStepTime - 1 == 0) {

                    currentStep++
                    if (currentStep >= steps.size) {

                        buzz()

                        handler.removeCallbacks(this)
                        onFinish()
                    } else {

                        currentStepTime = steps[currentStep].duration

                        buzz()

                        progress.max = currentStepTime
                        progress.progress = currentStepTime

                        handler.postDelayed(this, 1000)
                        formatLabels()
                    }
                }
                else {

                    currentStepTime--

                    progress.progress = currentStepTime

                    handler.postDelayed(this, 1000)
                    formatLabels()
                }
            }
        }

        fun start() {

            if (currentStepTime == 0) {

                currentStepTime = steps[currentStep].duration
            }

            progress.max = steps[currentStep].duration
            progress.progress = currentStepTime

            formatLabels()

            handler.postDelayed(runnable, 1000)
        }

        fun stop() {

            handler.removeCallbacks(runnable)
        }

        private fun formatLabels() {

            currentStepTimeLabel.setTime(currentStepTime / 60, currentStepTime % 60)
            totalTimeLabel.setTime(totalTime / 60, totalTime % 60)

            if (steps[currentStep].type == "walk") {

                activityLabel.text = walk
            } else {

                activityLabel.text = run
            }
        }
    }
}