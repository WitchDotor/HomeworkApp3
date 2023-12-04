package com.example.lessonapp3.presentation.signUp

import com.example.lessonapp3.SignUpException
import com.example.lessonapp3.UserService
import io.reactivex.rxjava3.disposables.Disposable


class SignUpPresenter(
    private val view: SignUpObject.View,
    private val userService: UserService
) : SignUpObject.Presenter {


    private var login = ""
    private var name: String = ""
    private var password: String = ""
    private var confirmPassword = ""
    private var id = 0
    private lateinit var disposable: Disposable


    override fun loginInput(data: String) {
        login = data

    }

    override fun nameInput(data: String) {
        name = data
    }

    override fun passwordInput(data: String) {
        password = data
    }

    override fun confirmPasswordInput(data: String) {
        confirmPassword = data
    }


    override fun signUp() {

        if (confirmPassword == password) {

            disposable = userService.signUp(
                login,
                password,
                name,
            ).subscribe(
                {
                    view.navigationToMainActivity(it.id)
                },
                {
                    view.disableErrors()
                    when (it) {
                        is SignUpException.InvalidLogin -> {
                            view.invalidLogin()
                        }
                        is SignUpException.UserAlreadyExists -> {
                            view.userAlreadyExist()
                        }
                        is SignUpException.InvalidName -> {
                            view.inavalidName()
                        }
                        is SignUpException.InvalidPassword -> {
                            view.invalidPassword()
                        }
                    }
                })

        } else {
            view.invalidConfirmPassword()
        }
    }

    fun dispose() {
        disposable.dispose()
    }


    override fun getId(): Int {
        return id
    }
}