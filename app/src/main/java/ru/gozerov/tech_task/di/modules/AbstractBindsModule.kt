package ru.gozerov.tech_task.di.modules

import dagger.Binds
import dagger.Module
import ru.gozerov.data.news.cache.storage.NewsStorage
import ru.gozerov.data.news.cache.storage.NewsStorageImpl
import ru.gozerov.data.news.remote.NewsRemote
import ru.gozerov.data.news.remote.NewsRemoteImpl
import ru.gozerov.data.news.repository.NewsRepositoryImpl
import ru.gozerov.domain.news.repository.NewsRepository
import javax.inject.Singleton

@Module
interface AbstractBindsModule {

    @Singleton
    @Suppress("unused")
    @Binds
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Singleton
    @Suppress("unused")
    @Binds
    fun provideNewsStorage(newsStorageImpl: NewsStorageImpl): NewsStorage

    @Singleton
    @Suppress("unused")
    @Binds
    fun provideNewsRemote(newsRemoteImpl: NewsRemoteImpl): NewsRemote

}