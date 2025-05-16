package com.example.topacademy_android

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.databinding.ActivityListBinding
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private val items = mutableListOf<String>()
    private lateinit var adapter: ItemAdapter
    private val prefsKey = "SAVED_ITEMS"
    private val stateKey = "CURRENT_ITEMS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items.addAll(
            savedInstanceState?.getStringArrayList(stateKey) ?: loadSavedItems()
        )

        adapter = ItemAdapter(items) { position ->
            items.removeAt(position)
            adapter.notifyItemRemoved(position)
            saveItemsToPrefs()
        }

        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val newItem = binding.etNewItem.text.toString().trim()
            if (newItem.isNotEmpty()) {
                items.add(newItem)
                adapter.notifyItemInserted(items.size - 1)
                binding.etNewItem.text?.clear()
                saveItemsToPrefs()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(stateKey, ArrayList(items))
    }

    private fun loadSavedItems(): List<String> {
        val prefs = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        val json = prefs.getString(prefsKey, null) ?: return emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }

    private fun saveItemsToPrefs() {
        val prefs = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        val json = Gson().toJson(items)
        prefs.edit { putString(prefsKey, json) }
    }
}