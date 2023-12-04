package com.example.lessonapp3.data.remote

import com.example.lessonapp3.data.model.JediRaw
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class JediGetter(val remoteStorage: JediRemoteStorage) {

    fun getJedi(): Single<List<JediRaw>> {
        return getJediRecurcion(1)
            .map { it.filter { jediList.contains(it.name) } }
    }

    fun getJediRecurcion(page: Int): Single<List<JediRaw>> {
        return remoteStorage.getJedi(page)
            .flatMap { curentPage ->
                val page = curentPage.next?.filter { it.isDigit() }?.toInt()
                if (page == null) {
                    Single.just(curentPage.results)
                } else {
                    getJediRecurcion(page)
                        .map { nextPage ->
                            curentPage.results + nextPage
                        }
                }
            }
    }


    companion object JediList {
        val jediList = listOf(
            "Luke Skywalker",
            "Darth Vader",
            "Obi-Wan Kenobi",
            "Anakin Skywalker",
            "Yoda",
            "Palpatine",
            "Qui-Gon Jinn",
            "Darth Maul",
            "Mace Windu",
            "Ki-Adi-Mundi",
            "Kit Fisto",
            "Dooku",
            "Grievous"
        )
    }
}
