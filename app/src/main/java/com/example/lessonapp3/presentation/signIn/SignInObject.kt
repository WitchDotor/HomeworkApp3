package com.example.lessonapp3.presentation.signIn

object SignInObject {
    interface View{
        fun navigationToEnterActivity()
        fun navigationToMainActivity(userId: Int)
        fun userNotFound()
        fun invalidPAssword()
        fun disableErrors()
    }
    interface Presenter{

        fun loginInput(data: String)
        fun passwordInput(data: String)
        fun signIn()
        fun dispose()
    }
}