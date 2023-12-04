package com.example.lessonapp3.presentation.signUp

object SignUpObject {
    interface View{
        fun navigationToEnterActivity()
        fun navigationToMainActivity(id: Int)
        fun invalidLogin()
        fun inavalidName()
        fun invalidPassword()
        fun invalidConfirmPassword()
        fun userAlreadyExist()
        fun disableErrors()
    }
    interface Presenter{
        fun signUp()
//        fun idInitialization(id: Int): Int
        fun loginInput(data: String)
        fun nameInput(data: String)
        fun passwordInput(data: String)
        fun confirmPasswordInput(data: String)
        fun getId():Int

    }
}