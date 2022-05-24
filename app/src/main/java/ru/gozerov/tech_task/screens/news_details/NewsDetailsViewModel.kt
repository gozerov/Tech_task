package ru.gozerov.tech_task.screens.news_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.gozerov.domain.news.usecases.GetNewsById
import javax.inject.Inject

class NewsDetailsViewModel(
    private val getNewsById: GetNewsById
) : ViewModel() {

    private val _viewState: MutableStateFlow<NewsDetailsState> = MutableStateFlow(NewsDetailsState.LoadingState)
    val viewState: StateFlow<NewsDetailsState> = _viewState.asStateFlow()

    fun getNewsById(id: Int) = viewModelScope.launch {
        getNewsById.execute(
            args = id,
            onSuccess = { news ->
                _viewState.tryEmit(NewsDetailsState.SuccessState(news))
            },
            onError = { e ->
                _viewState.tryEmit(NewsDetailsState.ErrorState(e))
            }
        )
    }

    fun tryAgain(id: Int) {
        _viewState.tryEmit(NewsDetailsState.LoadingState)
        getNewsById(id)
    }

    class Factory @Inject constructor(
        private val getNewsById: GetNewsById
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsDetailsViewModel(getNewsById) as T
        }
    }

}