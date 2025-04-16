package com.raj.assigment_mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.raj.assigment_mvvm.view.UserInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        Thread.sleep(2000)
        setContentView(R.layout.activity_main)
        openUserInfo()

    }

    fun openUserInfo() {
        val fragment = UserInfo()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Frame_layout, fragment)
        // Transaction.addToBackStack("User_info_Fragment")
        transaction.commit()
    }
}