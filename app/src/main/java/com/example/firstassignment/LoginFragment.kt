package com.example.firstassignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment(R.layout.login_activity) {
    private val credentialsManager = CredentialsManager.instance

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerNow = view.findViewById<TextView>(R.id.registerNow)
        val loginEmail = view.findViewById<TextInputLayout>(R.id.emailInputLayout)
        val loginPassword = view.findViewById<TextInputLayout>(R.id.loginPassword)
        val loginNextButton = view.findViewById<Button>(R.id.Next)

        loginNextButton.setOnClickListener {
            val emailText = loginEmail.editText?.text.toString().trim()
            val passwordText = loginPassword.editText?.text.toString().trim()

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

            val loginSuccessful = credentialsManager.login(emailText, passwordText)

            if (loginSuccessful) {
                (requireActivity() as? SampleViewsActivity)?.switchToRecipesFragment()
            } else {
                loginEmail.error = "Incorrect email or password"
                loginPassword.error = "Incorrect email or password"
            }
        }

        registerNow.setOnClickListener {
            (requireActivity() as? SampleViewsActivity)?.switchToRegisterFragment()
        }
    }
}
