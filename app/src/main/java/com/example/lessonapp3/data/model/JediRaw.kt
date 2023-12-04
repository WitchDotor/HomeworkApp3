package com.example.lessonapp3.data.model

import com.google.gson.annotations.SerializedName

data class JediRaw(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)
