package com.example.firstassignment

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CredentialsManager public constructor() {
    private val credentials = mutableMapOf<String, String>()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")

    fun isEmailValid(email: String): Boolean {
        val regex = Regex(emailPattern)
        return regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun areCredentialsValid(email: String, password: String): Boolean {
        return credentials[email.lowercase()] == password
    }

    fun login(email: String, password: String): Boolean {
        if (email == "test@te.st" && password == "1234") {
            _isLoggedIn.value = true
            return true
        }

        if (areCredentialsValid(email, password)) {
            _isLoggedIn.value = true
            return true
        }

        return false
    }

    fun logout() {
        _isLoggedIn.value = false
    }

    fun register(email: String, password: String): Boolean {
        if (isEmailValid(email) && isPasswordValid(password)) {
            val normalizedEmail = email.lowercase()
            if (credentials.containsKey(normalizedEmail)) {
                return false
            }
            credentials[normalizedEmail] = password
            return true
        }
        return false
    }

    companion object {
        val instance: CredentialsManager by lazy { CredentialsManager() }
    }
}
