package com.phoenix.mist.data

data class RegistrationUIState(
    var firstName :String = "",
    var lastName  :String = "",
    var email  :String = "",
    var password  :String = "",
    var phone_no :String = "",
    var privacyPolicyAccepted :Boolean = false,


    var firstNameError :Boolean = false,
    var lastNameError : Boolean = false,
    var emailError :Boolean = false,
    var phoneError :Boolean = false,
    var passwordError : Boolean = false,
    var privacyPolicyError:Boolean = false
)
