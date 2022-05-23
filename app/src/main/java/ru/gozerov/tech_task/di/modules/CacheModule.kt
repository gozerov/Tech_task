package ru.gozerov.tech_task.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.gozerov.data.news.cache.NewsDatabase

@Module
class CacheModule {

    @Provides
    fun provideNewsDao(context: Context) =
        NewsDatabase.getInstance(context).newsDao

}