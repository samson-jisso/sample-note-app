package com.example.samnotes.presentation

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.samnotes.presentation.navigation.NavigationGraph
import com.example.samnotes.ui.theme.SamNotesTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    private val permissions = listOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.MANAGE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val multiplePermissionsState =
                rememberMultiplePermissionsState(permissions = permissions)
            val result = multiplePermissionsState.permissions.all { permissionState ->
                permissionState.status == PermissionStatus.Granted
            }
            SamNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.primary
                ) {
                    if (result) {
                        Toast.makeText(this, "tnx", Toast.LENGTH_SHORT).show()
                    } else {
                        multiplePermissionsState.launchMultiplePermissionRequest()
                    }
                    NavigationGraph()
                }
            }
        }
    }

}