package com.example.lessonapp3.data.remote

import com.example.lessonapp3.data.model.BaseRaw
import com.example.lessonapp3.data.model.JediRaw
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JediService {
    @GET("people/")
    fun getJedi(@Query("page") page: Int): Single<BaseRaw<List<JediRaw>>>
}