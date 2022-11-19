package com.viel.babycare

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.viel.babycare.databinding.ActivityUpdateProfileBinding
import com.viel.babycare.progress.RequestHelper

class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateProfileBinding
    private val PERMISSIONS = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    private lateinit var permissionsRequest: ActivityResultLauncher<Array<String>>
    private lateinit var mUri: Uri
    val mActivityResuldLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult>(){
            if (it.resultCode == RESULT_OK){
                val intent = it.data
                val uri = intent!!.data
                mUri = uri!!
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val source = ImageDecoder.createSource(this.contentResolver, uri!!)
                    val bitmap = ImageDecoder.decodeBitmap(source)
                    binding.ciAvatar.setImageBitmap(bitmap)
                } else {
                    val bitmap = MediaStore.Images.Media.getBitmap(
                        this.contentResolver,uri)
                    binding.ciAvatar.setImageBitmap(bitmap)
                }
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser
        user?.let {
            val userName = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            binding.edtUsernameUpdate.setText(userName)
            binding.edtEmailUpdate.setText(email)
            Glide.with(this).load(photoUrl).error(R.drawable.ic_user).into(binding.ciAvatar)
        }

        binding.imgBackUpdate.setOnClickListener {
            finish()
        }

        permissionsRequest = getPermissionsRequest()
        binding.ciAvatar.setOnClickListener {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                openGallery()
            }
            else{
                if (this.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED)
                {
                    openGallery()
                }else{
                    RequestHelper.requestPermissions(permissionsRequest,PERMISSIONS)
                }
            }
        }


        binding.btnUpdate.setOnClickListener {
            val email = binding.edtEmailUpdate.text.toString()
            val userName = binding.edtUsernameUpdate.text.toString()

            if (!email.contains('@',true)){
                Toast.makeText(this,"Please check your email !!!!",Toast.LENGTH_LONG).show()
            }else if (email == "" && userName == ""){
                Toast.makeText(this,"Please enter the blank infomation !!!",Toast.LENGTH_LONG).show()
            }else{
                onClickUpdateProfile()
                finish()
            }
        }
    }

    private fun getPermissionsRequest() = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        if (RequestHelper.isAllPermissionsGranted(PERMISSIONS,this)){
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        mActivityResuldLauncher.launch(Intent.createChooser(intent,"Select Photo"))
    }

    private fun onClickUpdateProfile() {
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = binding.edtUsernameUpdate.text.toString()
            photoUri = Uri.parse(mUri.toString())
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Update avatar success",Toast.LENGTH_LONG).show()
                }
            }
    }
}