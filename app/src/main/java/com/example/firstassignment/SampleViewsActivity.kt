package com.example.firstassignment

import android.os.Bundle
import android.content.Intent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace


class SampleViewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sample_views)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<View>(R.id.switchBtn).setOnClickListener{
            val currentFragment = supportFragmentManager.findFragmentById(R.id.sampleActivity)
            supportFragmentManager.commit{
                if(currentFragment is FragmentA){

                    replace<FragmentB>(R.id.sampleActivity)
                    addToBackStack(null)
                }
                else{
                    supportFragmentManager.popBackStack()
                    replace<FragmentA>(R.id.sampleActivity)
                }
            }
        }
    }
}