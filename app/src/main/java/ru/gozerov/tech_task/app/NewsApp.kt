package ru.gozerov.tech_task.app

import android.app.Application
import androidx.fragment.app.Fragment
import ru.gozerov.core.di.DiComponent
import ru.gozerov.tech_task.di.component.AppComponent
import ru.gozerov.tech_task.di.component.DaggerAppComponent
import ru.gozerov.presentation.screens.news_details.NewsDetailsFragment
import ru.gozerov.presentation.screens.news_list.NewsListFragment

class NewsApp: Application(), DiComponent {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .context(this.applicationContext)
            .build()
        super.onCreate()
    }

    override fun inject(fragment: Fragment) {
        when (fragment) {
            is NewsListFragment -> appComponent.inject(fragment)
            is NewsDetailsFragment -> appComponent.inject(fragment)
            else -> throw ClassNotFoundException("Incorrect fragment")
        }
    }

}