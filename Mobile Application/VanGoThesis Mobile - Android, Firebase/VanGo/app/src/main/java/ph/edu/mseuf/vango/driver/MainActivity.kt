package ph.edu.vangodriver

import android.Manifest
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ph.edu.mseuf.vango.R
import ph.edu.mseuf.vango.databinding.ActivityMainBinding
import ph.edu.mseuf.vango.databinding.BottomAddrouteBinding
import ph.edu.mseuf.vango.databinding.SuccessAddrouteBinding
import ph.edu.mseuf.vango.driver.AtimonanSeat
import ph.edu.mseuf.vango.driver.DriverViewVanList
import ph.edu.mseuf.vango.driver.MaubanSeat
import ph.edu.mseuf.vango.driver.SanAndresSeat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var mapFragment : SupportMapFragment
    private lateinit var googleMap: GoogleMap
    private var currentMarker: Marker? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var lastLocation : Location
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    val TAG = "DriverEditRoute"


    companion object{
        private const val LOCATION_PERMISSION_REQUEST = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



    }

    override fun onStart() {
        super.onStart()


        // Google Map Fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map1) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        //Terminal Reference
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        val databaseSM = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
        databaseSM.child(userId).get().addOnSuccessListener {
            if (it.exists()) {

                mDatabase = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
                val ref = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
                val addRouteNav = binding.addRoutebtn

                ref.addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val uid = binding.accountLogin.text.toString()
                        val check = snapshot.child(uid).child("id").value
                        if (check == "1") {

                            addRouteNav.isEnabled = false
                        } else if (check == "0") {
                            addRouteNav.isEnabled = true
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

            }
        }

        val databaseTalipapa = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")
        databaseTalipapa.child(userId).get().addOnSuccessListener {
            if (it.exists()) {
                mDatabase = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")
                val ref = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")
                val addRouteNav = binding.addRoutebtn

                ref.addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val uid = binding.accountLogin.text.toString()
                        val check = snapshot.child(uid).child("id").value
                        if (check == "1") {

                            addRouteNav.isEnabled = false
                        } else if (check == "0") {
                            addRouteNav.isEnabled = true
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })



            }
        }

        val databasePacific = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
        databasePacific.child(userId).get().addOnSuccessListener {
            if (it.exists()) {
                mDatabase = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
                val ref = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
                val addRouteNav = binding.addRoutebtn

                ref.addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val uid = binding.accountLogin.text.toString()
                        val check = snapshot.child(uid).child("id").value
                        if (check == "1") {

                            addRouteNav.isEnabled = false
                        } else if (check == "0") {
                            addRouteNav.isEnabled = true
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

            }
        }

        val databaseGrand = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
        databaseGrand.child(userId).get().addOnSuccessListener {
            if (it.exists()) {
                mDatabase = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
                val ref = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
                val addRouteNav = binding.addRoutebtn

                ref.addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val uid = binding.accountLogin.text.toString()
                        val check = snapshot.child(uid).child("id").value
                        if (check == "1") {

                            addRouteNav.isEnabled = false
                        } else if (check == "0") {
                            addRouteNav.isEnabled = true
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

            }
        }


        //driver Account Login
        val driverUsernameID = intent.getStringExtra("user_id")
        binding.accountLogin.text = driverUsernameID

        binding.accountLogin.text = FirebaseAuth.getInstance().currentUser!!.uid
        binding.accountLogin.visibility = View.GONE

        //driver emailAccount
        val driverEmail = intent.getStringExtra("user_account")
        binding.emailLogin.text = driverEmail

        binding.emailLogin.text = FirebaseAuth.getInstance().currentUser!!.email.toString()
        binding.emailLogin.visibility = View.GONE


        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_home -> {
                    var intent = Intent(this@MainActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("user_id", binding.accountLogin.text.toString())
                    intent.putExtra("user_account", binding.emailLogin.text.toString())
                    startActivity(intent)
                }
                R.id.ic_profile -> {
                    val user = binding.accountLogin.text.toString()
                    var intent = Intent(this@MainActivity, DriverProfile::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("user_id", user)
                    intent.putExtra("user_account", binding.emailLogin.text.toString())
                    startActivity(intent)
                }

            }
            true
        }

        // Retrieve FCM Number
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            binding.fcmNumber.text = task.result
        }

        val addRouteNav = binding.addRoutebtn
        addRouteNav.setOnClickListener {

            addRouteBottom()

        }


    }
    // Add Route Nav


    private fun addRouteBottom() {
        val bottomSheetView = BottomAddrouteBinding.inflate(layoutInflater)

        val bottomSheetDialog =
            BottomSheetDialog(this@MainActivity, R.style.Theme_Design_BottomSheetDialog)

        bottomSheetDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val uid = binding.accountLogin.text.toString()
        mDatabase = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
        mDatabase.child(uid).get().addOnSuccessListener {

            if (it.exists()) {

                val terminals = it.child("terminal").value
                val name = it.child("fullName").value
                val number = it.child("phoneNum").value
                val vanModel = it.child("vanModel").value
                val plateNumber = it.child("plateNumber").value
                val vanCapacity = it.child("vanCapacity").value
                val destination = it.child("destination").value

                bottomSheetView.textInputTerminal.setText(terminals.toString())
                bottomSheetView.textInputDestination.setText(destination.toString())
                bottomSheetView.textInputDriversName.setText(name.toString())
                bottomSheetView.textInputDriversPhoneNum.setText(number.toString())
                bottomSheetView.textInputCapacity.setText(vanCapacity.toString())
                bottomSheetView.textInputModelVan.setText(vanModel.toString())
                bottomSheetView.textInputPlateNumber.setText(plateNumber.toString())

                bottomSheetView.textInputTerminal.isEnabled = false
                bottomSheetView.textInputDestination.isEnabled = false
                bottomSheetView.textInputDriversName.isEnabled = false
                bottomSheetView.textInputDriversPhoneNum.isEnabled = false
                bottomSheetView.textInputCapacity.isEnabled = false
                bottomSheetView.textInputModelVan.isEnabled = false
                bottomSheetView.textInputPlateNumber.isEnabled = false
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Username doest not exists",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.addOnFailureListener {
            Toast.makeText(
                this@MainActivity,
                "Failed",
                Toast.LENGTH_SHORT
            ).show()
        }

        bottomSheetView.timePicker.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener =
                TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                    cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    cal.set(Calendar.MINUTE, minute)

                    bottomSheetView.textInputTimeArrival.text =
                        SimpleDateFormat("HH:mm").format(cal.time)
                }

            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                false
            ).show()
        }

        bottomSheetView.btnAdd.setOnClickListener {

            //SM Terminal
            if (bottomSheetView.textInputTerminal.text.toString() == "SM City Lucena Terminal") {

                val terminalRoute = bottomSheetView.textInputTerminal.text.toString()
                val destination = bottomSheetView.textInputDestination.text.toString()
                val driverName = bottomSheetView.textInputDriversName.text.toString()
                val arrivalTime = bottomSheetView.textInputTimeArrival.text.toString()
                val fareRate = bottomSheetView.textInputFareRate.text.toString()
                val capacityVan = bottomSheetView.textInputCapacity.text.toString()
                val vanModel = bottomSheetView.textInputModelVan.text.toString()
                val plateNumber = bottomSheetView.textInputPlateNumber.text.toString()
                val account = binding.accountLogin.text.toString()
                val emailAccount = binding.emailLogin.text.toString()
                val fcmNumber = binding.fcmNumber.text.toString()

                if (terminalRoute.trim()
                        .isEmpty() && driverName.trim()
                        .isEmpty()
                    && arrivalTime.trim().isEmpty() && fareRate.trim()
                        .isEmpty() && capacityVan.trim().isEmpty()
                    && vanModel.trim().isEmpty() && plateNumber.trim()
                        .isEmpty() && account.trim().isEmpty()
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Fill All the Data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val ref = FirebaseDatabase.getInstance().getReference("smTerminal")
                val routeId: String? = ref.push().key

                val route = routeId?.let { it1 ->
                    SaveAddRoute(
                        it1,
                        terminalRoute,
                        destination,
                        driverName,
                        arrivalTime,
                        fareRate,
                        capacityVan,
                        vanModel,
                        plateNumber,
                        account,
                        emailAccount,
                        fcmNumber
                    )
                }
                if (routeId != null) {
                    ref.child(routeId).setValue(route).addOnCompleteListener {

                        if(destination == "Tayabas City"){
                            val tayabasSeatA1 = "Available"
                            val tayabasSeatA2 = "Available"
                            val tayabasSeatA3 = "Available"
                            val tayabasSeatA4 = "Available"
                            val tayabasSeatB1 = "Available"

                            val seats = TayabasSeat(tayabasSeatA1, tayabasSeatA2, tayabasSeatA3, tayabasSeatA4, tayabasSeatB1)
                            ref.child(routeId).child("Seats").setValue(seats)

                            Toast.makeText(
                                this@MainActivity,
                                "The route is added to the terminal ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        if(destination == "Mauban City"){
                            val maubanSeatA1 = "Available"
                            val maubanSeatA2 = "Available"
                            val maubanSeatA3 = "Available"
                            val maubanSeatA4 = "Available"
                            val maubanSeatB1 = "Available"

                            val seats = MaubanSeat(maubanSeatA1, maubanSeatA2, maubanSeatA3, maubanSeatA4, maubanSeatB1)
                            ref.child(routeId).child("Seats").setValue(seats)

                            Toast.makeText(
                                this@MainActivity,
                                "The route is added to the terminal ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        if(destination == "Atimonan City"){
                            val atimonanSeatA1 = "Available"
                            val atimonanSeatA2 = "Available"
                            val atimonanSeatA3 = "Available"
                            val atimonanSeatA4 = "Available"
                            val atimonanSeatB1 = "Available"

                            val seats = AtimonanSeat(atimonanSeatA1, atimonanSeatA2, atimonanSeatA3, atimonanSeatA4, atimonanSeatB1)
                            ref.child(routeId).child("Seats").setValue(seats)

                            Toast.makeText(
                                this@MainActivity,
                                "The route is added to the terminal ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        if(destination == "San Andres City"){
                            val sanAndresSeatA1 = "Available"
                            val sanAndresSeatA2 = "Available"
                            val sanAndresSeatA3 = "Available"
                            val sanAndresSeatA4 = "Available"
                            val sanAndresSeatB1 = "Available"

                            val seats = SanAndresSeat(sanAndresSeatA1, sanAndresSeatA2, sanAndresSeatA3, sanAndresSeatA4, sanAndresSeatB1)
                            ref.child(routeId).child("Seats").setValue(seats)

                            Toast.makeText(
                                this@MainActivity,
                                "The route is added to the terminal ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }




                        val mDialogView = SuccessAddrouteBinding.inflate(layoutInflater)
                        val mBuilder =
                            AlertDialog.Builder(this@MainActivity).setView(mDialogView.root)

                        val alert = mBuilder.show()
                        alert.window?.setBackgroundDrawableResource(android.R.color.transparent)
                        alert.setCanceledOnTouchOutside(true)
                        mDialogView.terminal.text = terminalRoute
                        mDialogView.destination.text = destination
                        mDialogView.driverName.text = driverName
                        mDialogView.arrivalTime.text = arrivalTime
                        mDialogView.fareRate.text = fareRate
                        mDialogView.capacityVan.text = capacityVan
                        mDialogView.vanModel.text = vanModel
                        mDialogView.plateNumber.text = plateNumber
                        mDialogView.account.text = emailAccount

                        mDialogView.btnOk.setOnClickListener {
                            val uid = binding.accountLogin.text.toString()
                            mDatabase.child(uid).child("id").setValue("1")

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                            val title = "Van Added to the terminal $terminalRoute"
                            val message =
                                "Destination: $destination \n Driver Name: $driverName"
                            if (title.isNotEmpty() && message.isNotEmpty()) {
                                PushNotification(
                                    NotificationData(title, message),
                                    TOPIC
                                ).also {
                                    sendNotification(it)
                                }

                            }

                            val intent = Intent(this@MainActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", binding.accountLogin.text.toString())
                            intent.putExtra("user_account", binding.emailLogin.text.toString())
                            startActivity(intent)
                        }
                    }
                }
            }

            //Pacific Terminal
            else if (bottomSheetView.textInputTerminal.text.toString() == "Pacific Lucena City Terminal") {
                val terminalRoute = bottomSheetView.textInputTerminal.text.toString()
                val destination = bottomSheetView.textInputDestination.text.toString()
                val driverName = bottomSheetView.textInputDriversName.text.toString()
                val arrivalTime = bottomSheetView.textInputTimeArrival.text.toString()
                val fareRate = bottomSheetView.textInputFareRate.text.toString()
                val capacityVan = bottomSheetView.textInputCapacity.text.toString()
                val vanModel = bottomSheetView.textInputModelVan.text.toString()
                val plateNumber = bottomSheetView.textInputPlateNumber.text.toString()
                val account = binding.accountLogin.text.toString()
                val emailAccount = binding.emailLogin.text.toString()
                val fcmNumber = binding.fcmNumber.text.toString()

                if (terminalRoute.trim()
                        .isEmpty() && destination == "Choose Destination" && driverName.trim()
                        .isEmpty()
                    && arrivalTime.trim().isEmpty() && fareRate.trim()
                        .isEmpty() && capacityVan.trim().isEmpty()
                    && vanModel.trim().isEmpty() && plateNumber.trim()
                        .isEmpty() && account.trim().isEmpty()
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Fill All the Data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val ref = FirebaseDatabase.getInstance().getReference("pacificTerminal")
                val routeId: String? = ref.push().key

                val route = routeId?.let { it1 ->
                    SaveAddRoute(
                        it1,
                        terminalRoute,
                        destination,
                        driverName,
                        arrivalTime,
                        fareRate,
                        capacityVan,
                        vanModel,
                        plateNumber,
                        account,
                        emailAccount,
                        fcmNumber
                    )
                }
                if (routeId != null) {
                    ref.child(routeId).setValue(route).addOnCompleteListener {

                        val seatA1 = "Available"
                        val seatA2 = "Available"
                        val seatA3 = "Available"
                        val seatA4 = "Available"
                        val seatB1 = "Available"

                        val seats = TayabasSeat(seatA1, seatA2, seatA3, seatA4, seatB1)
                        ref.child(routeId).child("Seats").setValue(seats)

                        Toast.makeText(
                            this@MainActivity,
                            "The route is added to the terminal ",
                            Toast.LENGTH_SHORT
                        ).show()

                        val mDialogView = SuccessAddrouteBinding.inflate(layoutInflater)
                        val mBuilder =
                            AlertDialog.Builder(this@MainActivity).setView(mDialogView.root)

                        val alert = mBuilder.show()
                        alert.window?.setBackgroundDrawableResource(android.R.color.transparent)
                        alert.setCanceledOnTouchOutside(true)
                        mDialogView.terminal.text = terminalRoute
                        mDialogView.destination.text = destination
                        mDialogView.driverName.text = driverName
                        mDialogView.arrivalTime.text = arrivalTime
                        mDialogView.fareRate.text = fareRate
                        mDialogView.capacityVan.text = capacityVan
                        mDialogView.vanModel.text = vanModel
                        mDialogView.plateNumber.text = plateNumber
                        mDialogView.account.text = emailAccount

                        mDialogView.btnOk.setOnClickListener {

                            val uid = binding.accountLogin.text.toString()
                            mDatabase.child(uid).child("id").setValue("1")

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                            val title = "Van Added to the terminal $terminalRoute"
                            val message =
                                "Destination: $destination \n Driver Name: $driverName"
                            if (title.isNotEmpty() && message.isNotEmpty()) {
                                PushNotification(
                                    NotificationData(title, message),
                                    TOPIC
                                ).also {
                                    sendNotification(it)
                                }

                            }

                            val intent = Intent(this@MainActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", binding.accountLogin.text.toString())
                            intent.putExtra("user_account", binding.emailLogin.text.toString())
                            startActivity(intent)
                        }
                    }
                }
            }

            //Talipapa Terminal
            else if (bottomSheetView.textInputTerminal.text.toString() == "Ibabang Dupay Talipapa Lucena City Terminal") {
                val terminalRoute = bottomSheetView.textInputTerminal.text.toString()
                val destination = bottomSheetView.textInputDestination.text.toString()
                val driverName = bottomSheetView.textInputDriversName.text.toString()
                val arrivalTime = bottomSheetView.textInputTimeArrival.text.toString()
                val fareRate = bottomSheetView.textInputFareRate.text.toString()
                val capacityVan = bottomSheetView.textInputCapacity.text.toString()
                val vanModel = bottomSheetView.textInputModelVan.text.toString()
                val plateNumber = bottomSheetView.textInputPlateNumber.text.toString()
                val account = binding.accountLogin.text.toString()
                val emailAccount = binding.emailLogin.text.toString()
                val fcmNumber = binding.fcmNumber.text.toString()

                if (terminalRoute.trim()
                        .isEmpty() && destination == "Choose Destination" && driverName.trim()
                        .isEmpty()
                    && arrivalTime.trim().isEmpty() && fareRate.trim()
                        .isEmpty() && capacityVan.trim().isEmpty()
                    && vanModel.trim().isEmpty() && plateNumber.trim()
                        .isEmpty() && account.trim().isEmpty()
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Fill All the Data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val ref = FirebaseDatabase.getInstance().getReference("talipapaTerminal")
                val routeId: String? = ref.push().key

                val route = routeId?.let { it1 ->
                    SaveAddRoute(
                        it1,
                        terminalRoute,
                        destination,
                        driverName,
                        arrivalTime,
                        fareRate,
                        capacityVan,
                        vanModel,
                        plateNumber,
                        account,
                        emailAccount,
                        fcmNumber
                    )
                }
                if (routeId != null) {
                    ref.child(routeId).setValue(route).addOnCompleteListener {

                        val seatA1 = "Available"
                        val seatA2 = "Available"
                        val seatA3 = "Available"
                        val seatA4 = "Available"
                        val seatB1 = "Available"

                        val seats = TayabasSeat(seatA1, seatA2, seatA3, seatA4, seatB1)
                        ref.child(routeId).child("Seats").setValue(seats)

                        Toast.makeText(
                            this@MainActivity,
                            "The route is added to the terminal ",
                            Toast.LENGTH_SHORT
                        ).show()

                        val mDialogView = SuccessAddrouteBinding.inflate(layoutInflater)
                        val mBuilder =
                            AlertDialog.Builder(this@MainActivity).setView(mDialogView.root)

                        val alert = mBuilder.show()
                        alert.window?.setBackgroundDrawableResource(android.R.color.transparent)
                        alert.setCanceledOnTouchOutside(true)
                        mDialogView.terminal.text = terminalRoute
                        mDialogView.destination.text = destination
                        mDialogView.driverName.text = driverName
                        mDialogView.arrivalTime.text = arrivalTime
                        mDialogView.fareRate.text = fareRate
                        mDialogView.capacityVan.text = capacityVan
                        mDialogView.vanModel.text = vanModel
                        mDialogView.plateNumber.text = plateNumber
                        mDialogView.account.text = emailAccount

                        mDialogView.btnOk.setOnClickListener {

                            val uid = binding.accountLogin.text.toString()
                            mDatabase.child(uid).child("id").setValue("1")

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                            val title = "Van Added to the terminal $terminalRoute"
                            val message =
                                "Destination: $destination \n Driver Name: $driverName"
                            if (title.isNotEmpty() && message.isNotEmpty()) {
                                PushNotification(
                                    NotificationData(title, message),
                                    TOPIC
                                ).also {
                                    sendNotification(it)
                                }

                            }

                            val intent = Intent(this@MainActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", binding.accountLogin.text.toString())
                            intent.putExtra("user_account", binding.emailLogin.text.toString())
                            startActivity(intent)
                        }
                    }
                }
            }

            //Grand Terminal
            else if (bottomSheetView.textInputTerminal.text.toString() == "Grand Terminal Lucena City") {
                val terminalRoute = bottomSheetView.textInputTerminal.text.toString()
                val destination = bottomSheetView.textInputDestination.text.toString()
                val driverName = bottomSheetView.textInputDriversName.text.toString()
                val arrivalTime = bottomSheetView.textInputTimeArrival.text.toString()
                val fareRate = bottomSheetView.textInputFareRate.text.toString()
                val capacityVan = bottomSheetView.textInputCapacity.text.toString()
                val vanModel = bottomSheetView.textInputModelVan.text.toString()
                val plateNumber = bottomSheetView.textInputPlateNumber.text.toString()
                val account = binding.accountLogin.text.toString()
                val emailAccount = binding.emailLogin.text.toString()
                val fcmNumber = binding.fcmNumber.text.toString()

                if (terminalRoute.trim()
                        .isEmpty() && destination == "Choose Destination" && driverName.trim()
                        .isEmpty()
                    && arrivalTime.trim().isEmpty() && fareRate.trim()
                        .isEmpty() && capacityVan.trim().isEmpty()
                    && vanModel.trim().isEmpty() && plateNumber.trim()
                        .isEmpty() && account.trim().isEmpty()
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Fill All the Data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val ref = FirebaseDatabase.getInstance().getReference("grandTerminal")
                val routeId: String? = ref.push().key

                val route = routeId?.let { it1 ->
                    SaveAddRoute(
                        it1,
                        terminalRoute,
                        destination,
                        driverName,
                        arrivalTime,
                        fareRate,
                        capacityVan,
                        vanModel,
                        plateNumber,
                        account,
                        emailAccount,
                        fcmNumber
                    )
                }
                if (routeId != null) {
                    ref.child(routeId).setValue(route).addOnCompleteListener {


                        val seatA1 = "Available"
                        val seatA2 = "Available"
                        val seatA3 = "Available"
                        val seatA4 = "Available"
                        val seatB1 = "Available"

                        val seats = TayabasSeat(seatA1, seatA2, seatA3, seatA4, seatB1)
                        ref.child(routeId).child("Seats").setValue(seats)

                        Toast.makeText(
                            this@MainActivity,
                            "The route is added to the terminal ",
                            Toast.LENGTH_SHORT
                        ).show()

                        val mDialogView = SuccessAddrouteBinding.inflate(layoutInflater)
                        val mBuilder =
                            AlertDialog.Builder(this@MainActivity).setView(mDialogView.root)

                        val alert = mBuilder.show()
                        alert.window?.setBackgroundDrawableResource(android.R.color.transparent)

                        mDialogView.terminal.text = terminalRoute
                        mDialogView.destination.text = destination
                        mDialogView.driverName.text = driverName
                        mDialogView.arrivalTime.text = arrivalTime
                        mDialogView.fareRate.text = fareRate
                        mDialogView.capacityVan.text = capacityVan
                        mDialogView.vanModel.text = vanModel
                        mDialogView.plateNumber.text = plateNumber
                        mDialogView.account.text = emailAccount

                        mDialogView.btnOk.setOnClickListener {

                            val uid = binding.accountLogin.text.toString()
                            mDatabase.child(uid).child("id").setValue("1")

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                            val title = "Van Added to the terminal $terminalRoute"
                            val message =
                                "Destination: $destination \n Driver Name: $driverName"
                            if (title.isNotEmpty() && message.isNotEmpty()) {
                                PushNotification(
                                    NotificationData(title, message),
                                    TOPIC
                                ).also {
                                    sendNotification(it)
                                }

                            }

                            val intent = Intent(this@MainActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", binding.accountLogin.text.toString())
                            intent.putExtra("user_account", binding.emailLogin.text.toString())
                            startActivity(intent)
                        }
                    }
                }
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Please Fill All the Data",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        bottomSheetView.btnCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }



        bottomSheetDialog.setContentView(bottomSheetView.root)
        bottomSheetDialog.show()
    }

    //Send Notification
    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {

        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(TAG, response.errorBody().toString())
            }
        } catch(e: Exception) {
            Log.e(TAG, e.toString())
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

        googleMap.isMyLocationEnabled = true
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

                val title = marker.title.toString()

                if(currentMarker == smLucena){

                    val userAccount = intent.getStringExtra("user_account")
                    val arrivedID = intent.getStringExtra("arrived_id")
                    val intent = Intent(this@MainActivity, DriverViewVanList::class.java)
                    intent.putExtra("Title", title)
                    intent.putExtra("user_id", binding.accountLogin.text.toString())
                    intent.putExtra("user_account", userAccount)
                    intent.putExtra("arrived_id", arrivedID)
                    startActivity(intent)

                }
                else if(currentMarker == pacificLucena){

                    val userAccount = intent.getStringExtra("user_account")
                    val arrivedID = intent.getStringExtra("arrived_id")
                    val intent = Intent(this@MainActivity, DriverViewVanList::class.java)
                    intent.putExtra("Title", title)
                    intent.putExtra("user_id", binding.accountLogin.text.toString())
                    intent.putExtra("user_account", userAccount)
                    intent.putExtra("arrived_id", arrivedID)
                    startActivity(intent)

                }
                else if(currentMarker == talipapaLucena) {

                    val userAccount = intent.getStringExtra("user_account")
                    val arrivedID = intent.getStringExtra("arrived_id")
                    val intent = Intent(this@MainActivity, DriverViewVanList::class.java)
                    intent.putExtra("Title", title)
                    intent.putExtra("user_id", binding.accountLogin.text.toString())
                    intent.putExtra("user_account", userAccount)
                    intent.putExtra("arrived_id", arrivedID)
                    startActivity(intent)

                }
                else if(currentMarker == grandLucena) {

                    val userAccount = intent.getStringExtra("user_account")
                    val arrivedID = intent.getStringExtra("arrived_id")
                    val intent = Intent(this@MainActivity, DriverViewVanList::class.java)
                    intent.putExtra("Title", title)
                    intent.putExtra("user_id", binding.accountLogin.text.toString())
                    intent.putExtra("user_account", userAccount)
                    intent.putExtra("arrived_id", arrivedID)
                    startActivity(intent)

                }

                return false
            }
        })

        googleMap.setOnMapClickListener { currentMarker = null }
    }





}











