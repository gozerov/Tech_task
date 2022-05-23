package ru.gozerov.tech_task.di.modules

import dagger.Module

@Module(includes = [RemoteModule::class, CacheModule::class, AbstractBindsModule::class])
class AppModule