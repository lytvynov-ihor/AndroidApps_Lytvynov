package com.example.firstassignment

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class SampleViewsActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sample_views)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initial fragment
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.sampleActivity, LoginFragment(credentialsManager))
            }
        }
    }

    fun switchToRegisterFragment() {
        supportFragmentManager.commit {
            replace(R.id.sampleActivity, RegisterFragment(credentialsManager))
            addToBackStack(null)
        }
    }

    fun switchToLoginFragment() {
        supportFragmentManager.commit {
            replace(R.id.sampleActivity, LoginFragment(credentialsManager))
            addToBackStack(null)
        }
    }
}
