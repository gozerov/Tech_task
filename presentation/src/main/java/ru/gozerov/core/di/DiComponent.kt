package ru.gozerov.core.di

import android.content.Context
import androidx.fragment.app.Fragment

interface DiComponent {
    fun inject(fragment: Fragment)
}

val Context.diComponent: DiComponent
    get() = this.applicationContext as DiComponent