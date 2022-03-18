package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyApplication()

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val layer = GeoJsonLayer(mMap, R.raw.geojson, this)
        layer.features.forEach {
            it.polygonStyle = GeoJsonPolygonStyle().apply {
                fillColor = Color.parseColor("#33ff5e05")
                strokeColor = Color.parseColor("#ff5e05")
                strokeWidth = 2f
                isClickable = true
            }
        }

        layer.addLayerToMap()

        layer.setOnFeatureClickListener {
            Toast.makeText(this, "polygon clicked", Toast.LENGTH_LONG).show()
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(52.517420, 13.373707), 11.0f))
    }
}


