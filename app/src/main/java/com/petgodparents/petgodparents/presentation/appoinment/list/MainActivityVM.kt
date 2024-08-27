package com.petgodparents.petgodparents.presentation.appoinment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petgodparents.petgodparents.domain.entity.Appointment
import com.petgodparents.petgodparents.domain.useCase.GetAppointmentsUC
import com.petgodparents.petgodparents.presentation.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(private val getAppointmentsUC: GetAppointmentsUC): ViewModel() {

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    fun getAppointments() {
        viewModelScope.launch {
            _status.value = Status.Loading
            val result = getAppointmentsUC.invoke()
            if(result.isEmpty()) {
                _status.value = Status.Error("No appointments found")
            } else {
                _status.value = Status.Success(result)
            }
        }
    }


}