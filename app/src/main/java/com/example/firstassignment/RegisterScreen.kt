package com.example.firstassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout

class RegisterScreen : AppCompatActivity() {

    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)

        val logIn = findViewById<TextView>(R.id.logIn)
        logIn.setOnClickListener {

            val goToLogInIntent = Intent(this,loginActivity::class.java)
            startActivity(goToLogInIntent)
        }

        val registerButton = findViewById<Button>(R.id.registerButton)
        val emailInputLayout = findViewById<TextInputLayout>(R.id.textInputLayoutEmail)
        val passwordInputLayout = findViewById<TextInputLayout>(R.id.textInputLayoutPassword)

        registerButton.setOnClickListener {
            val email = emailInputLayout.editText?.text.toString().trim()
            val password = passwordInputLayout.editText?.text.toString().trim()

            var isValid = true

            if (!credentialsManager.isEmailValid(email))
            {
                emailInputLayout.error = "Invalid email"
                isValid = false
            }
            else
            {
                emailInputLayout.error = null
            }

            if (!credentialsManager.isPasswordValid(password))
            {
                passwordInputLayout.error = "Password cannot be empty"
                isValid = false
            }
            else
            {
                passwordInputLayout.error = null
            }

            if (isValid) {
                val registrationSuccess = credentialsManager.register(email, password)
                if (registrationSuccess)
                {
                    val intent = Intent(this, loginActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    emailInputLayout.error = "Email already taken"
                }
            }
        }
    }
}