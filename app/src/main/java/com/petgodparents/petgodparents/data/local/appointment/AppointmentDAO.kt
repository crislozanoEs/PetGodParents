package com.petgodparents.petgodparents.data.local.appointment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppointmentDAO {
    @Insert
    fun save(appointment: DBAppointment)

    @Query("SELECT * FROM appointment")
    fun getAll(): List<DBAppointment>

}