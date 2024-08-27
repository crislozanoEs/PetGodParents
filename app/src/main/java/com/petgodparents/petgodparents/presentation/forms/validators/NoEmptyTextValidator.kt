package com.petgodparents.petgodparents.presentation.forms.validators

class NoEmptyTextValidator: BaseValidator() {

    override fun validate(value: String): Boolean {
        return value.isNotEmpty()
    }

}