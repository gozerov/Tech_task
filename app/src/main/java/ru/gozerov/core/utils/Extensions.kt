package ru.gozerov.core.utils

import android.content.Context
import ru.gozerov.tech_task.app.NewsApp
import ru.gozerov.tech_task.di.component.AppComponent

val Context.appComponent: AppComponent
    get() = when(this) {
        is NewsApp -> appComponent
        else -> this.applicationContext.appComponent
    }