package com.example.lessonapp3.presentation.jediEnter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.lessonapp3.R
import com.example.lessonapp3.UserService
import com.example.lessonapp3.data.remote.JediGetter
import com.example.lessonapp3.data.remote.JediRemoteStorage
import com.example.lessonapp3.databinding.ActivityJediBinding
import com.example.lessonapp3.presentation.enter.EnterActivity
import com.example.lessonapp3.presentation.main.MainActivity
import com.example.lessonapp3.presentation.signIn.SignInActivity

class JediActivity : AppCompatActivity(), JediObject.View {

    private lateinit var binding: ActivityJediBinding
    private lateinit var presenter: JediObject.Presenter
    private lateinit var jediNameList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJediBinding.inflate(layoutInflater)
        presenter =
            JediPresenter(this, JediRemoteStorage(), UserService(), JediGetter(JediRemoteStorage()))
        setContentView(binding.root)

        jediNameList = mutableListOf()

        doBeforeDataIsFetched()

        presenter.getAllJedi()


        binding.actvjediName.doAfterTextChanged {
            presenter.jediNameInput(it.toString())
        }

        binding.etjediPassword.doAfterTextChanged {
            presenter.jediPasswordInput(it.toString())
        }
        binding.btnJediSignIn.setOnClickListener {
            presenter.signInAsJedi()
        }
        binding.ibtnToEnterActivity.setOnClickListener {
            navigateToEnterActivity()
        }


        adapter = ArrayAdapter(
            this, android.R.layout.simple_dropdown_item_1line, jediNameList
        )

        val autoCompleteText = binding.actvjediName
        autoCompleteText.setAdapter(adapter)

        autoCompleteText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                autoCompleteText.showDropDown()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.disposeGetAllJedi()
        presenter.disposeSignInAsJedi()
    }

    override fun refreshAdapter() {
        if (adapter.isEmpty) {
            adapter.addAll(jediNameList)
        }
    }
    override fun showWaitingToast() {
        Toast.makeText(this, getString(R.string.jedi_enter_toast_wait_until_fetching_data), Toast.LENGTH_LONG).show()
    }

    override fun doAfterDataIsFetched() {
        enableNameInput()
        enableButton()
        enablePasswordInput()
        disableProgressBar()
        refreshAdapter()
    }

    override fun doBeforeDataIsFetched() {
        disableButton()
        disableNameInput()
        disablePasswordInput()
        enableProgressBar()
        showWaitingToast()
    }

    override fun enablePasswordInput() {
        binding.etjediPassword.isEnabled = true
    }

    override fun disablePasswordInput() {
        binding.etjediPassword.isEnabled = false
    }

    override fun disableNameInput() {
        binding.actvjediName.isEnabled = false
    }

    override fun enableNameInput() {
        binding.actvjediName.isEnabled = true
    }

    override fun disableButton() {
        binding.btnJediSignIn.isEnabled = false
    }

    override fun enableButton() {
        binding.btnJediSignIn.isEnabled = true
    }

    override fun showList(list: MutableList<String>) {
        jediNameList = list
    }

    override fun navigateToEnterActivity() {
        val intent = Companion.intentToEnterActivity(this)
        startActivity(intent)
    }

    override fun navigateToMainActivity(jediID: Int) {
        val intent = Companion.intentToMainActivity(this, jediID)
        startActivity(intent)
    }

    override fun disableErrors() {
        binding.nameInput.error = null
        binding.passwordInput.error = null
    }

    override fun showWrongName() {
        binding.nameInput.error = getString(R.string.wrong_jedi_name)
    }

    override fun userAlreadyExist() {
        binding.nameInput.error = getString(R.string.user_already_exist)
    }

    override fun invalidPassword() {
        binding.passwordInput.error = getString(R.string.short_password)
    }

    override fun enableProgressBar() {
        binding.progressBar.isVisible = true
    }

    override fun disableProgressBar() {
        binding.progressBar.isVisible = false
    }

    companion object {
        val ID = "id"

        fun intentToEnterActivity(context: Context): Intent {
            return Intent(context, EnterActivity::class.java)
        }

        fun intentToMainActivity(context: Context, id: Int): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(SignInActivity.ID, id)
            }
        }

    }
}