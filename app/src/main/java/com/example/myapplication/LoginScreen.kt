package com.example.myapplication

import android.content.*
import android.graphics.Insets.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.widget.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.*
import java.io.*

open class LoginScreen : AppCompatActivity() {

    val client = OkHttpClient()
    val FORM = "application/x-www-form-urlencoded".toMediaTypeOrNull()
companion object {
    public var globaltoken = "";
    public var globalusername = "";
    val globalUrl = "http://18.185.90.7:3000"
}

    fun httpPost(url: String, body: RequestBody, success: (response: Response)-> Unit, failure:() -> Unit){
        val request = Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Accept", "application/json")
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
        val url = globalUrl + "/login"


        val body = ("session[username]=" + login + "&session[password]=" + password).toRequestBody(FORM)

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

                 globaltoken =  json["token"] as String
                globalusername = login;

                val i = Intent(this@LoginScreen, MainMenu::class.java)
                startActivity(i)
                finish()

            }
        },
        fun(){
            Log.v("Info", "Failed")
        })
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        register();
        signIn();

    }

    fun signIn() {
        val btn_login = findViewById<Button>(R.id.btn_login)
        val login_field = findViewById<EditText>(R.id.field_username)
        val password_field = findViewById<EditText>(R.id.field_password)

        btn_login.setOnClickListener {
          val username = login_field.text.toString()
            val password = password_field.text.toString()
            login(username, password);
        }

    }

    fun register() {
        val btn_new_user = findViewById<Button>(R.id.btn_new_user);
btn_new_user.setOnClickListener(){
        val i = Intent(this@LoginScreen, RegistrationScreen::class.java)
        startActivity(i)
    }
    }
}