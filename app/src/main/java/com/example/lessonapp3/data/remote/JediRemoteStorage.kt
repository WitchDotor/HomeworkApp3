package com.example.lessonapp3.data.remote

import com.example.lessonapp3.data.model.JediRaw
import com.example.lessonapp3.presentation.jediEnter.JediPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class JediRemoteStorage {

    private val retrofit: Retrofit = buildRetrofit()
    private val service: JediService = retrofit.create(JediService::class.java)

    fun getJedi(page: Int) = service.getJedi(page)

    private fun buildRetrofit(): Retrofit{
       return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
