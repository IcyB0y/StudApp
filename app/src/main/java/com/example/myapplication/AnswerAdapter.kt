package com.example.myapplication

import android.app.*
import android.content.*
import android.util.*
import android.view.*
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.*
import androidx.recyclerview.widget.*

class AnswerAdapter(private val answer_list: List<Answer>): RecyclerView.Adapter<CustomViewHolder>() {

    companion object {
        var globalScore : Int = 0
    }

    ///number OfItems
    override fun getItemCount(): Int{
        return answer_list.count()
        //return field_list.fields.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.row_answer, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//val fieldList = fieldList.get(position)
//val field = field_list.fields.get(position)
        val answerx = answer_list[position]

       Log.v("1", answer_list.toString())
          Log.v("2", answerx.toString())
    holder.textView_answer.text = answerx.answer

        holder?.textView_answer.setOnClickListener(){
           if (answerx.is_correct == true)
            globalScore = globalScore + 1


        }
        /* holder?.textView_field_title.setOnClickListener(){
            val context=holder.textView_field_title.context
            val intent = Intent( context, GameScreen::class.java)
            globalId = field.id
            println(globalId)
            context.startActivity(intent)
        }
        * */

    }


}


