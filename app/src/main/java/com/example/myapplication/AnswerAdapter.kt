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


    override fun getItemCount(): Int{
        return answer_list.count()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.row_answer, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val answerx = answer_list[position]


    holder.textView_answer.text = answerx.answer

        holder.textView_answer.setOnClickListener(){
           if (answerx.is_correct == true)
            globalScore = globalScore + 1


        }

    }


}


