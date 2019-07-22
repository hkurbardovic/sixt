package com.hariskurbardovic.sixt.map.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hariskurbardovic.sixt.R
import com.hariskurbardovic.sixt.database.models.Car
import com.hariskurbardovic.sixt.databinding.FragmentMapBinding
import com.hariskurbardovic.sixt.map.components.DaggerMapsComponent
import com.hariskurbardovic.sixt.map.modules.MapsModule
import com.hariskurbardovic.sixt.map.viewmodels.MapsViewModel
import com.hariskurbardovic.sixt.map.viewmodels.MapsViewModelFactory
import javax.inject.Inject

class MapFragment : BaseFragment(), OnMapReadyCallback {

    companion object {
        const val MY_LOCATION_REQUEST_CODE = 987

        const val MUNICH_LATITUDE = 48.1351
        const val MUNICH_LONGITUDE = 11.5820

        const val GOOGLE_MAP_ZOOM = 12f
    }

    @Inject
    lateinit var mapsViewModelFactory: MapsViewModelFactory

    private val mapsViewModel: MapsViewModel by viewModels {
        mapsViewModelFactory
    }

    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMapBinding.inflate(inflater, container, false)

        DaggerMapsComponent.builder().mapsModule(MapsModule()).build().inject(this)

        requestAccessFineLocationPermission()

        observeData()
        mapsViewModel.getCars()

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // move camera to Munich
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(MUNICH_LATITUDE, MUNICH_LONGITUDE), GOOGLE_MAP_ZOOM
            )
        )

        setMyLocationEnabled(googleMap)

        this.googleMap = googleMap
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.size == 1 &&
                permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                setMyLocationEnabled(googleMap)
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }

    private fun observeData() {
        mapsViewModel.carsResponse.observe(viewLifecycleOwner) {
            if (googleMap == null) return@observe

            for (car: Car in it) {
                val latitude = car.latitude
                val longitude = car.longitude
                if (latitude != 0.0 || longitude != 0.0) {
                    createMarker(
                        latitude,
                        longitude,
                        car.name,
                        car.make
                    )
                }
            }
        }

        mapsViewModel.exception.observe(viewLifecycleOwner) {
            handleNetworkError(it)
        }
    }

    private fun createMarker(
        latitude: Double,
        longitude: Double,
        title: String?,
        snippet: String?
    ) {
        googleMap?.addMarker(
            MarkerOptions()
                .position(LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
        )
    }

    private fun setMyLocationEnabled(googleMap: GoogleMap?) {
        val activity = activity
        activity?.let {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                googleMap?.isMyLocationEnabled = true
            }
        }
    }

    private fun requestAccessFineLocationPermission() {
        val activity = activity
        activity?.let {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                setMyLocationEnabled(googleMap)
            } else {
                // Permission is not granted
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_LOCATION_REQUEST_CODE
                )
            }
        }
    }
}