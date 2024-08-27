package com.petgodparents.petgodparents.presentation.forms.fields

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.petgodparents.petgodparents.presentation.forms.Form
import com.petgodparents.petgodparents.presentation.forms.validators.BaseValidator

class InputTextField(private val editText: EditText, private val validator: BaseValidator, private val changeState: Boolean = false): Form.Field {

    override var form: Form? = null
    override var isValueValid: Boolean? = null

    init {
        editText.doAfterTextChanged {
            val isValid = validator.validate(it.toString())
            isValueValid = isValid
            isValueValid?.let { isValidData ->
                form?.validateThis(isValidData)
                if(changeState) {
                    when(isValid) {
                        true -> editText.error = null
                        false -> editText.error = "Invalid input"
                    }
                }
            }
            editText.requestFocus()
        }
    }
}