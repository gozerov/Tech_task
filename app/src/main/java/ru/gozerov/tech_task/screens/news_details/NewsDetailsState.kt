package ru.gozerov.tech_task.screens.news_details

import ru.gozerov.core.screens.ViewState
import ru.gozerov.domain.news.models.NewsApi

sealed class NewsDetailsState: ViewState {

    object LoadingState: NewsDetailsState()

    data class SuccessState(
        val news: NewsApi
    ): NewsDetailsState()

    data class ErrorState(
        val errorMessage: String
    ): NewsDetailsState()
}
