package com.example.myapplication

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.view.*
import android.widget.*
import com.google.gson.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.*
import java.io.*

class StatisticScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic_screen)
        configureBackButton()
        getStatistic()
    }


    fun configureBackButton() {
        val btn_s_back = findViewById<Button>(R.id.btn_s_back);
        btn_s_back.setOnClickListener(View.OnClickListener {
            val Intent = Intent(this, MainMenu::class.java)
            startActivity(Intent)
            finish()
        })
    }





    val client = OkHttpClient()
    val FORM = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    fun httpGet(url: String, success: (response: Response)-> Unit, failure:() -> Unit){
        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("Accept", "application/json")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                failure()

            }

            override fun onResponse(call: Call, response: Response) {
                success(response)

            }
        })
    }


    fun getStatistic(){
        val url = "http://3.67.41.247:3000/users/4"


        httpGet(url,
            fun(response: Response){
                Log.v("Info", "Succeeded Get Statistic")
                val response_string = response.body?.string()
                Log.v("INFO", response_string.toString())

                val json = JSONObject(response_string)
                if (json.has("last_score")){
                    val s_pkt = findViewById<TextView>(R.id.s_pkt)
                    this.runOnUiThread{
                        val pkt = json.getInt("last_score")
                        s_pkt.setText(pkt.toString());

                    }
                    Log.v("Info", "Udalo sie uzyskac punkty")

                }
            },
            fun(){
                Log.v("Info", "Failed Get Statistic")
            })
    }
}