package com.petgodparents.petgodparents.presentation.appoinment.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petgodparents.petgodparents.domain.entity.Appointment
import com.petgodparents.petgodparents.domain.entity.TypeAnimal
import com.petgodparents.petgodparents.domain.useCase.SaveAppointmentUC
import com.petgodparents.petgodparents.presentation.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAppointmentActivityVM @Inject constructor(
    private val saveAppointmentUC: SaveAppointmentUC
): ViewModel() {
    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status


    fun saveAppointment(type: String, description: String, patient: String, date: String) {
        viewModelScope.launch {
            _status.value = Status.Loading
            val result = saveAppointmentUC.invoke(Appointment(
                typeId = type,
                description = description,
                pet = patient,
                typeIdPatient = TypeAnimal.DOG.description,
                date = date,
                cost = 2.4,
                status = " Created"
            ))
            if(result) {
                _status.value = Status.Success("Appointment saved successfully")
            } else {
                _status.value = Status.Error("Error saving appointment")
            }
        }
    }


}