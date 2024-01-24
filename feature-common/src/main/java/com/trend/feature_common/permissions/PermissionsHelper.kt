package com.trend.feature_common.permissions

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat

class PermissionsHelper(val activity: Activity, val list: List<String>, val code: Int) {
    fun checkPermissions() {
        if(isPermissionsGranted() != PackageManager.PERMISSION_GRANTED) {
            showAlert()
        } else {
            Toast.makeText(activity, "Todos los permisos requeridos permitidos.", Toast.LENGTH_SHORT).show()
        }
    }

    fun isPermissionsGranted(): Int {
        // GRANTED = 0, DENIED = -1
        var counter = 0
        for(permission in list) {
            counter += ContextCompat.checkSelfPermission(activity, permission)
        }
        return counter
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Permiso(s) necesario(s)")
        builder.setMessage("Algunos permisos son de suma importancia para el correcto funcionamiento de la aplicación.")
        builder.setPositiveButton("OK", { dialog, which -> requestPermissions() })
        builder.setNeutralButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun requestPermissions() {
        val permission = deniedPermission()
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            // Show an explanation asynchronously
            Toast.makeText(activity, "Should show an explanation.", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(activity, list.toTypedArray(), code)
        }
    }

    private fun deniedPermission(): String {
        for (permission in list) {
            if (ContextCompat.checkSelfPermission(activity, permission)
                == PackageManager.PERMISSION_DENIED) return permission
        }
        return ""
    }

    fun processPermissionsResult(requestCode: Int, permissions: Array<String>,
                                 grantResults: IntArray): Boolean {
        var result = 0
        if (grantResults.isNotEmpty()) {
            for (item in grantResults) {
                result += item
            }
        }
        if (result == PackageManager.PERMISSION_GRANTED) return true
        return false
    }
}