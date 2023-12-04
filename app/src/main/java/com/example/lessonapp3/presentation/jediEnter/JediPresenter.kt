package com.example.lessonapp3.presentation.jediEnter

import com.example.lessonapp3.SignUpException
import com.example.lessonapp3.UserService
import com.example.lessonapp3.data.remote.JediGetter
import com.example.lessonapp3.data.remote.JediRemoteStorage
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class JediPresenter(
    val view: JediObject.View,
    private val remoteStorage: JediRemoteStorage,
    val userService: UserService,
    private val jediGetter: JediGetter
) :
    JediObject.Presenter {

    lateinit var disposableGetAllJedi: Disposable
    lateinit var disposableSignInAsJedi: Disposable

    var jediName = ""
    var jediPassword = ""
    var jediNamesList = mutableListOf<String>()

    override fun getAllJedi() {

        disposableGetAllJedi = jediGetter.getJedi()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                view.doBeforeDataIsFetched()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterSuccess {
                view.showList(jediNamesList)
                view.doAfterDataIsFetched()
            }
            .subscribe({
                it.forEach { jediNamesList.add(it.name) }
            }, {})
    }

    override fun jediNameInput(name: String) {
        jediName = name
    }

    override fun jediPasswordInput(password: String) {
        jediPassword = password
    }

    override fun signInAsJedi() {

        disposableSignInAsJedi = userService.signUp(jediName, jediPassword, jediName)
            .subscribe({ user ->
                view.navigateToMainActivity(user.id)
            },
                {
                    when (it) {
                        is SignUpException.InvalidPassword -> view.invalidPassword()
                        is SignUpException.UserAlreadyExists -> view.userAlreadyExist()
                        is SignUpException.InvalidName -> view.showWrongName()
                        is SignUpException.InvalidLogin -> view.showWrongName()
                    }
                })
    }

    override fun disposeGetAllJedi() {
        disposableGetAllJedi.dispose()
    }

    override fun disposeSignInAsJedi() {
        disposableSignInAsJedi.dispose()
    }
}