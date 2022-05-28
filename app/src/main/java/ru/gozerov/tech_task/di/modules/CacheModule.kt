package ru.gozerov.tech_task.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.gozerov.data.news.cache.room.NewsDatabase
import ru.gozerov.data.news.cache.dispatchers.Dispatcher
import ru.gozerov.data.news.cache.dispatchers.DispatcherIO

@Module
class CacheModule {

    @Provides
    fun provideNewsDao(context: Context) =
        NewsDatabase.getInstance(context).newsDao

    @Provides
    fun provideDispatcherIO(): Dispatcher = DispatcherIO()

}