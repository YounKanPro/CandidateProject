package com.example.candidateproject.base

interface RecyclerInterface<T> {
    fun onItemClick(data: T?) {}
    fun onErrorMessage(title: String, message: String) {}
}