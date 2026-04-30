package com.ppoppi.house.ui.main.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    val locationPermissions =
        rememberMultiplePermissionsState(
            permissions =
                listOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                ),
        )

    LaunchedEffect(Unit) {
        // 화면 진입 시 권한이 없으면 자동 요청
        if (!locationPermissions.allPermissionsGranted) {
            locationPermissions.launchMultiplePermissionRequest()
        }
    }

    when {
        locationPermissions.allPermissionsGranted -> {
            // ✅ 권한 있음 → 지도 표시 (다음 단계)
            MapWithCurrentLocation(modifier = modifier)
        }

        locationPermissions.shouldShowRationale -> {
            // ⚠️ 한 번 거절한 상태 → 왜 필요한지 설명
            PermissionRationaleCard(
                modifier = modifier,
                onConfirm = { locationPermissions.launchMultiplePermissionRequest() },
            )
        }

        else -> {
            // 🚫 영구 거절 → 설정으로 유도
            PermissionDeniedCard(modifier = modifier)
        }
    }
}

@Composable
private fun MapWithCurrentLocation(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }

    // FusedLocationProvider로 마지막 위치 요청
    LaunchedEffect(Unit) {
        val fusedClient = LocationServices.getFusedLocationProviderClient(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return@LaunchedEffect
        }
        fusedClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    currentLocation = LatLng(it.latitude, it.longitude)
                }
            }
    }

    if (currentLocation != null) {
        PetHospitalMap(userLocation = currentLocation!!, modifier = modifier)
    } else {
        // 위치 로딩 중
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(12.dp))
            Text("현재 위치를 가져오는 중...")
        }
    }
}

@Suppress("ParamsComparedByRef")
@Composable
fun PetHospitalMap(
    userLocation: LatLng,
    modifier: Modifier = Modifier,
) {
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(userLocation, 14f) // 줌 레벨 14 (동네 수준)
        }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties =
            MapProperties(
                isMyLocationEnabled = true, // 내 위치 파란 점 표시
                mapType = MapType.NORMAL,
            ),
        uiSettings =
            MapUiSettings(
                myLocationButtonEnabled = true, // 내 위치로 돌아가는 버튼
                zoomControlsEnabled = true,
            ),
    ) {
        // 현재 위치 마커
        Marker(
            state = MarkerState(position = userLocation),
            title = "현재 위치",
            snippet = "내 반려동물과 함께 있는 곳",
        )
    }
}

@Composable
private fun PermissionRationaleCard(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("📍 위치 권한이 필요해요", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "현재 위치를 기반으로 가까운 동물병원을\n빠르게 안내해 드리기 위해 사용됩니다.",
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onConfirm) { Text("권한 허용하기") }
    }
}

@Composable
private fun PermissionDeniedCard(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("위치 권한이 거절되었어요", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("설정 > 앱 > 뽀삐 > 권한에서 위치를 허용해 주세요.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // 앱 설정 화면으로 이동
            val intent =
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
            context.startActivity(intent)
        }) { Text("설정으로 이동") }
    }
}
