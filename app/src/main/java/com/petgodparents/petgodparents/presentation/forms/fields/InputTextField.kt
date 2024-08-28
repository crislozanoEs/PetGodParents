package com.petgodparents.petgodparents.presentation.forms.fields

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.petgodparents.petgodparents.presentation.forms.Form
import com.petgodparents.petgodparents.presentation.forms.validators.BaseValidator
import java.lang.ref.WeakReference

class InputTextField(private val editText: WeakReference<EditText>, private val validator: BaseValidator, private val changeState: Boolean = false): Form.Field {

    override var form: Form? = null
    override var isValueValid: Boolean? = null

    init {
        editText.get()?.doAfterTextChanged {
            val isValid = validator.validate(it.toString())
            isValueValid = isValid
            isValueValid?.let { isValidData ->
                form?.validateThis(isValidData)
                if(changeState) {
                    when(isValid) {
                        true -> editText.get()?.error = null
                        false -> editText.get()?.error = "Invalid input"
                    }
                }
            }
            editText.get()?.requestFocus()
        }
    }
}