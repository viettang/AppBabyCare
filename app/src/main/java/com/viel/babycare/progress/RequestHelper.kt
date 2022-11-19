package com.viel.babycare.progress

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class RequestHelper {
companion object {
    fun requestPermissions(
        request: ActivityResultLauncher<Array<String>>,
        permissions: Array<String>
    ) = request.launch(permissions)

    fun isAllPermissionsGranted(permissions: Array<String>, context: Context) = permissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }
}
}