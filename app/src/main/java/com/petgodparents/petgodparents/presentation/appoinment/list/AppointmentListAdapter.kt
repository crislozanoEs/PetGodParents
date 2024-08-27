package com.petgodparents.petgodparents.presentation.appoinment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petgodparents.petgodparents.R
import com.petgodparents.petgodparents.domain.entity.Appointment

class AppointmentListAdapter(private val listener: Listener): RecyclerView.Adapter<AppointmentListAdapter.AppointmentViewHolder>() {

    private var appointments: List<Appointment> = listOf()

    fun setAppointments(appointments: List<Appointment>){
        this.appointments = appointments
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appointments.size
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(appointments[position])
    }


    inner class AppointmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(appointment: Appointment) {
            itemView.findViewById<TextView>(R.id.appointment_title).text = appointment.getTypeDescription()
            itemView.findViewById<TextView>(R.id.appointment_date).text = appointment.date
            itemView.findViewById<TextView>(R.id.appointment_status).text = appointment.status
            itemView.findViewById<TextView>(R.id.appointment_cost).text = appointment.cost.toString()
            itemView.findViewById<TextView>(R.id.appointment_pet_type).text = appointment.getTypePatientDescription()
            itemView.findViewById<TextView>(R.id.appointment_pet).text = appointment.pet
            itemView.findViewById<TextView>(R.id.appointment_description).text = appointment.description

            itemView.setOnClickListener {
                listener.onAppointmentClick(appointment)
            }
            itemView.findViewById<Button>(R.id.appointment_button_delete).setOnClickListener {
                listener.onDeleteAppointmentClick(appointment)
            }
            itemView.findViewById<Button>(R.id.appointment_button_edit).setOnClickListener {
                listener.onEditAppointmentClick(appointment)
            }
        }


    }

    interface Listener{
        fun onAppointmentClick(appointment: Appointment)
        fun onDeleteAppointmentClick(appointment: Appointment)
        fun onEditAppointmentClick(appointment: Appointment)
    }
}