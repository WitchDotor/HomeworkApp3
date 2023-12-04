package com.example.lessonapp3.data.model

import com.google.gson.annotations.SerializedName

data class BaseRaw<R>(

    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: R
)
