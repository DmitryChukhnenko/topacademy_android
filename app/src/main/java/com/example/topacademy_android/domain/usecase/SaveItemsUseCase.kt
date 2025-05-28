package com.example.topacademy_android.domain.usecase

import com.example.topacademy_android.domain.repository.ListRepository

class SaveItemsUseCase (
    private val repository: ListRepository
) {
    operator fun invoke(items: List<String>) = repository.saveItems(items)
}