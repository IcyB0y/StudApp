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
    //val fieldList = listOf("kategoria1", "kategoria2")
    companion object {
       var globalId : Int = 0
    }

    ///number OfItems
    override fun getItemCount(): Int{
        return field_list.count()
        //return field_list.fields.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.field_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//val fieldList = fieldList.get(position)
//val field = field_list.fields.get(position)
        val field = field_list[position]

        Log.v("1", field_list.toString())
     //   Log.v("2", field.toString())
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
