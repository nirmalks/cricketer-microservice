package com.example.demo

import lombok.Data

@Data
data class Cricketer(
        val id: String,
        val name:String,
        val highestScore: Int,
        val country: String
)