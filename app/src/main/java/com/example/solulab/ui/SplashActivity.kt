package com.example.solulab.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.solulab.R

class SplashActivity : AppCompatActivity() {

    private lateinit var logoImage:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        logoImage = findViewById(R.id.image_solulabs_logo)

        startAnimation()
        startTimer()
    }

    private fun startAnimation() {
        var animation = AnimationUtils.loadAnimation(applicationContext,R.anim.slide_up)
        logoImage.startAnimation(animation)
    }

    private fun startTimer() {

        object: CountDownTimer(2000, 2000) {
            override fun onFinish() {
                goToRespectiveScreen()
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }.start()

    }

    private fun goToRespectiveScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}