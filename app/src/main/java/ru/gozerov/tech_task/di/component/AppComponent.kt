package ru.gozerov.tech_task.di.component

import android.content.Context
import dagger.Component
import ru.gozerov.tech_task.di.modules.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun context(context: Context): Builder

    }

}