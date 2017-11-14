package samtaylor.couchto5k.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import samtaylor.couchto5k.model.Week

class DataProvider(context: Context) {

    val data: Array<Week>
    init {
        val inputStream = context.assets.open("run_data.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer)

        val type = object : TypeToken<Array<Week>>() {}.type
        data = Gson().fromJson<Array<Week>>(json, type)
    }
}