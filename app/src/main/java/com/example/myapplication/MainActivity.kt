package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.widget.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.*
import java.io.*

class MainActivity : AppCompatActivity() {
    val client = OkHttpClient()
    val FORM = "application/x-www-form-urlencoded".toMediaTypeOrNull() //przyjmuje tylko rawJson




    fun httpPost(url: String, body: RequestBody, success: (response: Response)-> Unit, failure:() -> Unit){
        val request = Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Accept", "application/json")
            //.addHeader("content-type", "application/json; charset=utf-8")
            .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                failure()

            }

            override fun onResponse(call: Call, response: Response) {
                success(response)

            }
        })
    }

    fun login(login: String, password: String){
        Toast.makeText(this, "Logowanie (" + login + ":" + password + ")", Toast.LENGTH_SHORT).show()
        val url = "http://3.69.52.61:3000/login"


        val body = ("session[index]=" + login + "&session[password]=" + password).toRequestBody(FORM)
        print("JSON String: (String(data: responseData, encoding: .utf8))")
        httpPost(url, body,
        fun(response:Response){
            Log.v("Info", "Succeeded")
            val response_string = response.body?.string()
            Log.v("INFO", response_string.toString())
            val json = JSONObject(response_string)
            if (json.has("message")){
                this.runOnUiThread{
                    Toast.makeText(this, json["message"] as String, Toast.LENGTH_SHORT).show()
                }

                }
            else if (json.has("token")){
                Toast.makeText(this, json["token"] as String, Toast.LENGTH_SHORT).show()
            }
        },
        fun(){
            Log.v("Info", "Failed")
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_login = findViewById<Button>(R.id.btn_login)
        val login_field = findViewById<EditText>(R.id.login_field)
        val password_field = findViewById<EditText>(R.id.password_field)
        btn_login.setOnClickListener {
            val login = login_field.text.toString()
            val password = password_field.text.toString()
            login(login, password)


        }


    }
}