package com.example.topacademy_android.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.topacademy_android.domain.repository.ListRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val prefs: SharedPreferences
) : ListRepository {
    private val type = object : TypeToken<List<String>>() {}.type

    override fun saveItems(items: List<String>) {
        prefs.edit { putString("items_key", Gson().toJson(items)) }
    }

    override fun loadItems(): List<String> {
        return Gson().fromJson(prefs.getString("items_key", ""), type) ?: emptyList()
    }
}