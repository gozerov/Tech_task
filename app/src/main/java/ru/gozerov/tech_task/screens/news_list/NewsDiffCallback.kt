package ru.gozerov.tech_task.screens.news_list

import androidx.recyclerview.widget.DiffUtil
import ru.gozerov.domain.news.models.SimpleNews

class NewsDiffCallback(
    private val oldList: List<SimpleNews>,
    private val newList: List<SimpleNews>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}