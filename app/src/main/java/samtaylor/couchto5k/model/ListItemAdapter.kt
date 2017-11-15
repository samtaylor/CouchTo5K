package samtaylor.couchto5k.model

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import samtaylor.couchto5k.R

class ListItemAdapter(private val data: Array<ListItem>, private val onClick:(Int) -> Unit) : RecyclerView.Adapter<ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemViewHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)

        return ListItemViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder?, position: Int) {

        holder?.bind(data[position])
    }

    override fun getItemCount() = data.size
}