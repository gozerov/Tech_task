package ru.gozerov.presentation.screens.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.gozerov.core.UseCase
import ru.gozerov.domain.news.models.SimpleNews
import ru.gozerov.domain.news.usecases.FetchSimpleNews
import ru.gozerov.presentation.screens.news_list.NewsListState.*
import javax.inject.Inject

class NewsListViewModel (
    private val fetchSimpleNews: UseCase<Unit, Flow<List<SimpleNews>>>
) : ViewModel() {

    private val _viewState: MutableStateFlow<NewsListState> = MutableStateFlow(LoadingState)
    val viewState: StateFlow<NewsListState> = _viewState.asStateFlow()

    var scroll: Int? = null

    init {
        fetchNews()
    }

    fun tryAgain() {
        _viewState.tryEmit(LoadingState)
        fetchNews()
    }

    private fun fetchNews() = viewModelScope.launch {
        fetchSimpleNews.execute(
            args = Unit,
            onSuccess = { news ->
                _viewState.tryEmit(SuccessState(news))
            },
            onError = { e ->
                _viewState.tryEmit(ErrorState(e))
            }
        )
    }

    class Factory @Inject constructor(
        private val fetchSimpleNews: FetchSimpleNews
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsListViewModel(fetchSimpleNews) as T
        }
    }

}