package ru.gozerov.tech_task.screens.news_list

import kotlinx.coroutines.flow.Flow
import ru.gozerov.core.screens.ViewState
import ru.gozerov.domain.news.models.SimpleNews

sealed class NewsListState: ViewState {

    object LoadingState: NewsListState()

    data class SuccessState(
        val newsList: Flow<List<SimpleNews>>
    ): NewsListState()

    data class ErrorState(
        val errorMessage: String
    ): NewsListState()

}
