package com.example.lessonapp3.presentation.main

import com.example.lessonapp3.UserService
import io.reactivex.rxjava3.disposables.Disposable

class MainPresenter(private val view: MainObject.View, private val userService: UserService) :
    MainObject.Presenter {


    lateinit var disposable: Disposable

    override fun signOut() {
        view.navigateToEnterActivity()
    }

    override fun userInitialization(id: Int) {
        disposable = userService.getUser(id).subscribe(
            {
                view.showWelcomText(true, it.name)
            },
            { view.showWelcomText(false, "") }
        )
    }

    override fun dispose() {
        disposable.dispose()
    }
}