package ru.gozerov.presentation.screens.news_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import ru.gozerov.core.recycler_view.ActionListener
import ru.gozerov.core.recycler_view.BaseRecyclerViewAdapter
import ru.gozerov.core.recycler_view.BaseViewHolder
import ru.gozerov.domain.news.models.SimpleNews
import ru.gozerov.presentation.databinding.ItemSimpleNewsBinding

class NewsListAdapter(
    private val actionListener: ActionListener<Int>
): BaseRecyclerViewAdapter<SimpleNews>(), View.OnClickListener {

    private class ViewHolder(private val binding: ItemSimpleNewsBinding): BaseViewHolder<SimpleNews>(binding) {
        override fun bind(data: SimpleNews) {
            binding.txtTitle.text = data.title
            binding.txtContent.text = data.description
        }

    }

    override var data: List<SimpleNews> = emptyList()
        set(value) {
            val diffUtilCallback = NewsDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SimpleNews> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSimpleNewsBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ViewHolder(binding)
    }

    override fun onClick(view: View?) {
        val news = (view?.tag as SimpleNews)
        actionListener.onClick(news.id)
    }

}