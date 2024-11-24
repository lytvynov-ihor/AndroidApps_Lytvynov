package com.example.firstassignment

import org.junit.Test
import org.junit.Assert.assertFalse

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    @Test
    fun testEmptyEmail() {
        val email = ""
        val isEmailValid = credentialsManager.isEmailValid(email)
        assert(isEmailValid) //Empty email, test should fail
    }

    @Test
    fun testWrongFormatEmail() {
        val email = "testmail.com"
        val isEmailValid = credentialsManager.isEmailValid(email)
        assert(isEmailValid) //Invalid email, test should fail
    }

    @Test
    fun testWellFormattedEmail() {
        val email = "test@example.com"
        val isEmailValid = credentialsManager.isEmailValid(email)
        assert(isEmailValid) //Valid email, test should pass
    }

    @Test
    fun testEmptyPassword() {
        val password = " "
        val isPasswordValid = credentialsManager.isPasswordValid(password)
        assertFalse(isPasswordValid) //Empty password, test should fail
    }

    @Test
    fun testFilledPassword() {
        val password = "securepassword123"
        //val password = ""
        val isPasswordValid = credentialsManager.isPasswordValid(password)
        assert(isPasswordValid) //Any non-empty password should pass
    }
}