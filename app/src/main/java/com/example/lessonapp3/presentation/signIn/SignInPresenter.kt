package com.example.lessonapp3.presentation.signIn

import com.example.lessonapp3.SignInException
import com.example.lessonapp3.UserService
import io.reactivex.rxjava3.disposables.Disposable


class SignInPresenter(private val view: SignInObject.View, private val userService: UserService) :
    SignInObject.Presenter {

    var login = ""
    var password = ""
    private lateinit var disposable: Disposable

    override fun loginInput(data: String) {
        login = data
    }

    override fun passwordInput(data: String) {
        password = data
    }

    override fun signIn() {
        disposable = userService.signIn(login, password).subscribe({
            view.navigationToMainActivity(it.id)
        },
            {
                view.disableErrors()
                when (it) {
                    SignInException.UserNotFound -> {
                        view.userNotFound()
                    }
                    SignInException.InvalidPassword -> {
                        view.invalidPAssword()
                    }
                }
            })
    }

    override fun dispose() {
        disposable.dispose()
    }
}