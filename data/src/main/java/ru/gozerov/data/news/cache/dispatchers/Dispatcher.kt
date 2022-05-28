package ru.gozerov.data.news.cache.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

abstract class Dispatcher(
    val value: CoroutineDispatcher
)