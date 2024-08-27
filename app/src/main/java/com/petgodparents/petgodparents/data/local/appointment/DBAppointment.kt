package com.petgodparents.petgodparents.data.local.appointment

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.petgodparents.petgodparents.domain.entity.Appointment

@Entity(tableName = "appointment")
data class DBAppointment (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val typeId: String,
    val typeIdPatient: String,
    val description: String,
    val pet: String,
    val cost: Double,
    val status: String
) {

    fun toAppointment(): Appointment {
        return Appointment(
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

