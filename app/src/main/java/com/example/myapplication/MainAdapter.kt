package com.example.myapplication

import android.app.*
import android.content.*
import android.util.*
import android.view.*
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.*

class MainAdapter(private val field_list: List<Field>): RecyclerView.Adapter<CustomViewHolder>() {

    companion object {
       var globalId : Int = 0
    }


    override fun getItemCount(): Int{
        return field_list.count()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.field_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val field = field_list[position]

        Log.v("1", field_list.toString())

     holder?.textView_field_title.text = field.name

        holder?.textView_field_title.setOnClickListener(){
            val context=holder.textView_field_title.context
            val intent = Intent( context, GameScreen::class.java)
            globalId = field.id
            println(globalId)
            context.startActivity(intent)
        }



    }


}


class CustomViewHolder (val view: View):RecyclerView.ViewHolder(view){
val textView_field_title = view.findViewById<TextView>(R.id.textView_field_title)
val textView_answer = view.findViewById<TextView>(R.id.textView_answer)

}
