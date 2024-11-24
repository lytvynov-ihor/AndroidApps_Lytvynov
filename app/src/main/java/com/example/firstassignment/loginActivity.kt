package com.example.firstassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)


        val credentialsManager = CredentialsManager()
        val registerNow = findViewById<TextView>(R.id.registerNow)
        val loginEmail = findViewById<TextInputLayout>(R.id.emailInputLayout)
        val loginPassword = findViewById<TextInputLayout>(R.id.loginPassword)
        val loginNextButton = findViewById<Button>(R.id.Next)

        loginNextButton.setOnClickListener{

            val emailText = loginEmail.editText?.text.toString()
            val passwordText = loginPassword.editText?.text.toString()
            val correctEmail = "test@te.st"
            val correctPassword = "1234"

            if (!credentialsManager.isEmailValid(emailText)) {
                loginEmail.error = "Wrong email"
            }

            if (!credentialsManager.isPasswordValid(passwordText)) {
                loginPassword.error = "Password is not valid"
            }

            if (credentialsManager.checkLoginData(emailText, passwordText)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            else {
                loginEmail.error = "Wrong email or password"
                loginPassword.error = "Wrong email or password"
            }
        }
        registerNow.setOnClickListener{
            Log.d("Onboarding","Register now presses")

            val goToRegistrationIntent = Intent(this@loginActivity,RegisterScreen::class.java)
            startActivity(goToRegistrationIntent)
        }



    }








    }
