package ru.gozerov.tech_task.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.gozerov.tech_task.di.modules.AppModule
import ru.gozerov.tech_task.screens.news_details.NewsDetailsFragment
import ru.gozerov.tech_task.screens.news_list.NewsListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(newsListFragment: NewsListFragment)
    fun inject(newsListFragment: NewsDetailsFragment)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder

    }

}