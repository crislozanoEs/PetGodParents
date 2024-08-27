package com.petgodparents.petgodparents.domain.useCase

import com.petgodparents.petgodparents.data.repository.IAppointmentRepository
import com.petgodparents.petgodparents.domain.entity.Appointment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAppointmentsUC @Inject constructor(
    private val appointmentRepository: IAppointmentRepository
) {

    suspend fun invoke(): List<Appointment> = withContext(Dispatchers.IO){
       appointmentRepository.getAllAppointments()
    }
}