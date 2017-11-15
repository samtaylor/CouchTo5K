package samtaylor.couchto5k.data

import android.content.Context
import samtaylor.couchto5k.R

class Persistence(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    private val dataProvider = DataProvider(context)

    fun weekCompleted(week: Int) : Boolean {

        (0 .. dataProvider.data[week].runs.size).forEach {

            if (!runCompleted(week, it)) {

                return false
            }
        }

        return true
    }

    fun runCompleted(week: Int, run: Int) = sharedPreferences.getBoolean("week_${week}_run_$run", false)

    fun setRunCompleted(week: Int, run: Int, value: Boolean = true) {

        sharedPreferences.edit().putBoolean("week_${week}_run_$run", value).apply()
    }

    fun clearWeek(week: Int) {

        (0 .. dataProvider.data[week].runs.size).forEach {

            setRunCompleted(week, it, false)
        }
    }

    fun clearAll() {

        (0 .. dataProvider.data.size).forEach { week ->

            clearWeek(week)
        }
    }
}