package com.example.topacademy_android.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topacademy_android.domain.usecase.LoadItemsUseCase
import com.example.topacademy_android.domain.usecase.SaveItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val loadItemsUseCase: LoadItemsUseCase,
    private val saveItemsUseCase: SaveItemsUseCase
) : ViewModel() {
    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items: StateFlow<List<String>> = _items

    init {
        loadItems()
    }

    fun addItem(item: String) {
        viewModelScope.launch {
            val newList = _items.value + item
            saveItemsUseCase(newList)
            loadItems()
        }
    }

    fun deleteItem(position: Int) {
        viewModelScope.launch {
            val newList = _items.value.toMutableList().apply { removeAt(position) }
            saveItemsUseCase(newList)
            loadItems()
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            _items.value = loadItemsUseCase()
        }
    }
}