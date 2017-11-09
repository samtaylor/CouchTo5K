package samtaylor.couchto5k.model

import java.util.*

data class Run(val steps: Array<Step>?, val copyOf: Int?) {

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Run

        if (!Arrays.equals(steps, other.steps)) return false

        return true
    }

    override fun hashCode(): Int {

        return Arrays.hashCode(steps)
    }
}