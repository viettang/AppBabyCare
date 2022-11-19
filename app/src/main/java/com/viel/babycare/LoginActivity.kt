package com.viel.babycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.viel.babycare.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBackLogin.setOnClickListener {
            finish()
        }
        binding.tvSignUp.setOnClickListener {
            val intent:Intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnNewLogin.setOnClickListener {
            val email = binding.edtUsername.text.toString()
            val pass = binding.edtPassword.text.toString()

            acceptLogin(email,pass)
        }
    }

    private fun acceptLogin(email: String, pass: String) {

        val auth = Firebase.auth
        if (!email.contains('@',true)){
            Toast.makeText(this,"Please check your email",
                Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        finish()
                    } else {
                        Toast.makeText(this, "Login Fail",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}