package com.arsundram.sac_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.arsundram.googlesignin2.LoginActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)


        val intent= Intent(this@SplashActivity,LoginActivity::class.java)

        Timer().schedule(3000){startActivity(intent); this@SplashActivity.finish()}
    }
}