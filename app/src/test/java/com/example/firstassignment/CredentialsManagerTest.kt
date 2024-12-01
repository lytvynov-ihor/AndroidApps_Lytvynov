package com.example.firstassignment

import org.junit.Test
import org.junit.Assert.*

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    @Test
    fun testEmptyEmail() {
        val email = ""
        val isEmailValid = credentialsManager.isEmailValid(email)
        assertFalse("Empty email should be invalid", isEmailValid)
    }

    @Test
    fun testWrongFormatEmail() {
        val email = "testmail.com"
        val isEmailValid = credentialsManager.isEmailValid(email)
        assertFalse("Invalid email format should fail validation", isEmailValid)
    }

    @Test
    fun testWellFormattedEmail() {
        val email = "test@example.com"
        val isEmailValid = credentialsManager.isEmailValid(email)
        assertTrue("Well-formatted email should pass validation", isEmailValid)
    }

    @Test
    fun testEmptyPassword() {
        val password = ""
        val isPasswordValid = credentialsManager.isPasswordValid(password)
        assertFalse("Empty password should be invalid", isPasswordValid)
    }

    @Test
    fun testFilledPassword() {
        val password = "securepassword123"
        val isPasswordValid = credentialsManager.isPasswordValid(password)
        assert(isPasswordValid)
    }

    @Test
    fun testRegisterWithNewEmail() {
        val email = "newuser@example.com"
        val password = "newpassword123"

        val isRegistered = credentialsManager.register(email, password)
        assert(isRegistered)
    }

    @Test
    fun testRegisterWithExistingEmail() {
        val email = "existinguser@example.com"
        val password = "password123"

        credentialsManager.register(email, password)

        val isRegistered = credentialsManager.register(email, "differentpassword")
        assert(isRegistered)
    }

    @Test
    fun testCaseInsensitiveEmailRegistration() {
        val email = "User@Example.com"
        val password = "password123"

        val isRegisteredFirstTime = credentialsManager.register(email, password)
        assert(isRegisteredFirstTime)

        val isRegisteredSecondTime = credentialsManager.register(email.lowercase(), "differentpassword")
        assert(isRegisteredSecondTime)
    }

    @Test
    fun testLoginWithCorrectCredentials() {
        val email = "loginuser@example.com"
        val password = "password123"

        credentialsManager.register(email, password)

        val canLogin = credentialsManager.areCredentialsValid(email, password)
        assert(canLogin)
    }

    @Test
    fun testLoginWithIncorrectCredentials() {
        val email = "loginuser@example.com"
        val password = "password123"

        credentialsManager.register(email, password)

        val canLogin = credentialsManager.areCredentialsValid(email, "wrongpassword")
        assert(canLogin)
    }

    @Test
    fun testLoginWithCaseInsensitiveEmail() {
        val email = "CaseInsensitiveUser@example.com"
        val password = "password123"

        credentialsManager.register(email, password)

        val canLogin = credentialsManager.areCredentialsValid(email.uppercase(), password)
        assert(canLogin)
    }
}
