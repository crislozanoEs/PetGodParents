package com.petgodparents.petgodparents.data.local.appointment

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DBAppointment::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun appointmentDao(): AppointmentDAO
}