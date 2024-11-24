package com.example.firstassignment

import android.util.Patterns

class CredentialsManager {

    fun isEmailValid(email: String): Boolean {
        //Check for empty email
        if (email.isEmpty()) return false

        val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+")
        val regex = Regex(emailPattern)

        return regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        //Check for empty password
        if (password.isEmpty()) return false

        return true
    }
}