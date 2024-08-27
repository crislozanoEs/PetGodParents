package com.petgodparents.petgodparents.domain.entity

enum class TypeAppointment(val id: String, val description: String) {
    GENERAL("G", "General"),
    URGENCY("U", "Urgency"),
    CONTROL("C", "Control")
}