package com.petgodparents.petgodparents.presentation

sealed class Status {
    object Loading: Status()
    class Success<T>(val data: T): Status()
    class Error(val errorMessage: String): Status()
}