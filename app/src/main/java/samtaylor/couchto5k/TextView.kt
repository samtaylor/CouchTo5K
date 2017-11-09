package samtaylor.couchto5k

import android.widget.TextView

fun TextView.setTime(minutes: Int, seconds: Int) {

    val minutesString = if (minutes < 10) {

        if (minutes == 0) {

            "00"
        } else {

            "0$minutes"
        }
    } else {

        "$minutes"
    }

    val secondsString = if (seconds < 10) {

        if (seconds == 0) {

            "00"
        } else {

            "0$seconds"
        }
    } else {

        "$seconds"
    }

    text = "$minutesString:$secondsString"
}