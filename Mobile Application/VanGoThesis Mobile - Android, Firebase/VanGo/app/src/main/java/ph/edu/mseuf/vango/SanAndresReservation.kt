package ph.edu.mseuf.vango

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ph.edu.mseuf.vango.databinding.ActivityAddReservationBinding
import ph.edu.mseuf.vango.databinding.ActivitySanAndresReservationBinding

class SanAndresReservation : AppCompatActivity() {

    private lateinit var binding: ActivitySanAndresReservationBinding
    private lateinit var database: DatabaseReference
    private lateinit var database1: DatabaseReference
    private lateinit var database2: DatabaseReference
    private lateinit var auth: FirebaseAuth

    val TAG = "Reservation"



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySanAndresReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance().getReference("Seats")
        database1 = FirebaseDatabase.getInstance().getReference("Users")
        database2 = FirebaseDatabase.getInstance().getReference("smTerminal")


        auth = FirebaseAuth.getInstance()

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            val res = task.result
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)



    }

    override fun onStart() {
        super.onStart()
        val driverName = intent.getStringExtra("driverName")
        val destination = intent.getStringExtra("destination")
        val fareRate = intent.getStringExtra("fare")


        binding.txtDriverName.text = driverName
        binding.txtDestination.text = destination
        binding.txtTransportationFee.text = fareRate
        binding.btnA1!!.setOnClickListener{
            seatA1()
        }
        binding.btnA2!!.setOnClickListener {
            seatA2()
        }
        binding.btnA3!!.setOnClickListener {
            seatA3()
        }
        binding.btnA4!!.setOnClickListener {
            seatA4()
        }
        binding.btnB1!!.setOnClickListener {
            seatB1()
        }
        binding.logOut!!.setOnClickListener{
            startActivity(Intent(this, CommuterProfiles::class.java))
        }




        database2.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val vanID = intent.getStringExtra("id")

                val btnSeat1 = binding.btnA1!!
                val btnSeat2 = binding.btnA2!!
                val btnSeat3 = binding.btnA3!!
                val btnSeat4 = binding.btnA4!!
                val btnSeat5 = binding.btnB1!!

                val seat1 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatA1").value
                val seat2 = snapshot.child(vanID).child("Seats").child("sanAndresSeatA2").value
                val seat3 = snapshot.child(vanID).child("Seats").child("sanAndresSeatA3").value
                val seat4 = snapshot.child(vanID).child("Seats").child("sanAndresSeatA4").value
                val seat5 = snapshot.child(vanID).child("Seats").child("sanAndresSeatB1").value



                database1.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.child(auth.currentUser!!.uid).child("seatID").value

                        btnSeat1.isEnabled = !(user == "0" && seat1 == "Reserved")
                        btnSeat2.isEnabled = !(user == "0" && seat2 == "Reserved")
                        btnSeat3.isEnabled = !(user == "0" && seat3 == "Reserved")
                        btnSeat4.isEnabled = !(user == "0" && seat4 == "Reserved")
                        btnSeat5.isEnabled = !(user == "0" && seat5 == "Reserved")



                        if(user == "sanAndresSeatA1"){
                            btnSeat2.isEnabled = false
                            btnSeat3.isEnabled = false
                            btnSeat4.isEnabled = false
                            btnSeat5.isEnabled = false
                        }

                        else if(user == "sanAndresSeatA2"){
                            btnSeat1.isEnabled = false
                            btnSeat3.isEnabled = false
                            btnSeat4.isEnabled = false
                            btnSeat5.isEnabled = false
                        }
                        else if(user == "sanAndresSeatA3"){
                            btnSeat1.isEnabled = false
                            btnSeat2.isEnabled = false
                            btnSeat4.isEnabled = false
                            btnSeat5.isEnabled = false
                        }
                        else if(user == "sanAndresSeatA4"){
                            btnSeat1.isEnabled = false
                            btnSeat2.isEnabled = false
                            btnSeat3.isEnabled = false
                            btnSeat5.isEnabled = false
                        }
                        else if(user == "sanAndresSeatB1"){
                            btnSeat1.isEnabled = false
                            btnSeat2.isEnabled = false
                            btnSeat3.isEnabled = false
                            btnSeat4.isEnabled = false
                        }

                        if(user == "atimonanSeatA1" || user == "atimonanSeatA2"
                            || user == "atimonanSeatA3" || user == "atimonanSeatA4"
                            || user == "atimonanSeatB1"){
                            btnSeat1.isEnabled = false
                            btnSeat2.isEnabled = false
                            btnSeat3.isEnabled = false
                            btnSeat4.isEnabled = false
                            btnSeat5.isEnabled = false
                        }

                        if(user == "tayabasSeatA1" || user == "tayabasSeatA2"
                            || user == "tayabasSeatA3" || user == "tayabasSeatA4"
                            || user == "tayabasSeatB1"){

                            btnSeat1.isEnabled = false
                            btnSeat2.isEnabled = false
                            btnSeat3.isEnabled = false
                            btnSeat4.isEnabled = false
                            btnSeat5.isEnabled = false

                        }

                        if(user == "maubanSeatA1" || user == "maubanSeatA2"
                            || user == "maubanSeatA3" || user == "maubanSeatA4"
                            || user == "maubanSeatB1"){

                            btnSeat1.isEnabled = false
                            btnSeat2.isEnabled = false
                            btnSeat3.isEnabled = false
                            btnSeat4.isEnabled = false
                            btnSeat5.isEnabled = false

                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
                if(seat1 == "Reserved"){
                    btnSeat1.setBackgroundColor(Color.parseColor("#BFA780"))
                }
                if(seat2 == "Reserved"){
                    btnSeat2.setBackgroundColor(Color.parseColor("#BFA780"))
                }
                if(seat3 == "Reserved"){
                    btnSeat3.setBackgroundColor(Color.parseColor("#BFA780"))
                }
                if(seat4 == "Reserved"){
                    btnSeat4.setBackgroundColor(Color.parseColor("#BFA780"))
                }
                if(seat5 == "Reserved"){
                    btnSeat5.setBackgroundColor(Color.parseColor("#BFA780"))
                }



            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun seatA1(){

        val vanID = intent.getStringExtra("id")
        val view = ActivityAddReservationBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)

        val btnAdd = view.btnAdd
        val btnDelete = view.btnDelete
        val btnCancel = view.btnCancel

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)

        database1.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.child(auth.currentUser!!.uid).child("seatID").value
                if(user == "0"){
                    btnDelete.isEnabled = false

                    btnAdd.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {

                                val seat1 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatA1").value
                                if(seat1 == "Available"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatA1").setValue("Reserved")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("sanAndresSeatA1")

                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue(vanID)

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat A1 has Been Reserved"
                                            val message = "$userName reserve a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat A1 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }


                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }

                }

                else{
                    btnDelete.isEnabled = true

                    btnDelete.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {
                                val seat1 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatA1").value
                                if(seat1 == "Reserved"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatA1").setValue("Available")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("0")
                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue("0")

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat A1 has Been Deleted"
                                            val message = "$userName deleted a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat A1 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }

                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun seatA2(){
        val vanID = intent.getStringExtra("id")
        val view = ActivityAddReservationBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)

        val btnAdd = view.btnAdd
        val btnDelete = view.btnDelete
        val btnCancel = view.btnCancel

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)

        database1.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.child(auth.currentUser!!.uid).child("seatID").value
                if(user == "0"){
                    btnDelete.isEnabled = false

                    btnAdd.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {

                                val seat2 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatA2").value
                                if(seat2 == "Available"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatA2").setValue("Reserved")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("sanAndresSeatA2")

                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue(vanID)

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat A2 has Been Reserved"
                                            val message = "$userName reserve a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat A2 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }


                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }

                }

                else{
                    btnDelete.isEnabled = true

                    btnDelete.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {
                                val seat2 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatA2").value
                                if(seat2 == "Reserved"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatA2").setValue("Available")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("0")
                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue("0")

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat A2 has Been Deleted"
                                            val message = "$userName deleted a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat A2 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }

                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun seatA3(){
        val vanID = intent.getStringExtra("id")
        val view = ActivityAddReservationBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)

        val btnAdd = view.btnAdd
        val btnDelete = view.btnDelete
        val btnCancel = view.btnCancel

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)

        database1.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.child(auth.currentUser!!.uid).child("seatID").value
                if(user == "0"){
                    btnDelete.isEnabled = false

                    btnAdd.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {

                                val seat3 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatA3").value
                                if(seat3 == "Available"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatA3").setValue("Reserved")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("sanAndresSeatA3")

                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue(vanID)

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat A3 has Been Reserved"
                                            val message = "$userName reserve a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat A3 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }


                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }

                }

                else{
                    btnDelete.isEnabled = true

                    btnDelete.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {
                                val seat3 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatA3").value
                                if(seat3 == "Reserved"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatA3").setValue("Available")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("0")
                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue("0")

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat A3 has Been Deleted"
                                            val message = "$userName deleted a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat A3 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }

                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun seatA4(){
        val vanID = intent.getStringExtra("id")
        val view = ActivityAddReservationBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)

        val btnAdd = view.btnAdd
        val btnDelete = view.btnDelete
        val btnCancel = view.btnCancel

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)

        database1.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.child(auth.currentUser!!.uid).child("seatID").value
                if(user == "0"){
                    btnDelete.isEnabled = false

                    btnAdd.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {

                                val seat4 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatA4").value
                                if(seat4 == "Available"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatA4").setValue("Reserved")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("sanAndresSeatA4")

                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue(vanID)

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat A4 has Been Reserved"
                                            val message = "$userName reserve a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat A4 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }


                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }

                }

                else{
                    btnDelete.isEnabled = true

                    btnDelete.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {
                                val seat4 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatA4").value
                                if(seat4 == "Reserved"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatA4").setValue("Available")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("0")
                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue("0")

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat A4 has Been Deleted"
                                            val message = "$userName deleted a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat A4 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }

                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun seatB1(){
        val vanID = intent.getStringExtra("id")
        val view = ActivityAddReservationBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)

        val btnAdd = view.btnAdd
        val btnDelete = view.btnDelete
        val btnCancel = view.btnCancel

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)

        database1.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.child(auth.currentUser!!.uid).child("seatID").value
                if(user == "0"){
                    btnDelete.isEnabled = false

                    btnAdd.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {

                                val seat5 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatB1").value
                                if(seat5 == "Available"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatB1").setValue("Reserved")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("sanAndresSeatB1")

                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue(vanID)

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat B1 has Been Reserved"
                                            val message = "$userName reserve a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat B1 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }


                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }

                }

                else{
                    btnDelete.isEnabled = true

                    btnDelete.setOnClickListener{

                        startActivity(Intent(this@SanAndresReservation, CommuterHome::class.java))
                        finish()

                        val eventListener: ValueEventListener = object : ValueEventListener{

                            override fun onDataChange(snapshot: DataSnapshot) {
                                val seat5 = snapshot.child(vanID!!).child("Seats").child("sanAndresSeatB1").value
                                if(seat5 == "Reserved"){

                                    database2.child(vanID).child("Seats").child("sanAndresSeatB1").setValue("Available")
                                    database1.child(auth.currentUser!!.uid).child("seatID").setValue("0")
                                    database1.child(auth.currentUser!!.uid).child("vanID").setValue("0")

                                    database1.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val fcmNum = intent.getStringExtra("fcmNumber")!!
                                            val userName = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                                            val title = "Seat B1 has Been Deleted"
                                            val message = "$userName deleted a seat"
                                            val recipientToken = "eZKDdrIpST-7JLOBbyoJA-:APA91bH77OiLRdAwZiongjnRBUrSftny7jql8nKZvndmT5DzhAUd7bk4WY2vhPPpG-IsWEu5AFtq1g9UETmfJv_BUUX_iN97M-rmjGF8ZN41316ZegQ5zW1yQ3dzEAoswr81JoZntgC8"
                                            PushNotification(
                                                NotificationData(title, message),
                                                fcmNum
                                            ).also {
                                                sendNotification(it)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                                    Toast.makeText(
                                        baseContext, "Seat B1 has been Selected",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                else {
                                    Toast.makeText(
                                        baseContext, "Seat is Reserved",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        database2.addListenerForSingleValueEvent(eventListener)
                    }

                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {

        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if (response.isSuccessful) {
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(TAG, response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }



    override fun onStop(){
        super.onStop()
        print("onStop")
    }
    override fun onPause(){
        super.onPause()
        print("onPause")
    }

    override fun onRestart() {
        super.onRestart()
        print("onRestart")
    }

    override fun onResume() {
        super.onResume()
        print("onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        print("onDestroy")
    }

    fun print(msg: String){
        Log.d("Activity State ", msg)
    }
}