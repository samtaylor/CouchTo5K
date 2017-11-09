package samtaylor.couchto5k.data

import android.content.Context
import android.util.Log
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

        Log.v("xxx", data.toString())
    }


//    get() {
//
//        return Array(1) {
//
//            Week(Array(1) {
//
//                Run(Array(3) {
//
//                    when (it) {
//
//                        0 -> Step("walk", 30)
//                        1 -> Step("run", 20)
//                        else -> Step("walk", 10)
//                    }
//                })
//            })
//        }
//    }
}