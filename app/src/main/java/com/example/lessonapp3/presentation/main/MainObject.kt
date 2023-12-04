package com.example.lessonapp3.presentation.main

object MainObject {
    interface View {
        fun navigateToEnterActivity()
        fun showWelcomText(user: Boolean, userName: String)

    }

    interface Presenter {
        fun signOut()
        fun userInitialization(id: Int)
        fun dispose()
    }
}