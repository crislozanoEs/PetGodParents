package com.petgodparents.petgodparents.presentation.appoinment.create

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.petgodparents.petgodparents.R
import com.petgodparents.petgodparents.databinding.ActivityCreateAppointmentBinding
import com.petgodparents.petgodparents.presentation.Status
import com.petgodparents.petgodparents.presentation.appoinment.list.MainActivity
import com.petgodparents.petgodparents.presentation.appoinment.list.MainActivityVM
import com.petgodparents.petgodparents.presentation.forms.Form
import com.petgodparents.petgodparents.presentation.forms.fields.InputTextField
import com.petgodparents.petgodparents.presentation.forms.validators.NoEmptyTextValidator
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
class CreateAppointmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAppointmentBinding

    private val viewModel by viewModels<CreateAppointmentActivityVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayAdapter = ArrayAdapter.createFromResource(this, R.array.appointment_status, android.R.layout.simple_expandable_list_item_1)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createAppointmentType.adapter = arrayAdapter


        val form = Form(WeakReference(binding.createAppointmentButton))
        form.addInputToForm(InputTextField(binding.createAppointmentDescription, NoEmptyTextValidator(), true))
        form.addInputToForm(InputTextField(binding.createAppointmentPet, NoEmptyTextValidator(), true))
        form.addInputToForm(InputTextField(binding.createAppointmentDate, NoEmptyTextValidator(), true))

        form.addInputToForm(object: Form.Field {
            override var form: Form? = null
            override var isValueValid: Boolean? = false
                get() = binding.createAppointmentType.selectedItemPosition > 0

        })

        binding.createAppointmentType.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                if(position > 0) {
                    form.validateThis(true)
                    return
                } else {
                    form.validateThis(false)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Nothing to do here
            }
        }

        binding.createAppointmentButton.setOnClickListener {
            viewModel.saveAppointment(binding.createAppointmentType.selectedItem.toString(), binding.createAppointmentDescription.text.toString(), binding.createAppointmentPet.text.toString(), binding.createAppointmentDate.text.toString())
        }

        viewModel.status.observe(this) {
            when(it) {
                is Status.Loading -> {
                    binding.loading.isVisible = true
                }
                is Status.Success<*> -> {
                    binding.loading.isVisible = false
                    showSuccessDialog(it.data.toString(), goToMainActivity())
                }

                is Status.Error -> {
                    binding.loading.isVisible = false
                    showFailDialog(it.errorMessage)
                }
            }
        }

    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showFailDialog(errorMessage: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_error)
        dialog.findViewById<TextView>(R.id.success_description).text = errorMessage
        dialog.findViewById<TextView>(R.id.ok_button).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showSuccessDialog(message: String, goToMainActivity: Unit) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_success)
        dialog.findViewById<TextView>(R.id.success_description).text = message
        dialog.findViewById<TextView>(R.id.ok_button).setOnClickListener {
            dialog.dismiss()
            goToMainActivity()
        }
        dialog.show()
    }
}