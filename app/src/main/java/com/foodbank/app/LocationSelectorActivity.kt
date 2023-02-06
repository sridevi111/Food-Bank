package com.foodbank.app
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


class LocationSelectorActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var searchBox: EditText
    private lateinit var selectLocationButton: Button
    private lateinit var chooseCurrentLocationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_selector)
        searchBox = findViewById(R.id.search_box)
        selectLocationButton = findViewById(R.id.btn_select_location)
        chooseCurrentLocationButton = findViewById(R.id.btn_choose_current_location)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        selectLocationButton.setOnClickListener {
            val searchText = searchBox.text.toString().trim()
            if (searchText.isEmpty()) {
                showAlert("Error", "Please add Location")
                return@setOnClickListener
            }
            // Code to search location on the map using searchText
        }

        chooseCurrentLocationButton.setOnClickListener {
            // Code to get the current location
            val address = "Current Location Address"
            val resultIntent = Intent()
            resultIntent.putExtra("location_address", address)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        // Code to set the initial location and zoom level of the map
    }

    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
}
