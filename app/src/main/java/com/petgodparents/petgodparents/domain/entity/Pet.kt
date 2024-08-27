package com.petgodparents.petgodparents.domain.entity

data class Pet (
    val id: Long,
    val name: String,
    val typeId: String,
    val birthday: String,
    val gender: String
) {
    fun getTypeDescription(): String {
      return TypeAnimal.entries.find {
            it.id == typeId
        }?.description ?: TypeAnimal.DOG.description
    }
}