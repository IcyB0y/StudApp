package com.example.myapplication

import android.content.*
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.*
import com.google.gson.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.*

class FieldScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field_screen)

        configureBackButton();




        val recyclerView_main = findViewById<RecyclerView>(R.id.recyclerView_main)

       val arrayList = ArrayList<String>();

        arrayList.add("Matematyka");
        arrayList.add("Fizyka");
        arrayList.add("Historia");
        arrayList.add("Sport");

        val arrayAdapter = ArrayAdapter(this@FieldScreen, R.layout.field_row, arrayList)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = MainAdapter()
        fechtJson();
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
        val url = "http://3.67.41.247:3000/fields"

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
                    Log.v("Info", "Wybór kategorii nie powiódł się")

                }

                override fun onResponse(call: Call, response: Response) {
                  val body = response.body?.string()
val gson = GsonBuilder().create()
                    val field_list = gson.fromJson(body, FieldStructure::class.java)
                    val recyclerView_main = findViewById<RecyclerView>(R.id.recyclerView_main);
                    runOnUiThread{
                      recyclerView_main.adapter = MainAdapter()
                    }
                }
            })
        }
    }






}