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

       val arrayList = ArrayList<String>();

        arrayList.add("Matematyka");
        arrayList.add("Fizyka");
        arrayList.add("Historia");
        arrayList.add("Sport");


        recyclerView_main.layoutManager = LinearLayoutManager(this)
        fechtJson()
     //   recyclerView_main.adapter = MainAdapter()

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
        val url = "http://18.185.157.106:3000/fields"
        val request = Request.Builder()
            .url(url)
          //  .get()
           .addHeader("Accept", "application/json")
            .build()

        val client = OkHttpClient()
      //val FORM = "application/x-www-form-urlencoded".toMediaTypeOrNull()
       // fun httpGet(url: String, success: (response: Response)-> Unit, failure:() -> Unit){


            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                  val body = response?.body?.string()
                    Log.v("INFO", body.toString())
                   val gson = GsonBuilder().create()
println(body)
                    val recyclerView_main = findViewById<RecyclerView>(R.id.recyclerView_main);
                    val field_list = gson.fromJson(body, Array<FieldStructure>::class.java)

                    Log.v("Field_list", field_list.toString() )

                     //   Log.v("Info", "Wybór kategorii udał się")
                    runOnUiThread{


                    //    recyclerView_main.adapter = MainAdapter(field_list = FieldStructure())

                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.v("Info", "Wybór kategorii nie powiódł się")

                }



            })
        }
   // }

}


