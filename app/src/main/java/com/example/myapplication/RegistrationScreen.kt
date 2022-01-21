package com.example.myapplication

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*

class RegistrationScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_screen)
        configureBackButton();
    }

    fun configureBackButton() {



        val back_btn = findViewById<Button>(R.id.registration_back_button);
        back_btn.setOnClickListener(View.OnClickListener {
            val Intent = Intent(this, LoginScreen::class.java)
            startActivity(Intent)
            finish()
        })

    }
}