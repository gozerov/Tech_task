package ru.gozerov.data.news.cache.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class DispatcherIO(
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
): Dispatcher(dispatcher)
