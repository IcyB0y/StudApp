package com.example.myapplication

import android.content.*
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.*
import android.widget.*
import androidx.recyclerview.widget.*
import com.example.myapplication.AnswerAdapter.Companion.globalScore
import com.example.myapplication.LoginScreen.Companion.globalUrl
import com.example.myapplication.MainAdapter.Companion.globalId
import com.google.gson.*
import okhttp3.*
import java.io.*

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
        val recyclerView_answer = findViewById<RecyclerView>(R.id.recyclerView_answer);
        recyclerView_answer.layoutManager = LinearLayoutManager(this)
        globalScore =0;
        getQuestions();

    }

    companion object {
        var question_list: List<Question> = listOf()
        var answer_list: List<Answer> = listOf()
        var numberOfQuestions : Int = 0;
    }


    fun getQuestions() {
        val url = globalUrl + "/questions"
        val request = Request.Builder()
            .url(url)
            .addHeader("Accept", "application/json")
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()

                question_list = gson.fromJson(body, Array<Question>::class.java).toList()

                Log.v("QUESTIONS_Info", "Pobranie pytań udało się")
                getAnswer();
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.v("QUESTIONS_Info", "Pobrnaie pytań nie powiódło się")

            }


        })
    }

    fun getAnswer() {
        val url = globalUrl + "/answers"
        val request = Request.Builder()
            .url(url)
            .addHeader("Accept", "application/json")
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()

                answer_list = gson.fromJson(body, Array<Answer>::class.java).toList()


                Log.v("Answer_Info", "Pobranie odpowiedzi udało się")
                startGame();
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.v("Answer_Info", "Pobrnaie odpowiedzi nie powiódło się")

            }


        })
    }

    fun startGame() {


        var filterQuestions: List<Question> = question_list.filter { it.field_id == globalId }

         numberOfQuestions = filterQuestions.count()
        val textView_question = findViewById<TextView>(R.id.textView_question)
        val recyclerView_answer = findViewById<RecyclerView>(R.id.recyclerView_answer);

        println(numberOfQuestions)
var i : Int =0
        var b: Int =0;
       repeat (numberOfQuestions) {
           b=i
            val question = filterQuestions.get(i)
           var filterAnswers: List<Answer> = answer_list.filter { it.question_id == question.id}

           runOnUiThread{

               recyclerView_answer.adapter = AnswerAdapter(filterAnswers)

           }

            textView_question.text = question.question

           runOnUiThread(){
           textView_question.setOnClickListener(){
               b++

           }
               runOnUiThread(){
                   Handler(Looper.getMainLooper()).postDelayed({
                      if(b==i){
                          b++
                      }
                   }
                       , 5000)
               }
           }
           while(b==i){println(b)}
           i++



//miejsce na update last_score
           Log.v("Twój wynik to: ", globalScore.toString())

        }
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@GameScreen, StatisticScreen::class.java)
            startActivity(i)
            finish()
        }
            , 1000)

}}


