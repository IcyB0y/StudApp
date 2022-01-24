package com.example.myapplication

import com.google.gson.annotations.*

data class Field(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
@SerializedName("created_at")
    val created_at: String,
@SerializedName("updated_at")
    val updated_at: String,
@SerializedName("url")
    val url: String
)