package com.ximin.divee.presentation.onboardingActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ximin.divee.R

class OnboardingActivitySingUpEmail : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_singup_email)
        val singup_phone = findViewById<Button>(com.ximin.divee.R.id.singup_phone)
        singup_phone.setOnClickListener {
            val intent = Intent(this, OnboardingActivitySingUp::class.java)
            startActivity(intent)
        }
        val next = findViewById<Button>(com.ximin.divee.R.id.nexn_singin)
        next.setOnClickListener {
            val intent = Intent(this, OnboardingActivityPersonName::class.java)
            startActivity(intent)
        }
    }
}