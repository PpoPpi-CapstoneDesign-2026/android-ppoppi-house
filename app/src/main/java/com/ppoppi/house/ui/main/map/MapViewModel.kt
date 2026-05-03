package com.ppoppi.house.ui.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.HospitalItem
import com.ppoppi.house.domain.model.MapView
import com.ppoppi.house.domain.repository.HospitalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel
    @Inject
    constructor(
        private val hospitalRepository: HospitalRepository,
    ) : ViewModel() {
        private val _hospitals = MutableStateFlow<List<HospitalItem>>(emptyList())
        val hospitals: StateFlow<List<HospitalItem>> = _hospitals

        fun loadHospitals(
            lat: Double,
            lng: Double,
        ) {
            viewModelScope.launch {
                val delta = 0.009
                val mapView =
                    MapView(
                        bounds =
                            MapView.Bounds(
                                northeast = MapView.LatLng(lat + delta, lng + delta),
                                southwest = MapView.LatLng(lat - delta, lng - delta),
                            ),
                        zoom = 14,
                        center = MapView.Center(lat, lng),
                        limit = 20,
                    )
                runCatching { hospitalRepository.postHospitalsSearch(mapView) }
                    .onSuccess { _hospitals.value = it }
            }
        }
    }
