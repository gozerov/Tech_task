package ru.gozerov.domain.news.usecases

import kotlinx.coroutines.flow.Flow
import ru.gozerov.core.UseCase
import ru.gozerov.domain.news.models.SimpleNews
import ru.gozerov.domain.news.repository.NewsRepository
import javax.inject.Inject

class FetchSimpleNews @Inject constructor(
    private val newsRepository: NewsRepository
): UseCase<Unit, Flow<List<SimpleNews>>> {

    override suspend fun execute(
        args: Unit,
        onSuccess: (Flow<List<SimpleNews>>) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onSuccess(newsRepository.fetchSimpleNews())
        } catch (e: Exception) {
            onError(e.localizedMessage)
        }
    }

}