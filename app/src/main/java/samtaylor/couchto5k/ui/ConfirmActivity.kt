package samtaylor.couchto5k.ui

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_confirm.*
import samtaylor.couchto5k.R

class ConfirmActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        setResult(RESULT_NEGATIVE)

        if (intent.hasExtra(EXTRA_TITLE) && intent.hasExtra(EXTRA_DESCRIPTION)) {

            confirmTitle.text = intent.extras[EXTRA_TITLE] as String
            confirmDescription.text = intent.extras[EXTRA_DESCRIPTION] as String

            positiveAction.setOnClickListener {

                setResult(RESULT_POSITIVE)
                finish()
            }

            negativeAction.setOnClickListener {

                finish()
            }
        } else {

            finish()
        }
    }

    companion object {

        val EXTRA_TITLE = "extra.title"
        val EXTRA_DESCRIPTION = "extra.description"

        val RESULT_POSITIVE = 1001
        val RESULT_NEGATIVE = 1002
    }
}