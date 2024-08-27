package com.petgodparents.petgodparents.presentation.forms

import android.widget.Button

class Form (val button: Button) {

    private val fields = mutableListOf<Field>()

    val isValid: Boolean
        get() {
            if(fields.isEmpty()){
                return false
            }
            for(item in fields) {
                item.isValueValid?.let {
                    if(!it){
                        return false
                    }
                } ?: return false
            }
            return true
        }

    fun addInputToForm(field: Field) {
        fields.add(field)
        field.form = this
        checkButtonStatus()
    }

    private fun checkButtonStatus() {
        button.isEnabled = isValid
    }

    fun validateThis(isValid: Boolean) {
        if(isValid) {
            checkButtonStatus()
        } else {
            button.isEnabled = false
        }
    }

    interface Field {
        var form: Form?
        var isValueValid: Boolean?
    }

}