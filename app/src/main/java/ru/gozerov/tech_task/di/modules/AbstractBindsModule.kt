package ru.gozerov.tech_task.di.modules

import dagger.BindsInstance
import ru.gozerov.data.news.repository.NewsRepositoryImpl
import ru.gozerov.domain.news.repository.NewsRepository
import javax.inject.Singleton

interface AbstractBindsModule {

    @Singleton
    @Suppress("unused")
    @BindsInstance
    fun bindNewsRepoImplToNewsRepo(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}