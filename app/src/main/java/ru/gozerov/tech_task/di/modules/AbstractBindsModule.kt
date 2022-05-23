package ru.gozerov.tech_task.di.modules

import dagger.Binds
import dagger.Module
import ru.gozerov.data.news.repository.NewsRepositoryImpl
import ru.gozerov.domain.news.repository.NewsRepository
import javax.inject.Singleton

@Module
interface AbstractBindsModule {

    @Singleton
    @Suppress("unused")
    @Binds
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}