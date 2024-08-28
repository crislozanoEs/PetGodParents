package com.petgodparents.petgodparents.presentation.forms

import android.widget.Button
import java.lang.ref.WeakReference

class Form (private val button: WeakReference<Button>) {

    private val fields = mutableListOf<Field>()

    private val isValid: Boolean
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
        button.get()?.isEnabled = isValid
    }

    fun validateThis(isValid: Boolean) {
        if(isValid) {
            checkButtonStatus()
        } else {
            button.get()?.isEnabled = false
        }
    }

    interface Field {
        var form: Form?
        var isValueValid: Boolean?
    }

}