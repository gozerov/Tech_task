package ru.gozerov.tech_task.screens.news_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.gozerov.core.recycler_view.ActionListener
import ru.gozerov.core.recycler_view.BaseRecyclerViewAdapter
import ru.gozerov.core.recycler_view.BaseViewHolder
import ru.gozerov.domain.news.models.SimpleNews
import ru.gozerov.tech_task.databinding.ItemSimpleNewsBinding

class NewsListAdapter(
    private val actionListener: ActionListener<Int>
): BaseRecyclerViewAdapter<SimpleNews>(), View.OnClickListener {

    class ViewHolder(private val binding: ItemSimpleNewsBinding): BaseViewHolder<SimpleNews>(binding) {
        override fun bind(data: SimpleNews) {
            binding.txtTitle.text = data.title
            binding.txtContent.text = data.content
        }

    }

    override var data: List<SimpleNews> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SimpleNews> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSimpleNewsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onClick(view: View?) {
        val id = (view?.tag as SimpleNews).id
        actionListener.onClick(id)
    }

}