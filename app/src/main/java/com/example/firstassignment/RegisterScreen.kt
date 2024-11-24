package com.example.firstassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegisterScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)

        val logIn = findViewById<TextView>(R.id.logIn)
        logIn.setOnClickListener {
            val goToLogInIntent = Intent(this,MainActivity::class.java)
            startActivity(goToLogInIntent)
        }
    }
}
