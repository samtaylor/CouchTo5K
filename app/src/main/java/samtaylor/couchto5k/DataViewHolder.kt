package samtaylor.couchto5k

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.list_item.view.*

class DataViewHolder(view : View, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {

    private val label = view.label

    fun bind(labelString: String) {

        label.text = labelString

        itemView.setOnClickListener {

            onClick(adapterPosition)
        }
    }
}