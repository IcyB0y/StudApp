package com.example.myapplication

data class Answer(
    val answer: String,
    val created_at: String,
    val id: Int,
    val is_correct: Boolean,
    val question_id: Int,
    val updated_at: String,
    val url: String
)