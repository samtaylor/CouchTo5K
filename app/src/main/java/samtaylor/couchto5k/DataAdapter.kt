package samtaylor.couchto5k

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class DataAdapter(private val data: Array<String>, private val onClick:(Int) -> Unit) : RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataViewHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)

        return DataViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: DataViewHolder?, position: Int) {

        holder?.bind(data[position])
    }

    override fun getItemCount() = data.size
}