package ru.gozerov.domain.news.usecases

import ru.gozerov.core.UseCase
import ru.gozerov.domain.news.models.NewsApi
import ru.gozerov.domain.news.repository.NewsRepository
import javax.inject.Inject

class GetNewsById @Inject constructor(
    private val newsRepository: NewsRepository
): UseCase<Int, NewsApi> {

    override suspend fun execute(
        args: Int,
        onSuccess: (NewsApi) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onSuccess(newsRepository.getNewsById(args))
        } catch (e: Exception) {
            onError(e.localizedMessage)
        }
    }

}