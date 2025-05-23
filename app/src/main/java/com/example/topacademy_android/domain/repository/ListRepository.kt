package com.example.topacademy_android.domain.repository

interface ListRepository {
    fun saveItems(items: List<String>)
    fun loadItems(): List<String>
}