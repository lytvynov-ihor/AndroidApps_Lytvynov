package com.example.firstassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class loginActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val registerNow = findViewById<TextView>(R.id.registerNow)
        val loginEmail = findViewById<TextInputLayout>(R.id.emailInputLayout)
        val loginPassword = findViewById<TextInputLayout>(R.id.loginPassword)
        val loginNextButton = findViewById<Button>(R.id.Next)

        loginNextButton.setOnClickListener {
            val emailText = loginEmail.editText?.text.toString().trim()
            val passwordText = loginPassword.editText?.text.toString().trim()
            val correctEmail = "test@te.st"
            val correctPassword = "1234"

            loginEmail.error = null
            loginPassword.error = null

            if (!credentialsManager.isEmailValid(emailText)) {
                loginEmail.error = "Invalid email format"
                return@setOnClickListener
            }

            if (!credentialsManager.isPasswordValid(passwordText)) {
                loginPassword.error = "Password cannot be empty"
                return@setOnClickListener
            }

            val loginSuccessful = (emailText == correctEmail && passwordText == correctPassword) ||
                    credentialsManager.login(emailText, passwordText)

            if (loginSuccessful)
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else
            {
                loginEmail.error = "Incorrect email or password"
                loginPassword.error = "Incorrect email or password"
            }
        }

        registerNow.setOnClickListener {
            val goToRegistrationIntent = Intent(this@loginActivity, RegisterScreen::class.java)
            startActivity(goToRegistrationIntent)
        }
    }
}