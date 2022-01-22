package com.example.myapplication

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.widget.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.*
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.*
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success


class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_screen)
        configurePlayButton();
        configureStatisticButton();
        configureLogOutButton();
    }

    fun configurePlayButton(){
        val btn_play = findViewById<Button>(R.id.btn_play)
        btn_play.setOnClickListener(){
           play();
        }
    }
    fun configureStatisticButton(){
        val btn_statistic = findViewById<Button>(R.id.btn_statistic)
        btn_statistic.setOnClickListener(){
           getStatistic();
        }
    }
    fun configureLogOutButton(){
        val btn_logout = findViewById<Button>(R.id.btn_logout)
        btn_logout.setOnClickListener(){
            logout();

        }

    }
    fun play(){
        val i = Intent(this@MainMenu, FieldScreen::class.java)
        startActivity(i)
    }
    fun getStatistic(){
        val i = Intent(this@MainMenu, StatisticScreen::class.java)
        startActivity(i)
    }

    val client = OkHttpClient()
    val FORM = "application/x-www-form-urlencoded".toMediaTypeOrNull()


    fun httpDelete(url: String,body: RequestBody, success: (response: Response)-> Unit, failure:() -> Unit){
        val request = Request.Builder()
            .url(url)
            .delete(body)
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
    fun logout(){
        val url = "http://3.67.41.247:3000/logout"
      //  Toast.makeText(this, "Wylogowywanie", Toast.LENGTH_SHORT).show()
        val body = (url+"4").toRequestBody(FORM)
        httpDelete(url,body,
            fun(response:Response){
                Log.v("Info", "Succeeded LogOut")
                val response_string = response.body?.string()
                Log.v("INFO", response_string.toString())
                val json = JSONObject(response_string)
                if (json.has("errors")){
                    this.runOnUiThread{
                        Toast.makeText(this, json["errors"] as String, Toast.LENGTH_SHORT).show()
                    }
                    Log.v("Info", "Jednak errors")

                }
                else{
                    Log.v("Info", "Coś innego")
                }
            },
            fun(){
                Log.v("Info", "Failed LogOut")
            })
    }






}