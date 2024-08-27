package com.petgodparents.petgodparents.presentation.forms.validators

abstract class BaseValidator {
    abstract fun validate(value: String): Boolean
}