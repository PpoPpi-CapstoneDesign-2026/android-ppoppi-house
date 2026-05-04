package com.ppoppi.house.ui.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.HospitalInfo
import com.ppoppi.house.domain.model.HospitalItem
import com.ppoppi.house.domain.model.MapView
import com.ppoppi.house.domain.repository.HospitalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

        private val _selectedHospitalInfo = MutableStateFlow<HospitalInfo?>(null)
        val selectedHospitalInfo: StateFlow<HospitalInfo?> = _selectedHospitalInfo

        private var loadHospitalsJob: Job? = null

        fun loadHospitals(mapView: MapView, immediate: Boolean = false) {
            loadHospitalsJob?.cancel()
            loadHospitalsJob = viewModelScope.launch {
                if (!immediate) delay(SEARCH_DEBOUNCE_MS)
                runCatching { hospitalRepository.postHospitalsSearch(mapView) }
                    .onSuccess { _hospitals.value = it }
            }
        }

        fun loadHospitalInfo(
            hospitalId: Long,
            centerLat: Double,
            centerLng: Double,
        ) {
            _selectedHospitalInfo.value = null
            viewModelScope.launch {
                runCatching { hospitalRepository.getHospitalsInfo(hospitalId, centerLat, centerLng) }
                    .onSuccess { _selectedHospitalInfo.value = it }
            }
        }

        fun clearSelectedHospitalInfo() {
            _selectedHospitalInfo.value = null
        }

        companion object {
            private const val SEARCH_DEBOUNCE_MS = 300L
        }
    }
