package ru.gozerov.core.recycler_view

interface ActionListener<T> {
    fun onClick(args: T)
}