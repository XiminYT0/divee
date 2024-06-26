package com.ximin.divee.presentation.onboardingActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(com.ximin.divee.R.layout.activity_onboarding)
        val singin = findViewById<Button>(com.ximin.divee.R.id.Singin)
        singin.setOnClickListener {
            val intent = Intent(this, OnboardingActivitySingIn::class.java)
            startActivity(intent)




        }
        val singup = findViewById<Button>(com.ximin.divee.R.id.Singup)
        singup.setOnClickListener {
            val intent = Intent(this, OnboardingActivitySingUp::class.java)
            startActivity(intent)
        }
    }
}