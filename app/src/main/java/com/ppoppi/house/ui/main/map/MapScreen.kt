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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ppoppi.house.domain.model.BusinessStatus
import com.ppoppi.house.domain.model.VetHospital
import com.ppoppi.house.ui.main.map.component.VetHospitalBottomSheet
import kotlinx.coroutines.launch

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
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(12.dp))
            Text("현재 위치를 가져오는 중...")
        }
    }
}

private fun dummyHospitals(center: LatLng): List<VetHospital> =
    listOf(
        VetHospital(
            name = "24시 광진 동물병원",
            businessStatus = BusinessStatus.OPEN,
            businessTime = "24시간 운영",
            location = LatLng(center.latitude + 0.005, center.longitude + 0.003),
            phone = "02-456-7890",
            distance = 615,
            address = "서울특별시 광진구 자양동 234-5",
        ),
        VetHospital(
            name = "성수 동물의료센터",
            businessStatus = BusinessStatus.OPEN,
            businessTime = "평일 09:00 - 20:00 / 토 09:00 - 15:00",
            location = LatLng(center.latitude - 0.003, center.longitude + 0.006),
            phone = "02-498-2211",
            distance = 624,
            address = "서울특별시 성동구 성수동1가 656-281",
        ),
        VetHospital(
            name = "한강 동물병원",
            businessStatus = BusinessStatus.OPEN,
            businessTime = "평일 10:00 - 19:00 / 토 10:00 - 17:00",
            location = LatLng(center.latitude + 0.007, center.longitude - 0.004),
            phone = "02-512-3344",
            distance = 853,
            address = "서울특별시 광진구 구의동 48-12",
        ),
        VetHospital(
            name = "굿프랜드 동물병원",
            businessStatus = BusinessStatus.CLOSED,
            businessTime = "평일 09:30 - 18:30 / 토 09:30 - 14:00",
            location = LatLng(center.latitude - 0.006, center.longitude - 0.002),
            phone = "02-467-5500",
            distance = 689,
            address = "서울특별시 성동구 행당동 312-7",
        ),
        VetHospital(
            name = "아이러브 동물병원",
            businessStatus = BusinessStatus.CLOSED,
            businessTime = "평일 10:00 - 19:30 / 토 10:00 - 16:00",
            location = LatLng(center.latitude + 0.002, center.longitude + 0.008),
            phone = "02-523-8899",
            distance = 738,
            address = "서울특별시 광진구 중곡동 175-3",
        ),
        VetHospital(
            name = "뚝섬 VIP 동물병원",
            businessStatus = BusinessStatus.OPEN,
            businessTime = "평일 09:00 - 21:00 / 토·일 10:00 - 18:00",
            location = LatLng(center.latitude - 0.004, center.longitude + 0.005),
            phone = "02-481-6677",
            distance = 625,
            address = "서울특별시 성동구 뚝섬로 425",
        ),
        VetHospital(
            name = "건대 동물메디컬센터",
            businessStatus = BusinessStatus.CLOSED,
            businessTime = "평일 09:00 - 18:00 / 토 09:00 - 13:00",
            location = LatLng(center.latitude + 0.009, center.longitude + 0.001),
            phone = "02-534-1234",
            distance = 1003,
            address = "서울특별시 광진구 화양동 49-2",
        ),
    )

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ParamsComparedByRef")
@Composable
fun PetHospitalMap(
    userLocation: LatLng,
    modifier: Modifier = Modifier,
) {
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(userLocation, 14f)
        }
    val hospitals = remember(userLocation) { dummyHospitals(userLocation) }

    var selectedHospital by remember { mutableStateOf<VetHospital?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties =
            MapProperties(
                isMyLocationEnabled = true,
                mapType = MapType.NORMAL,
            ),
        uiSettings =
            MapUiSettings(
                myLocationButtonEnabled = true,
                zoomControlsEnabled = true,
            ),
    ) {
        Marker(
            state = MarkerState(position = userLocation),
            title = "현재 위치",
            snippet = "내 반려동물과 함께 있는 곳",
        )

        hospitals.forEach { hospital ->
            Marker(
                state = MarkerState(position = hospital.location),
                title = hospital.name,
                snippet = hospital.phone,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
                onClick = {
                    selectedHospital = hospital
                    scope.launch { sheetState.show() }
                    true
                },
            )
        }
    }

    selectedHospital?.let { hospital ->
        ModalBottomSheet(
            onDismissRequest = { selectedHospital = null },
            sheetState = sheetState,
        ) {
            VetHospitalBottomSheet(hospital = hospital)
        }
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
            val intent =
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
            context.startActivity(intent)
        }) { Text("설정으로 이동") }
    }
}
