package com.example.myapplication

import android.view.*
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.*

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {
    val fieldList = listOf("kategoria1", "kategoria2")
    ///number OfItems
    override fun getItemCount(): Int{
        return 2//field_list.fields.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.field_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
val fieldList = fieldList.get(position)

      holder?.textView_field_title.text =fieldList



    }
}

class CustomViewHolder (val view: View):RecyclerView.ViewHolder(view){
val textView_field_title = view.findViewById<TextView>(R.id.textView_field_title)


}
