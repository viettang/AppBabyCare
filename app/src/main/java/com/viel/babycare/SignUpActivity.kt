package com.viel.babycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.viel.babycare.databinding.ActivitySignUpBinding
import com.viel.babycare.db.DialogManager
import com.viel.babycare.dialog.WaitDialog
import com.viel.babycare.fragments.HomeFragment
import com.viel.babycare.model.DialogAction

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imgBackSignup.setOnClickListener {
            finish()
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.edtUsernameSignup.text.toString()
            val pass1 = binding.edtPasswordSignup.text.toString()
            val pass2 = binding.edtPasswordSignup2.text.toString()

            acceptSignUp(email,pass1,pass2)
        }
    }

    private fun acceptSignUp(email: String, pass1: String, pass2: String) {

        if (!email.contains('@',true)){
            Toast.makeText(this,"Please check your email !!!!",Toast.LENGTH_LONG).show()
        }else if (pass1 != pass2){
            Toast.makeText(this,"Please check your password !!!!",Toast.LENGTH_SHORT).show()
        }else{
            auth = Firebase.auth
            auth.createUserWithEmailAndPassword(email, pass2)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        WaitDialog.waitDialog(this)
                        Handler(Looper.getMainLooper()).postDelayed({
                         finish()
                        },3000)
                    } else {
                        Toast.makeText(this,"Sign Up fail",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}