package com.example.myapplication

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.view.*
import android.widget.*
import com.example.myapplication.LoginScreen.Companion.globalUrl
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.*
import java.io.*

class RegistrationScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_screen)
        configureRegisterButton();
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

     fun configureRegisterButton() {
         val r_username = findViewById<EditText>(R.id.registration_username)
         val r_password = findViewById<EditText>(R.id.registration_password)
         val r_password_confirmation =
             findViewById<EditText>(R.id.registarion_password_confirmation)
         val btn_register = findViewById<Button>(R.id.registration_button)
         btn_register.setOnClickListener {
             val username = r_username.text.toString()
             val password = r_password.text.toString()
             val password_confirmation = r_password_confirmation.text.toString()
             register(username, password)
         }
     }



    val client = OkHttpClient()
    val FORM = "application/x-www-form-urlencoded".toMediaTypeOrNull()




    fun httpPost(url: String, body: RequestBody, success: (response: Response)-> Unit, failure:() -> Unit){
        val request = Request.Builder()
            .url(url)
            .post(body)
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
    fun register(username: String, password: String){
        val url = globalUrl + "/users"


        val body = ("user[username]=" + username + "&user[password]=" + password).toRequestBody(FORM)
        print("JSON String: (String(data: responseData, encoding: .utf8))")
        httpPost(url, body,
            fun(response:Response){
                Log.v("Info", "Succeeded")
                val response_string = response.body?.string()
                Log.v("INFO", response_string.toString())
                val json = JSONObject(response_string)
              if (json.has("password")){
                    this.runOnUiThread{

                        Toast.makeText(this,"Hasło powinno mieć minimum 6 znaków", Toast.LENGTH_SHORT).show()
                    }

                }
                else {
                  this.runOnUiThread {
                      Toast.makeText(
                          this,
                          "Pomyślnie zarejestrowano użytkownika: " + username ,
                          Toast.LENGTH_SHORT
                      ).show()
                  }
              }
            },
            fun(){
                Log.v("Info", "Failed")
            })
    }









}