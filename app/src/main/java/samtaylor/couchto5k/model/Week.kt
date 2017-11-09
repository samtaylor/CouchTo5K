package samtaylor.couchto5k.model

import java.util.*

data class Week(val runs: Array<Run>) {

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Week

        if (!Arrays.equals(runs, other.runs)) return false

        return true
    }

    override fun hashCode(): Int {

        return Arrays.hashCode(runs)
    }
}