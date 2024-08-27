package com.petgodparents.petgodparents.domain.entity

import com.petgodparents.petgodparents.data.local.appointment.DBAppointment

data class Appointment(
    val date: String,
    val description: String,
    val typeId: String,
    val typeIdPatient: String,
    val pet: String,
    val cost: Double,
    val status: String
){
    fun getTypePatientDescription(): String {
        return TypeAnimal.entries.find {
            it.id == typeIdPatient
        }?.description ?: TypeAnimal.DOG.description
    }

    fun getTypeDescription(): String {
        return TypeAnimal.entries.find {
            it.id == typeId
        }?.description ?: TypeAppointment.GENERAL.description
    }

    fun toDBAppointment(): DBAppointment {
        return DBAppointment(
            date = date,
            typeId = typeId,
            typeIdPatient = typeIdPatient,
            cost = cost,
            status = status,
            description = description,
            pet = pet
        )
    }

}