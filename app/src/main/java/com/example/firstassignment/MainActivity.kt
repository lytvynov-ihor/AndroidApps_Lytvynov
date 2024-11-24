package com.example.firstassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerNow= findViewById<TextView>(R.id.registerNow)
        registerNow.setOnClickListener{
            Log.d("Onboarding","Register now presses")

            val goToRegistrationIntent = Intent(this@MainActivity,RegisterScreen::class.java)
            startActivity(goToRegistrationIntent)
        }
    }
}