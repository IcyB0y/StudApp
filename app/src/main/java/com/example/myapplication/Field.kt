package com.example.myapplication

import com.google.gson.annotations.*

data class Field(
    val id: Int,
    val name: String,
    val created_at: String,
    val updated_at: String,
    val url: String
)