package com.example.lessonapp3.presentation.enter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lessonapp3.presentation.signUp.SignUpActivity
import com.example.lessonapp3.databinding.ActivityEnterBinding
import com.example.lessonapp3.presentation.jediEnter.JediActivity
import com.example.lessonapp3.presentation.signIn.SignInActivity

class EnterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this@EnterActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this@EnterActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUpJedi.setOnClickListener {
            val intent = Intent(this@EnterActivity, JediActivity::class.java)
            startActivity(intent)
        }
    }
}