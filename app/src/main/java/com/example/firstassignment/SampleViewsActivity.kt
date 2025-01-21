package com.example.firstassignment

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            credentialsManager.isLoggedIn.collectLatest { isLoggedIn ->
                if (isLoggedIn) {
                    switchToRecipesFragment()
                } else {
                    switchToLoginFragment()
                }
            }
        }

        if (savedInstanceState == null) {
            switchToLoginFragment()
        }
    }

    fun switchToRegisterFragment() {
        supportFragmentManager.commit {
            replace<RegisterFragment>(R.id.sampleActivity)
            addToBackStack(null) // Enable back navigation
        }
    }

    fun switchToLoginFragment() {
        supportFragmentManager.commit {
            replace<LoginFragment>(R.id.sampleActivity)
        }
    }

    fun switchToRecipesFragment() {
        supportFragmentManager.commit {
            replace<RecipesFragment>(R.id.sampleActivity)
        }
    }
}
