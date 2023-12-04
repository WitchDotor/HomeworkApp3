package com.example.lessonapp3.presentation.jediEnter

object JediObject {
    interface View {

        fun navigateToEnterActivity()
        fun navigateToMainActivity(jediID: Int)
        fun showWrongName()
        fun userAlreadyExist()
        fun invalidPassword()
        fun disableErrors()
        fun showList(list: MutableList<String>)
        fun enableProgressBar()
        fun disableProgressBar()
        fun disableButton()
        fun enableButton()

        fun enableNameInput()
        fun disableNameInput()
        fun enablePasswordInput()
        fun disablePasswordInput()
        fun doAfterDataIsFetched()
        fun doBeforeDataIsFetched()
        fun showWaitingToast()
        fun refreshAdapter()
    }

    interface Presenter {
        fun jediNameInput(name: String)
        fun signInAsJedi()
        fun jediPasswordInput(password: String)
        fun disposeGetAllJedi()
        fun getAllJedi()
        fun disposeSignInAsJedi()
    }
}