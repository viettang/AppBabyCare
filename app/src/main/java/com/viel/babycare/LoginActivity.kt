package com.viel.babycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.viel.babycare.databinding.ActivityLoginBinding
import com.viel.babycare.db.DialogManager
import com.viel.babycare.dialog.WaitDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var database: DatabaseReference
    private val dialogManager = DialogManager(this)

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
            var check = true
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
                        WaitDialog.waitDialog(this)
                        Handler(Looper.getMainLooper()).postDelayed({
                            synData(email)
                            finishAffinity()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        },3000)
                    } else {
                        Toast.makeText(this, "Login Fail",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    private fun synData(email: String) {
        database = Firebase.database.getReference(email!!.replace(".",""))
        for (i in 0..dialogManager.getAll().size-1){
            val pathObject = i.toString()
            database.child(pathObject).setValue(dialogManager.getAll()[i])
        }
    }
}