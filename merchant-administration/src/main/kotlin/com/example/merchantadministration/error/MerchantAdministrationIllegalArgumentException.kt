package com.example.merchantadministration.error

data class MerchantAdministrationIllegalArgumentException(val validationErrors: List<ValidationError>) : Exception()