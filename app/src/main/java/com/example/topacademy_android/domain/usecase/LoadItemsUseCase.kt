package com.example.topacademy_android.domain.usecase

import com.example.topacademy_android.domain.repository.ListRepository

class LoadItemsUseCase (
    private val repository: ListRepository
) {
    operator fun invoke(): List<String> = repository.loadItems()
}