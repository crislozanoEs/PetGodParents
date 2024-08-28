package com.petgodparents.petgodparents.presentation.appoinment.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.petgodparents.petgodparents.databinding.ActivityMainBinding
import com.petgodparents.petgodparents.domain.entity.Appointment
import com.petgodparents.petgodparents.presentation.Status
import com.petgodparents.petgodparents.presentation.appoinment.create.CreateAppointmentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AppointmentListAdapter.Listener {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainActivityVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.newAppointment.setOnClickListener {
            startActivity(Intent(this, CreateAppointmentActivity::class.java))
        }
        val adapter = AppointmentListAdapter(this)
        binding.appointmentList.adapter = adapter
        binding.appointmentList.layoutManager = LinearLayoutManager(this)

        viewModel.getAppointments()

        viewModel.status.observe(this) {
            when (it) {
                is Status.Loading -> {
                    binding.loading.isVisible = true
                }

                is Status.Success<*> -> {
                    binding.loading.isVisible = false
                    if(it.data is List<*>){
                        adapter.setAppointments(it.data as List<Appointment>)
                    }
                }

                is Status.Error -> {
                    binding.loading.isVisible = false
                    binding.appointmentList.isVisible = false
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        startActivity(Intent(this, CreateAppointmentActivity::class.java))
    }

    override fun onAppointmentClick(appointment: Appointment) {
        TODO("Not yet implemented")
    }

    override fun onDeleteAppointmentClick(appointment: Appointment) {
        TODO("Not yet implemented")
    }

    override fun onEditAppointmentClick(appointment: Appointment) {
        TODO("Not yet implemented")
    }
}