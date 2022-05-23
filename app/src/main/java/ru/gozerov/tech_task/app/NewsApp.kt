package ru.gozerov.tech_task.app

import android.app.Application
import ru.gozerov.tech_task.di.component.AppComponent
import ru.gozerov.tech_task.di.component.DaggerAppComponent

class NewsApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .context(this.applicationContext)
            .build()
        super.onCreate()
    }

}