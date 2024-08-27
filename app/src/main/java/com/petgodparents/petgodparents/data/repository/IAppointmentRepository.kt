package com.petgodparents.petgodparents.data.repository

import com.petgodparents.petgodparents.data.local.appointment.AppDatabase
import com.petgodparents.petgodparents.domain.entity.Appointment
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

interface IAppointmentRepository {
    suspend fun save(appointment: Appointment): Boolean

    suspend fun getAllAppointments(): List<Appointment>

    suspend fun getAppointmentByPet(petId: String): Appointment?
}

@ActivityRetainedScoped
class AppointmentRepository @Inject constructor(
    private val database: AppDatabase
): IAppointmentRepository {
    override suspend fun save(appointment: Appointment): Boolean {
        try {
            database.appointmentDao().save(appointment.toDBAppointment())
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    override suspend fun getAllAppointments(): List<Appointment> {
        return database.appointmentDao().getAll().map { it.toAppointment() } }

    override suspend fun getAppointmentByPet(petId: String): Appointment? {
        return database.appointmentDao().getAll().firstOrNull {
            it.typeIdPatient == petId
        }?.toAppointment()
    }
}