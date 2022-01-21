package com.example.myapplication

import android.content.*
import android.os.*
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class StartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        viewLogo();
        showNextScreen();
    }
  fun viewLogo(){
   val logo = findViewById<ImageView>(R.id.logo);

      Handler(Looper.getMainLooper()).postDelayed({


        logo.setVisibility(View.VISIBLE);
        }
    , 3000)
}
    fun showNextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
        val i = Intent(this@StartScreen, LoginScreen::class.java)
        startActivity(i)
        finish()
        }
            , 8000)
    }
}