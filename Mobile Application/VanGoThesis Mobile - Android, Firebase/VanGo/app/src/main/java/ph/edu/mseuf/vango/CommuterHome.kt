package ph.edu.mseuf.vango

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.mseuf.vango.databinding.*


class CommuterHome : AppCompatActivity(), OnMapReadyCallback {
    lateinit var mapFragment : SupportMapFragment
    private lateinit var googleMap: GoogleMap
    private var currentMarker: Marker? = null
    private lateinit var binding: ActivityCommuterHomeBinding
    private lateinit var lastLocation : Location
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    companion object{
        private const val LOCATION_PERMISSION_REQUEST = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommuterHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Google Map Fragment
        mapFragment = supportFragmentManager.findFragmentById(R.id.map1) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        //driver Account Login
        val driverUsernameID = intent.getStringExtra("user_id")
        binding.accountLogin.text = driverUsernameID
        binding.accountLogin.visibility = View.VISIBLE

        //driver emailAccount
        val driverEmail = intent.getStringExtra("user_account")
        binding.emailLogin.text = driverEmail
        binding.emailLogin.visibility = View.VISIBLE

        //Home Button
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.ic_dashbiard -> startActivity(Intent(this, CommuterDashboard::class.java))
                R.id.ic_home -> startActivity(Intent(this, CommuterHome::class.java))
                R.id.ic_profile -> startActivity(Intent(this, CommuterProfiles::class.java))

            }
            true
        }
        }



    override fun onMapReady(googleMap: GoogleMap) {

        this.googleMap = googleMap
        setUpMap()

    }

    //Current Location
    private fun setUpMap() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)


            return
        }
        googleMap.isMyLocationEnabled =true
        fusedLocationClient.lastLocation.addOnSuccessListener(this){ location ->

            if(location !=null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLong)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 15f))
            }
        }

    }

    //Marker Location
    private fun placeMarkerOnMap(currentLatLong: LatLng) {

        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("My Location")
        googleMap.addMarker(markerOptions)
        val smLucenaCity = LatLng(13.9408, 121.6245)
        val pacificLucenaCity = LatLng(13.9375, 121.6088)
        val talipapaLucenaCity = LatLng(13.9419, 121.6242)
        val grandLucenaCity = LatLng(13.9583, 121.6179)


        val smLucena = googleMap.addMarker(
            MarkerOptions().position(smLucenaCity).title("SM Lucena City Terminal")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        )
        val pacificLucena = googleMap.addMarker(
            MarkerOptions().position(pacificLucenaCity).title("Pacific Lucena City Terminal")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        )
        val talipapaLucena = googleMap.addMarker(
            MarkerOptions().position(talipapaLucenaCity)
                .title("Ibabang Dupay Talipapa Lucena City Terminal")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        )
        val grandLucena = googleMap.addMarker(
            MarkerOptions().position(grandLucenaCity).title("Grand Terminal Lucena City")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        )
        googleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this, R.raw.style_json
            )
        )

        googleMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                currentMarker = marker

                val title: String? = marker.title

                if(currentMarker == smLucena){

                    val intent = Intent(this@CommuterHome, CommuterViewVanList::class.java)
                    intent.putExtra("Title", title)
                    startActivity(intent)
                }

                else if(currentMarker == pacificLucena){
                    val mDialogView = MarkerMenuBinding.inflate(layoutInflater)
                    val mBuilder = AlertDialog.Builder(this@CommuterHome).setView(mDialogView.root)
                    mBuilder.show()

                    mDialogView.marker.text = title
                    mDialogView.addRoutebtn.setOnClickListener{
                        intent.putExtra("Title", title)
                        intent.putExtra("user_id", binding.accountLogin.text.toString())
                        intent.putExtra("user_account", binding.emailLogin.text.toString())
                        startActivity(intent)
                    }
                    mDialogView.vanList.setOnClickListener{
                        val userAccount = intent.getStringExtra("user_account")
                        val arrivedID = intent.getStringExtra("arrived_id")
                        val intent = Intent(this@CommuterHome, CommuterViewVanList::class.java)
                        intent.putExtra("Title", title)
                        intent.putExtra("user_id", binding.accountLogin.text.toString())
                        intent.putExtra("user_account", userAccount)
                        intent.putExtra("arrived_id", arrivedID)
                        startActivity(intent)
                    }
                }
                else if(currentMarker == talipapaLucena) {
                    val mDialogView = MarkerMenuBinding.inflate(layoutInflater)
                    val mBuilder =
                        AlertDialog.Builder(this@CommuterHome).setView(mDialogView.root)
                    mBuilder.show()

                    mDialogView.marker.text = title
                    mDialogView.addRoutebtn.setOnClickListener {
                        intent.putExtra("Title", title)
                        intent.putExtra("user_id", binding.accountLogin.text.toString())
                        intent.putExtra("user_account", binding.emailLogin.text.toString())
                        startActivity(intent)
                    }
                    mDialogView.vanList.setOnClickListener{
                        val userAccount = intent.getStringExtra("user_account")
                        val arrivedID = intent.getStringExtra("arrived_id")
                        val intent = Intent(this@CommuterHome, CommuterViewVanList::class.java)
                        intent.putExtra("Title", title)
                        intent.putExtra("user_id", binding.accountLogin.text.toString())
                        intent.putExtra("user_account", userAccount)
                        intent.putExtra("arrived_id", arrivedID)
                        startActivity(intent)
                    }
                }
                else if(currentMarker == grandLucena) {
                    val mDialogView = MarkerMenuBinding.inflate(layoutInflater)
                    val mBuilder =
                        AlertDialog.Builder(this@CommuterHome).setView(mDialogView.root)
                    mBuilder.show()

                    mDialogView.marker.text = title
                    mDialogView.addRoutebtn.setOnClickListener {
                        intent.putExtra("Title", title)
                        intent.putExtra("user_id", binding.accountLogin.text.toString())
                        intent.putExtra("user_account", binding.emailLogin.text.toString())
                        startActivity(intent)
                    }
                    mDialogView.vanList.setOnClickListener{
                        val userAccount = intent.getStringExtra("user_account")
                        val arrivedID = intent.getStringExtra("arrived_id")
                        val intent = Intent(this@CommuterHome, CommuterViewVanList::class.java)
                        intent.putExtra("Title", title)
                        intent.putExtra("user_id", binding.accountLogin.text.toString())
                        intent.putExtra("user_account", userAccount)
                        intent.putExtra("arrived_id", arrivedID)
                        startActivity(intent)
                    }
                }

                return false
            }
        })

        googleMap.setOnMapClickListener { currentMarker = null }
    }

}











