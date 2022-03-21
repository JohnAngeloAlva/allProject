package ph.edu.vangodriver

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ph.edu.mseuf.vango.R
import ph.edu.mseuf.vango.databinding.*
import ph.edu.mseuf.vango.driver.DriverViewVanList

const val TOPIC = "/topics/myTopic"

class DriverEditRoute : AppCompatActivity() {
    private lateinit var binding: EditRouteBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var database: DatabaseReference
    lateinit var mDatabase: DatabaseReference
    private lateinit var database1: DatabaseReference
    private lateinit var database2: DatabaseReference
    val TAG = "DriverEditRoute"

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim)}
    private val rotateClose: Animation by lazy {AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim)}
    private val fromBottom: Animation by lazy {AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim)}
    private val toBottom: Animation by lazy {AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim)}

    private var clicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database1 = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
        database2 = FirebaseDatabase.getInstance().getReference("Users")
        binding.addBtn.setOnClickListener{
            onAddButtonClicked()
        }
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        val databaseSM = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
        databaseSM.child(userId).get().addOnSuccessListener {
            if(it.exists()) {

                mDatabase = FirebaseDatabase.getInstance().getReference("SmDriverProfile")


            }
        }

        val databaseTalipapa = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")
        databaseTalipapa.child(userId).get().addOnSuccessListener {
            if(it.exists()) {

                mDatabase = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")


            }
        }

        val databasePacific = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
        databasePacific.child(userId).get().addOnSuccessListener {
            if(it.exists()) {

                mDatabase = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")


            }
        }

        val databaseGrand = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
        databaseGrand.child(userId).get().addOnSuccessListener {
            if(it.exists()) {

                mDatabase = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")


            }
        }


        val terminal = intent.getStringExtra("vanTerminal")

        if (terminal == "SM Lucena City Terminal") {
            smTerminal(terminal)
        }
        else if (terminal == "Pacific Lucena City Terminal") {
            pacificTerminal(terminal)
        }
        else if (terminal == "Ibabang Dupay Talipapa Lucena City Terminal") {
            talipapaTerminal(terminal)
        }
        else if (terminal == "Grand Terminal Lucena City") {
            grandTerminal(terminal)
        }

        binding.btnArriveRoute.setOnClickListener {
            val routeID= intent.getStringExtra("route_id")
            val destinationArrived = binding.textInputDestination.text.toString()
            val driverNameArrived = binding.textInputDriversName.text.toString()
            val arrivalTimeArrived = binding.textInputTimeArrival.text.toString()
            val fareRateArrived = binding.textInputFareRate.text.toString()
            val capacityVanArrived = binding.textInputCapacity.text.toString()
            val vanModelArrived = binding.textInputModelVan.text.toString()
            val plateNumberArrived = binding.textInputPlateNumber.text.toString()
            val accountArrived = binding.accountID.text.toString()
            val emailAccountArrived = binding.accountEmail.text.toString()
            val terminalArrived = binding.terminal.text.toString()
            val arrivedID = intent.getStringExtra("arrived_id")

            val mDialogViewArrive = ArrivedRouteAlertBinding.inflate(layoutInflater)
            val mBuilderArrive = AlertDialog.Builder(this@DriverEditRoute).setView(mDialogViewArrive.root)

            val alertDialogArrive = mBuilderArrive .show()

            mDialogViewArrive.destination.text = destinationArrived
            mDialogViewArrive.driverName.text = driverNameArrived
            mDialogViewArrive.fareRate.text = fareRateArrived

            mDialogViewArrive.btnArrivedRoute.setOnClickListener {

                if (routeID != null) {

                    database.child(routeID).removeValue().addOnSuccessListener {

                        Toast.makeText(
                            this@DriverEditRoute,
                            "Successfully Arrived ",
                            Toast.LENGTH_SHORT
                        ).show()

                        val ref = FirebaseDatabase.getInstance().getReference("ArrivedVan")
                        val arrivedId: String? = ref.push().key
                        val profiles = arrivedId?.let { it1 -> ArrivedVan(it1, accountArrived, emailAccountArrived, terminalArrived, destinationArrived, driverNameArrived, arrivalTimeArrived, fareRateArrived, capacityVanArrived, vanModelArrived, plateNumberArrived) }
                        if (arrivedId != null) {
                            ref.child(accountArrived).child(arrivedId).setValue(profiles)
                                .addOnCompleteListener {
                                    Toast.makeText(
                                        this@DriverEditRoute,
                                        "Your Profile is Successfully Save ",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    mDatabase.child(accountArrived).child("id").setValue("0")

                                    database1.addValueEventListener(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            for (smTerminalSnapshot in snapshot.children) {
                                                val seatCheck = smTerminalSnapshot.child("destination").value
                                                if (seatCheck == "Tayabas City") {

                                                    database2.addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            for (userSeat in snapshot.children) {
                                                                val user = userSeat.child("seatID").value
                                                                if (user == "tayabasSeatA1") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                    Log.d("User: ", user.toString())
                                                                }
                                                                if (user == "tayabasSeatA2") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                    Log.d("User: ", user.toString())
                                                                }
                                                                if (user == "tayabasSeatA3") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                    Log.d("User: ", user.toString())
                                                                }
                                                                if (user == "tayabasSeatA4") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                    Log.d("User: ", user.toString())
                                                                }
                                                                if (user == "tayabasSeatB1") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                    Log.d("User: ", user.toString())
                                                                }
                                                            }
                                                        }

                                                        override fun onCancelled(error: DatabaseError) {
                                                            TODO("Not yet implemented")
                                                        }

                                                    })
                                                    Log.d("Seat: ", seatCheck.toString())
                                                }
                                                if (seatCheck == "Mauban City") {

                                                    database2.addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            for (userSeat in snapshot.children) {
                                                                val user = userSeat.child("seatID").value
                                                                if (user == "maubanSeatA1") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "maubanSeatA2") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "maubanSeatA3") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "maubanSeatA4") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "maubanSeatB1") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                            }
                                                        }

                                                        override fun onCancelled(error: DatabaseError) {
                                                            TODO("Not yet implemented")
                                                        }

                                                    })
                                                }

                                                if (seatCheck == "Atimonan City") {

                                                    database2.addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            for (userSeat in snapshot.children) {
                                                                val user = userSeat.child("seatID").value
                                                                if (user == "atimonanSeatA1") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "atimonanSeatA2") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "atimonanSeatA3") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "atimonanSeatA4") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "atimonanSeatB1") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                            }
                                                        }

                                                        override fun onCancelled(error: DatabaseError) {
                                                            TODO("Not yet implemented")
                                                        }

                                                    })
                                                }
                                                if (seatCheck == "San Andres City") {

                                                    database2.addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            for (userSeat in snapshot.children) {
                                                                val user = userSeat.child("seatID").value
                                                                if (user == "sanAndresSeatA1") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "sanAndresSeatA2") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "sanAndresSeatA3") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "sanAndresSeatA4") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                                if (user == "sanAndresSeatB1") {
                                                                    userSeat.ref.child("seatID")
                                                                        .setValue("0")
                                                                }
                                                            }
                                                        }

                                                        override fun onCancelled(error: DatabaseError) {
                                                            TODO("Not yet implemented")
                                                        }

                                                    })
                                                }
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })

                                    FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                                        val title = "Arrived van"
                                        val message = "The $destinationArrived destination has been arrived in the $terminalArrived"
                                        if(title.isNotEmpty() && message.isNotEmpty() ) {
                                            PushNotification(
                                                NotificationData(title, message),
                                                TOPIC
                                            ).also {
                                                sendNotification(it)
                                            }

                                        }

                                    alertDialogArrive.dismiss()
                                    val userAccount = intent.getStringExtra("user_account")
                                    val userID = intent.getStringExtra("user_id")

                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("arrived_id", arrivedId)
                                    intent.putExtra("arrived_ID", arrivedID)
                                    intent.putExtra("user_id", userID)
                                    intent.putExtra("user_account", userAccount)
                                    startActivity(intent)

                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        this@DriverEditRoute,
                                        "Your Profile is Failed to Save ",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }

                        }

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Failed to delete Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            mDialogViewArrive.btnCancel.setOnClickListener {
                alertDialogArrive.dismiss()
            }
        }

        binding.btnNotifyRoute.setOnClickListener {


            val mDialogViewSendSMS = DriverSendSmsBinding.inflate(layoutInflater)
            val mBuilderSendSMS = AlertDialog.Builder(this@DriverEditRoute).setView(mDialogViewSendSMS .root)

            val alertDialogSendSMS = mBuilderSendSMS.show()
            
            FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)


            database = FirebaseDatabase.getInstance().getReference("Users")
            database.addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (userSeat in snapshot.children) {
                        val user = userSeat.child("seatID").value
                        if (user == "tayabasSeatA1") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            TOPIC
                                        ).also {
                                            sendNotification(it)
                                        }

                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }


                        }
                        if (user == "tayabasSeatA2") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "tayabasSeatA3") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()
                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "tayabasSeatA4") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "tayabasSeatB1") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }

                        //Mauban Seat
                        if (user == "maubanSeatA1") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }


                        }
                        if (user == "maubanSeatA2") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "maubanSeatA3") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()
                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "maubanSeatA4") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "maubanSeatB1") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }

                        //Atimonan Seat
                        if (user == "atimonanSeatA1") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }


                        }
                        if (user == "atimonanSeatA2") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "atimonanSeatA3") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()
                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "atimonanSeatA4") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "atimonanSeatB1") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }

                        //SanAndres Seat
                        if (user == "sanAndresSeatA1") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }


                        }
                        if (user == "sanAndresSeatA2") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "sanAndresSeatA3") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()
                            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "sanAndresSeatA4") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()


                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message =
                                        "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if (title.isNotEmpty() && message.isNotEmpty()) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        }
                        if (user == "sanAndresSeatB1") {
                            val token = userSeat.child("fcmNumber").value.toString()
                            val name = userSeat.child("fullName").value.toString()

                                mDialogViewSendSMS.btnNotify.setOnClickListener {
                                    val title = "5 mins to Arrive the van"
                                    val message = "This message is to remind you that your reserved van is arriving after 5 mins"
                                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                                        PushNotification(
                                            NotificationData(title, message),
                                            token
                                        ).also {
                                            sendNotification(it)
                                        }
                                        Toast.makeText(
                                            this@DriverEditRoute,
                                            "Notified Commuter named $name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }

                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setClickable(clicked: Boolean) {
        if(!clicked){
            binding.btnEditRoute.isClickable = true
            binding.btnDeleteRoute.isClickable = true
            binding.btnArriveRoute.isClickable = true
            binding.btnNotifyRoute.isClickable = true
        }
        else{
            binding.btnEditRoute.isClickable = false
            binding.btnDeleteRoute.isClickable = false
            binding.btnArriveRoute.isClickable = false
            binding.btnNotifyRoute.isClickable = false
        }
    }

    private fun setVisibility(clicked: Boolean){
        if(!clicked){
            binding.btnEditRoute.show()
            binding.btnDeleteRoute.show()
            binding.btnArriveRoute.show()
            binding.btnNotifyRoute.show()

            binding.arriveText.isVisible = true
            binding.deleteText.isVisible = true
            binding.editText.isVisible = true
            binding.notifyText.isVisible = true

        }else{
            binding.btnEditRoute.hide()
            binding.btnDeleteRoute.hide()
            binding.btnArriveRoute.hide()
            binding.btnNotifyRoute.hide()

            binding.arriveText.isVisible = false
            binding.deleteText.isVisible = false
            binding.editText.isVisible = false
            binding.notifyText.isVisible = false
        }
    }
    private fun setAnimation(clicked: Boolean){
        if(!clicked){
            binding.btnEditRoute.startAnimation(fromBottom)
            binding.btnDeleteRoute.startAnimation(fromBottom)
            binding.btnArriveRoute.startAnimation(fromBottom)
            binding.btnNotifyRoute.startAnimation(fromBottom)
            binding.addBtn.startAnimation(rotateOpen)



        }else{
            binding.btnEditRoute.startAnimation(toBottom)
            binding.btnDeleteRoute.startAnimation(toBottom)
            binding.btnArriveRoute.startAnimation(toBottom)
            binding.btnNotifyRoute.startAnimation(toBottom)
            binding.addBtn.startAnimation(rotateClose)
        }
    }


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

    private fun grandTerminal(terminal: String) {

        val routeIDGrand = intent.getStringExtra("route_id")
        database = FirebaseDatabase.getInstance().getReference("grandTerminal")
        if (routeIDGrand != null) {
            database.child(routeIDGrand).get().addOnSuccessListener {

                if (it.exists()) {

                    val destination = it.child("destination").value
                    val name = it.child("driverName").value
                    val arrivalTime = it.child("arrivalTime").value
                    val fareRate = it.child("fareRate").value
                    val capacityVan = it.child("capacityVan").value
                    val vanModel = it.child("vanModel").value
                    val plateNumber = it.child("plateNumber").value
                    val account =  it.child("account").value
                    val emailAccount =  it.child("emailAccount").value
                    val terminals =  it.child("terminal").value
                    val fcm =  it.child("fcmNumber").value

                    binding.textInputDestination.text = destination.toString()
                    binding.textInputDriversName.text = name.toString()
                    binding.textInputTimeArrival.text = arrivalTime.toString()
                    binding.textInputFareRate.text = fareRate.toString()
                    binding.textInputCapacity.text = capacityVan.toString()
                    binding.textInputModelVan.text = vanModel.toString()
                    binding.textInputPlateNumber.text = plateNumber.toString()
                    binding.accountID.text = account.toString()
                    binding.accountEmail.text = emailAccount.toString()
                    binding.terminal.text = terminals.toString()
                    binding.fcmNumber.text = fcm.toString()

                } else {
                    Toast.makeText(
                        this@DriverEditRoute,
                        "Username doest not exists",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                Toast.makeText(
                    this@DriverEditRoute,
                    "Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.btnEditRoute.setOnClickListener {
                val driverName = binding.textInputDriversName.text.toString()
                val arrivalTime = binding.textInputTimeArrival.text.toString()
                val fareRate = binding.textInputFareRate.text.toString()
                val capacityVan = binding.textInputCapacity.text.toString()
                val vanModel = binding.textInputModelVan.text.toString()
                val plateNumber = binding.textInputPlateNumber.text.toString()

                val mDialogView = EditRouteAlertBinding.inflate(layoutInflater)
                val mBuilder = AlertDialog.Builder(this@DriverEditRoute).setView(mDialogView.root)

                val alertDialog = mBuilder.show()

                mDialogView.textInputDriversName.setText(driverName)
                mDialogView.textInputTimeArrival.setText(arrivalTime)
                mDialogView.textInputFareRate.setText(fareRate)
                mDialogView.textInputCapacity.setText(capacityVan)
                mDialogView.textInputModelVan.setText(vanModel)
                mDialogView.textInputPlateNumber.setText(plateNumber)


                mDialogView.btnEditRoute.setOnClickListener {

                    val driverNameEdited = mDialogView.textInputDriversName.text.toString()
                    val arrivalTimeEdited = mDialogView.textInputTimeArrival.text.toString()
                    val fareRateEdited = mDialogView.textInputFareRate.text.toString()
                    val capacityVanEdited = mDialogView.textInputCapacity.text.toString()
                    val vanModelEdited = mDialogView.textInputModelVan.text.toString()
                    val plateNumberEdited = mDialogView.textInputPlateNumber.text.toString()

                    val route = mapOf<String, String>(
                        "driverName" to driverNameEdited,
                        "arrivalTime" to arrivalTimeEdited,
                        "fareRate" to fareRateEdited,
                        "capacityVan" to capacityVanEdited,
                        "vanModel" to vanModelEdited,
                        "plateNumber" to plateNumberEdited,
                    )

                    database.child(routeIDGrand).updateChildren(route).addOnSuccessListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Successfully Updated Profile",
                            Toast.LENGTH_SHORT
                        ).show()
                        alertDialog.dismiss()

                        val userAccount = intent.getStringExtra("user_account")
                        val userID = intent.getStringExtra("user_id")

                        val intent = Intent(this@DriverEditRoute, DriverViewVanList::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("Title", terminal)
                        intent.putExtra("user_id", userID)
                        intent.putExtra("user_account", userAccount)
                        startActivity(intent)

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Failed to updated Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            binding.btnDeleteRoute.setOnClickListener {

                val destinationDelete = binding.textInputDestination.text.toString()
                val driverNameDelete = binding.textInputDriversName.text.toString()
                val fareRateDelete = binding.textInputFareRate.text.toString()

                val mDialogViewDelete = DeleteRouteAlertBinding.inflate(layoutInflater)
                val mBuilderDelete =
                    AlertDialog.Builder(this@DriverEditRoute).setView(mDialogViewDelete.root)

                val alertDialogDelete = mBuilderDelete.show()

                mDialogViewDelete.destination.text = destinationDelete
                mDialogViewDelete.driverName.text = driverNameDelete
                mDialogViewDelete.fareRate.text = fareRateDelete

                mDialogViewDelete.btnDeleteRoute.setOnClickListener {

                    database.child(routeIDGrand).removeValue().addOnSuccessListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Successfully to deleted the data",
                            Toast.LENGTH_SHORT
                        ).show()

                        alertDialogDelete.dismiss()

                        val accountArrived = binding.accountID.text.toString()

                        mDatabase.child(accountArrived).child("id").setValue("0")

                        val userAccount = intent.getStringExtra("user_account")
                        val userID = intent.getStringExtra("user_id")

                        val intent = Intent(this@DriverEditRoute, DriverViewVanList::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("Title", terminal)
                        intent.putExtra("user_id", userID)
                        intent.putExtra("user_account", userAccount)
                        startActivity(intent)

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Failed to delete Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                mDialogViewDelete.btnCancel.setOnClickListener {
                    alertDialogDelete.dismiss()
                }
            }
        }

    }

    private fun talipapaTerminal(terminal: String) {

        val routeIDTalipapa = intent.getStringExtra("route_id")
        database = FirebaseDatabase.getInstance().getReference("talipapaTerminal")
        if (routeIDTalipapa != null) {
            database.child(routeIDTalipapa).get().addOnSuccessListener {

                if (it.exists()) {

                    val destination = it.child("destination").value
                    val name = it.child("driverName").value
                    val arrivalTime = it.child("arrivalTime").value
                    val fareRate = it.child("fareRate").value
                    val capacityVan = it.child("capacityVan").value
                    val vanModel = it.child("vanModel").value
                    val plateNumber = it.child("plateNumber").value
                    val account =  it.child("account").value
                    val emailAccount =  it.child("emailAccount").value
                    val terminals =  it.child("terminal").value
                    val fcm =  it.child("fcmNumber").value

                    binding.textInputDestination.text = destination.toString()
                    binding.textInputDriversName.text = name.toString()
                    binding.textInputTimeArrival.text = arrivalTime.toString()
                    binding.textInputFareRate.text = fareRate.toString()
                    binding.textInputCapacity.text = capacityVan.toString()
                    binding.textInputModelVan.text = vanModel.toString()
                    binding.textInputPlateNumber.text = plateNumber.toString()
                    binding.accountID.text = account.toString()
                    binding.accountEmail.text = emailAccount.toString()
                    binding.terminal.text = terminals.toString()
                    binding.fcmNumber.text = fcm.toString()

                } else {
                    Toast.makeText(
                        this@DriverEditRoute,
                        "Username doest not exists",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                Toast.makeText(
                    this@DriverEditRoute,
                    "Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.btnEditRoute.setOnClickListener {
                val driverName = binding.textInputDriversName.text.toString()
                val arrivalTime = binding.textInputTimeArrival.text.toString()
                val fareRate = binding.textInputFareRate.text.toString()
                val capacityVan = binding.textInputCapacity.text.toString()
                val vanModel = binding.textInputModelVan.text.toString()
                val plateNumber = binding.textInputPlateNumber.text.toString()

                val mDialogView = EditRouteAlertBinding.inflate(layoutInflater)
                val mBuilder = AlertDialog.Builder(this@DriverEditRoute).setView(mDialogView.root)

                val alertDialog = mBuilder.show()

                mDialogView.textInputDriversName.setText(driverName)
                mDialogView.textInputTimeArrival.setText(arrivalTime)
                mDialogView.textInputFareRate.setText(fareRate)
                mDialogView.textInputCapacity.setText(capacityVan)
                mDialogView.textInputModelVan.setText(vanModel)
                mDialogView.textInputPlateNumber.setText(plateNumber)

                mDialogView.spinnerDestination.adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.destinations,
                    android.R.layout.simple_spinner_dropdown_item
                )

                mDialogView.btnEditRoute.setOnClickListener {


                    val driverNameEdited = mDialogView.textInputDriversName.text.toString()
                    val arrivalTimeEdited = mDialogView.textInputTimeArrival.text.toString()
                    val fareRateEdited = mDialogView.textInputFareRate.text.toString()
                    val capacityVanEdited = mDialogView.textInputCapacity.text.toString()
                    val vanModelEdited = mDialogView.textInputModelVan.text.toString()
                    val plateNumberEdited = mDialogView.textInputPlateNumber.text.toString()

                    val route = mapOf<String, String>(
                        "driverName" to driverNameEdited,
                        "arrivalTime" to arrivalTimeEdited,
                        "fareRate" to fareRateEdited,
                        "capacityVan" to capacityVanEdited,
                        "vanModel" to vanModelEdited,
                        "plateNumber" to plateNumberEdited,
                    )

                    database.child(routeIDTalipapa).updateChildren(route).addOnSuccessListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Successfully Updated Profile",
                            Toast.LENGTH_SHORT
                        ).show()
                        alertDialog.dismiss()

                        val userAccount = intent.getStringExtra("user_account")
                        val userID = intent.getStringExtra("user_id")

                        val intent = Intent(this@DriverEditRoute, DriverViewVanList::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("Title", terminal)
                        intent.putExtra("user_id", userID)
                        intent.putExtra("user_account", userAccount)
                        startActivity(intent)

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Failed to updated Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            binding.btnDeleteRoute.setOnClickListener {

                val destinationDelete = binding.textInputDestination.text.toString()
                val driverNameDelete = binding.textInputDriversName.text.toString()
                val fareRateDelete = binding.textInputFareRate.text.toString()

                val mDialogViewDelete = DeleteRouteAlertBinding.inflate(layoutInflater)
                val mBuilderDelete =
                    AlertDialog.Builder(this@DriverEditRoute).setView(mDialogViewDelete.root)

                val alertDialogDelete = mBuilderDelete.show()

                mDialogViewDelete.destination.text = destinationDelete
                mDialogViewDelete.driverName.text = driverNameDelete
                mDialogViewDelete.fareRate.text = fareRateDelete

                mDialogViewDelete.btnDeleteRoute.setOnClickListener {

                    database.child(routeIDTalipapa).removeValue().addOnSuccessListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Successfully to deleted the data",
                            Toast.LENGTH_SHORT
                        ).show()

                        alertDialogDelete.dismiss()

                        val accountArrived = binding.accountID.text.toString()

                        mDatabase.child(accountArrived).child("id").setValue("0")

                        val userAccount = intent.getStringExtra("user_account")
                        val userID = intent.getStringExtra("user_id")

                        val intent = Intent(this@DriverEditRoute, DriverViewVanList::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("Title", terminal)
                        intent.putExtra("user_id", userID)
                        intent.putExtra("user_account", userAccount)
                        startActivity(intent)

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Failed to delete Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                mDialogViewDelete.btnCancel.setOnClickListener {
                    alertDialogDelete.dismiss()
                }
            }
        }

    }

    private fun pacificTerminal(terminal: String) {

        val routeIDPacific = intent.getStringExtra("route_id")
        database = FirebaseDatabase.getInstance().getReference("pacificTerminal")
        if (routeIDPacific != null) {
            database.child(routeIDPacific).get().addOnSuccessListener {

                if (it.exists()) {

                    val destination = it.child("destination").value
                    val name = it.child("driverName").value
                    val arrivalTime = it.child("arrivalTime").value
                    val fareRate = it.child("fareRate").value
                    val capacityVan = it.child("capacityVan").value
                    val vanModel = it.child("vanModel").value
                    val plateNumber = it.child("plateNumber").value
                    val account =  it.child("account").value
                    val emailAccount =  it.child("emailAccount").value
                    val terminals =  it.child("terminal").value
                    val fcm =  it.child("fcmNumber").value

                    binding.textInputDestination.text = destination.toString()
                    binding.textInputDriversName.text = name.toString()
                    binding.textInputTimeArrival.text = arrivalTime.toString()
                    binding.textInputFareRate.text = fareRate.toString()
                    binding.textInputCapacity.text = capacityVan.toString()
                    binding.textInputModelVan.text = vanModel.toString()
                    binding.textInputPlateNumber.text = plateNumber.toString()
                    binding.accountID.text = account.toString()
                    binding.accountEmail.text = emailAccount.toString()
                    binding.terminal.text = terminals.toString()
                    binding.fcmNumber.text = fcm.toString()

                } else {
                    Toast.makeText(
                        this@DriverEditRoute,
                        "Username doest not exists",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                Toast.makeText(
                    this@DriverEditRoute,
                    "Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.btnEditRoute.setOnClickListener {
                val driverName = binding.textInputDriversName.text.toString()
                val arrivalTime = binding.textInputTimeArrival.text.toString()
                val fareRate = binding.textInputFareRate.text.toString()
                val capacityVan = binding.textInputCapacity.text.toString()
                val vanModel = binding.textInputModelVan.text.toString()
                val plateNumber = binding.textInputPlateNumber.text.toString()

                val mDialogView = EditRouteAlertBinding.inflate(layoutInflater)
                val mBuilder = AlertDialog.Builder(this@DriverEditRoute).setView(mDialogView.root)

                val alertDialog = mBuilder.show()

                mDialogView.textInputDriversName.setText(driverName)
                mDialogView.textInputTimeArrival.setText(arrivalTime)
                mDialogView.textInputFareRate.setText(fareRate)
                mDialogView.textInputCapacity.setText(capacityVan)
                mDialogView.textInputModelVan.setText(vanModel)
                mDialogView.textInputPlateNumber.setText(plateNumber)

                mDialogView.spinnerDestination.adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.destinations,
                    android.R.layout.simple_spinner_dropdown_item
                )

                mDialogView.btnEditRoute.setOnClickListener {

                    val driverNameEdited = mDialogView.textInputDriversName.text.toString()
                    val arrivalTimeEdited = mDialogView.textInputTimeArrival.text.toString()
                    val fareRateEdited = mDialogView.textInputFareRate.text.toString()
                    val capacityVanEdited = mDialogView.textInputCapacity.text.toString()
                    val vanModelEdited = mDialogView.textInputModelVan.text.toString()
                    val plateNumberEdited = mDialogView.textInputPlateNumber.text.toString()

                    val route = mapOf<String, String>(
                        "driverName" to driverNameEdited,
                        "arrivalTime" to arrivalTimeEdited,
                        "fareRate" to fareRateEdited,
                        "capacityVan" to capacityVanEdited,
                        "vanModel" to vanModelEdited,
                        "plateNumber" to plateNumberEdited,
                    )

                    database.child(routeIDPacific).updateChildren(route).addOnSuccessListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Successfully Updated Profile",
                            Toast.LENGTH_SHORT
                        ).show()
                        alertDialog.dismiss()

                        val userAccount = intent.getStringExtra("user_account")
                        val userID = intent.getStringExtra("user_id")

                        val intent = Intent(this@DriverEditRoute, DriverViewVanList::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("Title", terminal)
                        intent.putExtra("user_id", userID)
                        intent.putExtra("user_account", userAccount)
                        startActivity(intent)

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Failed to updated Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            binding.btnDeleteRoute.setOnClickListener {

                val destinationDelete = binding.textInputDestination.text.toString()
                val driverNameDelete = binding.textInputDriversName.text.toString()
                val fareRateDelete = binding.textInputFareRate.text.toString()

                val mDialogViewDelete = DeleteRouteAlertBinding.inflate(layoutInflater)
                val mBuilderDelete =
                    AlertDialog.Builder(this@DriverEditRoute).setView(mDialogViewDelete.root)

                val alertDialogDelete = mBuilderDelete.show()

                mDialogViewDelete.destination.text = destinationDelete
                mDialogViewDelete.driverName.text = driverNameDelete
                mDialogViewDelete.fareRate.text = fareRateDelete

                mDialogViewDelete.btnDeleteRoute.setOnClickListener {

                    database.child(routeIDPacific).removeValue().addOnSuccessListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Successfully to deleted the data",
                            Toast.LENGTH_SHORT
                        ).show()

                        alertDialogDelete.dismiss()

                        val accountArrived = binding.accountID.text.toString()

                        mDatabase.child(accountArrived).child("id").setValue("0")

                        val userAccount = intent.getStringExtra("user_account")
                        val userID = intent.getStringExtra("user_id")

                        val intent = Intent(this@DriverEditRoute, DriverViewVanList::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("Title", terminal)
                        intent.putExtra("user_id", userID)
                        intent.putExtra("user_account", userAccount)
                        startActivity(intent)

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Failed to delete Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                mDialogViewDelete.btnCancel.setOnClickListener {
                    alertDialogDelete.dismiss()
                }
            }


        }

    }

    private fun smTerminal(terminal : String) {

        val routeIDSM = intent.getStringExtra("route_id")
        database = FirebaseDatabase.getInstance().getReference("smTerminal")
        if (routeIDSM != null) {
            database.child(routeIDSM).get().addOnSuccessListener {

                if (it.exists()) {

                    val destination = it.child("destination").value
                    val name = it.child("driverName").value
                    val arrivalTime = it.child("arrivalTime").value
                    val fareRate = it.child("fareRate").value
                    val capacityVan = it.child("capacityVan").value
                    val vanModel = it.child("vanModel").value
                    val plateNumber = it.child("plateNumber").value
                    val account =  it.child("account").value
                    val emailAccount =  it.child("emailAccount").value
                    val terminals =  it.child("terminal").value
                    val fcm =  it.child("fcmNumber").value

                    binding.textInputDestination.text = destination.toString()
                    binding.textInputDriversName.text = name.toString()
                    binding.textInputTimeArrival.text = arrivalTime.toString()
                    binding.textInputFareRate.text = fareRate.toString()
                    binding.textInputCapacity.text = capacityVan.toString()
                    binding.textInputModelVan.text = vanModel.toString()
                    binding.textInputPlateNumber.text = plateNumber.toString()
                    binding.accountID.text = account.toString()
                    binding.accountEmail.text = emailAccount.toString()
                    binding.terminal.text = terminals.toString()
                    binding.fcmNumber.text = fcm.toString()

                } else {
                    Toast.makeText(
                        this@DriverEditRoute,
                        "Username doest not exists",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                Toast.makeText(
                    this@DriverEditRoute,
                    "Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.btnEditRoute.setOnClickListener {
                val driverName = binding.textInputDriversName.text.toString()
                val arrivalTime = binding.textInputTimeArrival.text.toString()
                val fareRate = binding.textInputFareRate.text.toString()
                val capacityVan = binding.textInputCapacity.text.toString()
                val vanModel = binding.textInputModelVan.text.toString()
                val plateNumber = binding.textInputPlateNumber.text.toString()

                val mDialogView = EditRouteAlertBinding.inflate(layoutInflater)
                val mBuilder = AlertDialog.Builder(this@DriverEditRoute).setView(mDialogView.root)

                val alertDialog = mBuilder.show()

                mDialogView.textInputDriversName.setText(driverName)
                mDialogView.textInputTimeArrival.setText(arrivalTime)
                mDialogView.textInputFareRate.setText(fareRate)
                mDialogView.textInputCapacity.setText(capacityVan)
                mDialogView.textInputModelVan.setText(vanModel)
                mDialogView.textInputPlateNumber.setText(plateNumber)

                mDialogView.spinnerDestination.adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.destinations,
                    android.R.layout.simple_spinner_dropdown_item
                )

                mDialogView.btnEditRoute.setOnClickListener {

                    val driverNameEdited = mDialogView.textInputDriversName.text.toString()
                    val arrivalTimeEdited = mDialogView.textInputTimeArrival.text.toString()
                    val fareRateEdited = mDialogView.textInputFareRate.text.toString()
                    val capacityVanEdited = mDialogView.textInputCapacity.text.toString()
                    val vanModelEdited = mDialogView.textInputModelVan.text.toString()
                    val plateNumberEdited = mDialogView.textInputPlateNumber.text.toString()

                    val route = mapOf<String, String>(
                        "driverName" to driverNameEdited,
                        "arrivalTime" to arrivalTimeEdited,
                        "fareRate" to fareRateEdited,
                        "capacityVan" to capacityVanEdited,
                        "vanModel" to vanModelEdited,
                        "plateNumber" to plateNumberEdited,
                    )

                    database.child(routeIDSM).updateChildren(route).addOnSuccessListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Successfully Updated Profile",
                            Toast.LENGTH_SHORT
                        ).show()
                            alertDialog.dismiss()

                        val userAccount = intent.getStringExtra("user_account")
                        val userID = intent.getStringExtra("user_id")

                        val intent = Intent(this@DriverEditRoute, DriverViewVanList::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("Title", terminal)
                        intent.putExtra("user_id", userID)
                        intent.putExtra("user_account", userAccount)
                        startActivity(intent)

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Failed to updated Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            binding.btnDeleteRoute.setOnClickListener {

                val destinationDelete = binding.textInputDestination.text.toString()
                val driverNameDelete = binding.textInputDriversName.text.toString()
                val fareRateDelete = binding.textInputFareRate.text.toString()

                val mDialogViewDelete = DeleteRouteAlertBinding.inflate(layoutInflater)
                val mBuilderDelete = AlertDialog.Builder(this@DriverEditRoute).setView(mDialogViewDelete.root)

                val alertDialogDelete = mBuilderDelete.show()

                mDialogViewDelete.destination.text = destinationDelete
                mDialogViewDelete.driverName.text = driverNameDelete
                mDialogViewDelete.fareRate.text = fareRateDelete

                mDialogViewDelete.btnDeleteRoute.setOnClickListener {

                    database.child(routeIDSM).removeValue().addOnSuccessListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Successfully to deleted the data",
                            Toast.LENGTH_SHORT
                        ).show()

                        alertDialogDelete.dismiss()
                        val accountArrived = binding.accountID.text.toString()

                        mDatabase.child(accountArrived).child("id").setValue("0")

                        val userAccount = intent.getStringExtra("user_account")
                        val userID = intent.getStringExtra("user_id")

                        val intent = Intent(this@DriverEditRoute, DriverViewVanList::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("Title", terminal)
                        intent.putExtra("user_id", userID)
                        intent.putExtra("user_account", userAccount)
                        startActivity(intent)

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@DriverEditRoute,
                            "Failed to delete Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                mDialogViewDelete.btnCancel.setOnClickListener {
                    alertDialogDelete.dismiss()
                }
            }



        }
    }

    override fun onBackPressed() {
        val terminal = intent.getStringExtra("vanTerminal")
        val userAccount = FirebaseAuth.getInstance().currentUser!!.email
        val userID = intent.getStringExtra("user_id")

        val intent = Intent(this@DriverEditRoute, DriverViewVanList::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("Title", terminal)
        intent.putExtra("user_id", userID)
        intent.putExtra("user_account", userAccount)
        startActivity(intent)
    }

}



