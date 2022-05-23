package ru.gozerov.data.news.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.gozerov.data.news.models.CacheNewsApi

@Database(entities = [CacheNewsApi::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao

    companion object {

        const val name: String = "NEWS_DB"

        private var database: NewsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): NewsDatabase {
            return database ?: Room.databaseBuilder(context, NewsDatabase::class.java, name).build()
        }
    }

}