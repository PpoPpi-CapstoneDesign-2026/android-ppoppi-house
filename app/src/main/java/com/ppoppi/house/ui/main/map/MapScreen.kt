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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
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
            MapWithCurrentLocation(modifier = modifier)
        }

        locationPermissions.shouldShowRationale -> {
            PermissionRationaleCard(
                modifier = modifier,
                onConfirm = { locationPermissions.launchMultiplePermissionRequest() },
            )
        }

        else -> {
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

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ParamsComparedByRef")
@Composable
fun PetHospitalMap(
    userLocation: LatLng,
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel(),
) {
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(userLocation, 14f)
        }
    val hospitals by viewModel.hospitals.collectAsState()
    val selectedHospitalInfo by viewModel.selectedHospitalInfo.collectAsState()

    LaunchedEffect(userLocation) {
        viewModel.loadHospitals(userLocation.latitude, userLocation.longitude)
    }

    var showBottomSheet by remember { mutableStateOf(false) }
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
                state = MarkerState(position = LatLng(hospital.latitude, hospital.longitude)),
                title = hospital.name,
                snippet = "${hospital.distanceMeter}m",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
                onClick = {
                    showBottomSheet = true
                    viewModel.loadHospitalInfo(
                        hospitalId = hospital.hospitalId.toLong(),
                        centerLat = userLocation.latitude,
                        centerLng = userLocation.longitude,
                    )
                    scope.launch { sheetState.show() }
                    true
                },
            )
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                viewModel.clearSelectedHospitalInfo()
            },
            sheetState = sheetState,
        ) {
            if (selectedHospitalInfo != null) {
                VetHospitalBottomSheet(hospital = selectedHospitalInfo!!)
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
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
