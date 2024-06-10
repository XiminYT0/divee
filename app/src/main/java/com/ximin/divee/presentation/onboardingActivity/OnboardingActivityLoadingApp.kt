package com.ximin.divee.presentation.onboardingActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ximin.divee.R
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.DataStoreCase.isOnboardingPassed
import com.ximin.divee.presentation.mainActivity.MainActivity

class OnboardingActivityLoadingApp : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_loading_app)
        val DELAY = 1000

        val handler: Handler = Handler()
        handler.postDelayed(Runnable {
            val intent: Intent = Intent(
                this@OnboardingActivityLoadingApp,
                MainActivity::class.java
            )
            startActivity(intent)
        }, DELAY.toLong())



    }


}