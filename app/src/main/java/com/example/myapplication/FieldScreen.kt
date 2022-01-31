package com.example.myapplication

import android.content.*
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.*
import com.example.myapplication.LoginScreen.Companion.globalUrl
import com.google.gson.*
import com.google.gson.annotations.*
import com.google.gson.internal.bind.*
import com.google.gson.reflect.*
import okhttp3.*
import org.json.*
import java.io.*


class FieldScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field_screen)

        configureBackButton();

        val recyclerView_main = findViewById<RecyclerView>(R.id.recyclerView_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        fechtJson()

    }

    fun configureBackButton() {
        val back_btn = findViewById<Button>(R.id.btn_f_back);
        back_btn.setOnClickListener(View.OnClickListener {
            val Intent = Intent(this, MainMenu::class.java)
            startActivity(Intent)
            finish()
        })
    }




    fun fechtJson(){
        val url = globalUrl + "/fields"
        val request = Request.Builder()
            .url(url)
           .addHeader("Accept", "application/json")
            .build()

        val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                  val body = response.body?.string()
                   val gson = GsonBuilder().create()
                    val recyclerView_main = findViewById<RecyclerView>(R.id.recyclerView_main);

                    val jsonData2 = gson.fromJson(body, Array<Field>::class.java).toList()



                        Log.v("Info", "Wybór kategorii udał się")
                    runOnUiThread{


                      recyclerView_main.adapter = MainAdapter(jsonData2)

                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.v("Info", "Wybór kategorii nie powiódł się")

                }



            })
        }


    // }

}


