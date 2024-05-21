package kaiyrzhan.de.core.permission

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kaiyrzhan.de.core.permission.PermissionUtils.findActivity
import okhttp3.Request


@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberPermissionState(
    permission: String,
    onPermissionShouldShowRationale: @Composable () -> Unit,
    onPermissionGranted: @Composable () -> Unit,
    onPermissionNotGranted: @Composable () -> Unit,
): PermissionState {
    val permissionState = rememberPermissionState(permission = permission)
    if (permissionState.status.isGranted) {
        onPermissionGranted()
    } else {
        if (permissionState.status.shouldShowRationale) {
            onPermissionShouldShowRationale()
        } else {
            permissionState.launchPermissionRequest()
        }
    }
    return permissionState
}

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberMultiplePermissions(
    activity: Activity?,
    permissions: List<String>,
    onPermissionsGranted: () -> Unit,
    onPermissionShouldShowRationale: () -> Unit,
): MultiplePermissionsState {
    val permissionsState = rememberMultiplePermissionsState(permissions)

    LaunchedEffect(permissionsState) {
        if (permissionsState.allPermissionsGranted) {
            onPermissionsGranted()
        } else {
            if (permissionsState.shouldShowRationale) {
                onPermissionShouldShowRationale()
            } else {
                permissionsState.launchMultiplePermissionRequest()
            }
        }
    }
    return permissionsState
}
