package com.example.topacademy_android.presentation.list

import androidx.lifecycle.ViewModel
import com.example.topacademy_android.domain.repository.ListRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: ListRepository
) : ViewModel() {
    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items: StateFlow<List<String>> = _items

    init {
        loadItems()
    }

    fun addItem(item: String) {
        viewModelScope.launch {
            repository.saveItems(_items.value + item)
            loadItems()
        }
    }

    fun deleteItem(position: Int) {
        viewModelScope.launch {
            val newList = _items.value.toMutableList().apply { removeAt(position) }
            repository.saveItems(newList)
            loadItems()
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            _items.value = repository.loadItems()
        }
    }
}