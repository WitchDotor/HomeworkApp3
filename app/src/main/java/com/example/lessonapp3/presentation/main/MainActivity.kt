package com.example.lessonapp3.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lessonapp3.R
import com.example.lessonapp3.presentation.enter.EnterActivity
import com.example.lessonapp3.UserService
import com.example.lessonapp3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainObject.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainObject.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this, UserService())
        presenter.userInitialization(intent.getIntExtra(ID, 0))

        binding.btnSigninOut.setOnClickListener {
            presenter.signOut()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun navigateToEnterActivity() {
        val intent = Intent(this, EnterActivity::class.java)
        startActivity(intent)
    }

    override fun showWelcomText(userExist: Boolean, userName: String) {
        return if (userExist) {
            binding.tvWelcomeText.text = getString(R.string.you_are_welcome, userName)
        } else {
            binding.tvWelcomeText.text = getString(R.string.we_cant_find_you)
        }
    }


    companion object {
        val ID = "id"
    }
}
