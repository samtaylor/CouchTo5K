package samtaylor.couchto5k

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.list_item.view.*

class ListItemViewHolder(view : View, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {

    private val label = view.label
    private val completeIcon = view.completeIcon

    fun bind(item: ListItem) {

        label.text = item.label
        if (item.complete) {

            completeIcon.setImageResource(R.drawable.ic_check_circle)
        } else {

            completeIcon.setImageResource(R.drawable.ic_cancel_circle)
        }

        itemView.setOnClickListener {

            onClick(adapterPosition)
        }
    }
}