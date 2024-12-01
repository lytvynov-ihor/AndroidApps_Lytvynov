package com.example.firstassignment

class CredentialsManager {
    private val credentials = mutableMapOf<String, String>()

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
            return true
        }
        return areCredentialsValid(email, password)
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
}