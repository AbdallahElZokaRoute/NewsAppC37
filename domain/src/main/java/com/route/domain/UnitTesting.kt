package com.route.domain

/**
 * Testing "In general"-> Code that tests another code
 * Registeration Screen
 * attributes -> username , password, passwordCofirmation
 * methods -> register
 * Reliable
 * Proberly
 *
 * Unit Testing ->
 *          Unit Testing vs run on real device
 *                          run on real device tests takes more time
 *                          than unit testing
 *
 *
 */
class RegisterationUtils {
    var userName: String? = null
    var password: String? = null
    var passwordConfimration: String? = null

    /**
     * if User name is Blank then return false
     * if password is blank then return false
     * if username.length < 5 then return false
     * if password is < 6 chars then return false
     * if password != passwordConfirmation then return false
     *
     */
    //Espresso -> EditText -> Ahmed
    //Espresso -> EditText -> 123456
    //Espresso -> Button -> perform(click())

    fun validateRegisterData(
        userName: String?,
        password: String?,
        passwordConfimration: String?
    ): Boolean {
        if (userName == null) return false
        if (userName.isBlank()) return false
        if (userName.length < 5) return false
        if (password == null) return false
        if (password.length < 6) return false
        if (passwordConfimration == null) return false
        if (password != passwordConfimration) return false

        return true
    }
}
