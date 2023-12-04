package com.example.lessonapp3.presentation.signIn

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.doAfterTextChanged
import com.example.lessonapp3.R
import com.example.lessonapp3.presentation.enter.EnterActivity
import com.example.lessonapp3.UserService
import com.example.lessonapp3.databinding.ActivitySignInBinding
import com.example.lessonapp3.presentation.main.MainActivity

class SignInActivity : AppCompatActivity(), SignInObject.View {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var presenter: SignInObject.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = SignInPresenter(this, UserService())

        binding.ibtnToEnterActivity.setOnClickListener {
            navigationToEnterActivity()
        }

        binding.etLogin.doAfterTextChanged {
            presenter.loginInput(it.toString())
        }

        binding.etPassword.doAfterTextChanged {
            presenter.passwordInput(it.toString())
        }
        binding.btnSignIn.setOnClickListener {
            presenter.signIn()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun disableErrors() {
        binding.ilLogin.error = null
        binding.ilPassword.error = null
    }

    override fun userNotFound() {
        binding.ilLogin.error = getString(R.string.user_did_not_found)
    }

    override fun invalidPAssword() {
        binding.ilPassword.error = getString(R.string.invalid_password)
    }

    override fun navigationToEnterActivity() {
        val intent = Companion.intentToEnterActivity(this)
        startActivity(intent)
    }

    override fun navigationToMainActivity(userId: Int) {
        val intent = Companion.intentToMainActivity(this, userId)
        startActivity(intent)
    }

    companion object {
        const val ID = "id"

        fun intentToEnterActivity(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }

        fun intentToMainActivity(context: Context, id: Int): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(ID, id)
            }
        }


    }
}
