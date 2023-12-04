package com.example.lessonapp3.presentation.signUp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.example.lessonapp3.R
import com.example.lessonapp3.UserService
import com.example.lessonapp3.databinding.ActivitySignUpBinding
import com.example.lessonapp3.presentation.enter.EnterActivity
import com.example.lessonapp3.presentation.jediEnter.JediActivity
import com.example.lessonapp3.presentation.main.MainActivity
import com.example.lessonapp3.presentation.signIn.SignInActivity


class SignUpActivity : AppCompatActivity(), SignUpObject.View {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var presenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SignUpPresenter(this, UserService())
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.etLogin.doAfterTextChanged {
            presenter.loginInput(it.toString())
        }
        binding.etName.doAfterTextChanged {
            presenter.nameInput(it.toString())
        }
        binding.etPassword.doAfterTextChanged {
            presenter.passwordInput(it.toString())
        }
        binding.etConfirmPassword.doAfterTextChanged {
            presenter.confirmPasswordInput(it.toString())
        }

        binding.btnSignUn.setOnClickListener {
            presenter.signUp()

        }

        binding.ibtnToEnterActivity.setOnClickListener {
            navigationToEnterActivity()
        }
    }

    override fun disableErrors() {
        binding.ilName.error = null
        binding.ilLogin.error = null
        binding.ilPassword.error = null
        binding.ilConfirmPassword.error = null
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun inavalidName() {
        binding.ilName.error = getString(R.string.invalid_name)
    }

    override fun invalidLogin() {
        binding.ilLogin.error = getString(R.string.invalid_login)
    }

    override fun invalidPassword() {
        binding.ilPassword.error = getString(R.string.short_password)
    }

    override fun invalidConfirmPassword() {
        binding.ilConfirmPassword.error = getString(R.string.passwords_are_different)
    }

    override fun userAlreadyExist() {
        binding.ilLogin.error=getString(R.string.user_already_exist)
    }

    override fun navigationToEnterActivity() {
        val intent = JediActivity.Companion.intentToEnterActivity(this)
        startActivity(intent)

    }

    override fun navigationToMainActivity(id: Int) {
        val intent = JediActivity.Companion.intentToMainActivity(this, id)
        startActivity(intent)
    }


    companion object SignUpCompanion {
        private const val ID = "id"

        fun intentToEnterActivity(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }

        fun intentToMainActivity(context: Context, id: Int): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(SignInActivity.ID, id)
            }
        }

    }
}


