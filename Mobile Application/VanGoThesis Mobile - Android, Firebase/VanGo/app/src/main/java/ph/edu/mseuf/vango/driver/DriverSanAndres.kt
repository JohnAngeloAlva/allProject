package ph.edu.mseuf.vango.driver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.mseuf.vango.R
import ph.edu.mseuf.vango.databinding.ActivityDriverSanAndresBinding
import ph.edu.mseuf.vango.databinding.ActivityShowReserveBinding
import ph.edu.mseuf.vango.databinding.EditRouteLoginBinding
import ph.edu.vangodriver.DriverEditRoute
import ph.edu.vangodriver.MainActivity

class DriverSanAndres : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var database1: DatabaseReference
    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding : ActivityDriverSanAndresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDriverSanAndresBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.ic_home -> startActivity(Intent(this, MainActivity::class.java))
                //R.id.ic_profile -> startActivity(Intent(this, DriverProfile::class.java))

            }
            true
        }
        database1 = FirebaseDatabase.getInstance().getReference("smTerminal")
        database = FirebaseDatabase.getInstance().getReference("Users")
        binding.btnEdit.setOnClickListener{
            loginDriver()
        }
        binding.btnA1.setOnClickListener {
            seatA1()
        }

        binding.btnA2.setOnClickListener {
            seatA2()
        }

        binding.btnA3.setOnClickListener {
            seatA3()
        }

        binding.btnA4.setOnClickListener {
            seatA4()
        }

        binding.btnB1.setOnClickListener {
            seatB1()
        }
    }
    private fun loginDriver() {
        val userAccount = intent.getStringExtra("EmailAccount")

        val mDialogView = EditRouteLoginBinding.inflate(layoutInflater)
        val mBuilder = AlertDialog.Builder(this@DriverSanAndres).setView(mDialogView.root)

        val alertDialog = mBuilder.show()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mDialogView.userAccount.text = userAccount
        mDialogView.userAccount.visibility = View.GONE

        mDialogView.btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        mDialogView.btnLogin.setOnClickListener {

            if(mDialogView.textInputPassword.text.toString().isEmpty()){
                Toast.makeText(
                    this@DriverSanAndres,
                    "Please enter Password to continue",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                val username = mDialogView.userAccount.text.toString().trim{it <= ' '}
                val password = mDialogView.textInputPassword.text.toString().trim{it <= ' '}

                FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password)
                    .addOnCompleteListener(
                        OnCompleteListener { task ->

                            //If the signup is successfully done

                            if(task.isSuccessful){

                                Toast.makeText(
                                    this@DriverSanAndres,
                                    "You login successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val terminal = intent.getStringExtra("vanTerminal")
                                val userID = intent.getStringExtra("user_id")
                                val arrivedID = intent.getStringExtra("arrived_id")
                                val id = intent.getStringExtra("Id")

                                val intent = Intent(this@DriverSanAndres, DriverEditRoute::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("route_id", id)
                                intent.putExtra("vanTerminal", terminal)
                                intent.putExtra("user_id", userID)
                                intent.putExtra("user_account", userAccount)
                                intent.putExtra("arrived_id", arrivedID)
                                startActivity(intent)

                            }
                            else{

                                Toast.makeText(
                                    this@DriverSanAndres,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    )
            }
        }
    }
    fun seatA1(){
        val view = ActivityShowReserveBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)
        val del = view.btnDelete
        val cancel = view.btnCancel
        val close = view.btnClose
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshots in snapshot.children) {
                    val message = "No One Reserved"

                    val fName = userSnapshots.child("fullName").value.toString()
                    val seat = userSnapshots.child("seatID").value.toString()

                    if (seat != "sanAndresSeatA1") {
                        view.txtName.text = message
                        view.txtSeat.text = message
                    } else {
                        view.txtName.text = fName
                        view.txtSeat.text = seat
                    }

                    del.setOnClickListener {
                        dialog.dismiss()
                        database1.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (smTerminalSnapshot in snapshot.children) {
                                    val seatCheck = smTerminalSnapshot.child("Seats")
                                        .child("sanAndresSeatA1").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("sanAndresSeatA1").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
                                                    if (user == "sanAndresSeatA1") {
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

                    }

                    cancel.setOnClickListener {
                        dialog.dismiss()
                    }

                    close.setOnClickListener {
                        dialog.dismiss()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun seatA2(){
        val view = ActivityShowReserveBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)
        val del = view.btnDelete
        val cancel = view.btnCancel
        val close = view.btnClose
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshots in snapshot.children) {
                    val message = "No One Reserved"

                    val fName = userSnapshots.child("fullName").value.toString()
                    val seat = userSnapshots.child("seatID").value.toString()

                    if (seat != "sanAndresSeatA2") {
                        view.txtName.text = message
                        view.txtSeat.text = message
                    } else {
                        view.txtName.text = fName
                        view.txtSeat.text = seat
                    }

                    del.setOnClickListener {
                        dialog.dismiss()
                        database1.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (smTerminalSnapshot in snapshot.children) {
                                    val seatCheck = smTerminalSnapshot.child("Seats")
                                        .child("sanAndresSeatA2").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("sanAndresSeatA2").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
                                                    if (user == "sanAndresSeatA2") {
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

                    }

                    cancel.setOnClickListener {
                        dialog.dismiss()
                    }

                    close.setOnClickListener {
                        dialog.dismiss()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun seatA3(){
        val view = ActivityShowReserveBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)
        val del = view.btnDelete
        val cancel = view.btnCancel
        val close = view.btnClose
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshots in snapshot.children) {
                    val message = "No One Reserved"

                    val fName = userSnapshots.child("fullName").value.toString()
                    val seat = userSnapshots.child("seatID").value.toString()

                    if (seat != "sanAndresSeatA3") {
                        view.txtName.text = message
                        view.txtSeat.text = message
                    } else {
                        view.txtName.text = fName
                        view.txtSeat.text = seat
                    }

                    del.setOnClickListener {
                        dialog.dismiss()
                        database1.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (smTerminalSnapshot in snapshot.children) {
                                    val seatCheck = smTerminalSnapshot.child("Seats")
                                        .child("sanAndresSeatA3").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("sanAndresSeatA3").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
                                                    if (user == "sanAndresSeatA3") {
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

                    }

                    cancel.setOnClickListener {
                        dialog.dismiss()
                    }

                    close.setOnClickListener {
                        dialog.dismiss()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun seatA4(){
        val view = ActivityShowReserveBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)
        val del = view.btnDelete
        val cancel = view.btnCancel
        val close = view.btnClose
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshots in snapshot.children) {
                    val message = "No One Reserved"

                    val fName = userSnapshots.child("fullName").value.toString()
                    val seat = userSnapshots.child("seatID").value.toString()

                    if (seat != "sanAndresSeatA4") {
                        view.txtName.text = message
                        view.txtSeat.text = message
                    } else {
                        view.txtName.text = fName
                        view.txtSeat.text = seat
                    }

                    del.setOnClickListener {
                        dialog.dismiss()
                        database1.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (smTerminalSnapshot in snapshot.children) {
                                    val seatCheck = smTerminalSnapshot.child("Seats")
                                        .child("sanAndresSeatA4").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("sanAndresSeatA4").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
                                                    if (user == "sanAndresSeatA4") {
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

                    }

                    cancel.setOnClickListener {
                        dialog.dismiss()
                    }

                    close.setOnClickListener {
                        dialog.dismiss()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun seatB1(){
        val view = ActivityShowReserveBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)
        val del = view.btnDelete
        val cancel = view.btnCancel
        val close = view.btnClose
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshots in snapshot.children) {
                    val message = "No One Reserved"

                    val fName = userSnapshots.child("fullName").value.toString()
                    val seat = userSnapshots.child("seatID").value.toString()

                    if (seat != "sanAndresSeatB1") {
                        view.txtName.text = message
                        view.txtSeat.text = message
                    } else {
                        view.txtName.text = fName
                        view.txtSeat.text = seat
                    }

                    del.setOnClickListener {
                        dialog.dismiss()
                        database1.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (smTerminalSnapshot in snapshot.children) {
                                    val seatCheck = smTerminalSnapshot.child("Seats")
                                        .child("sanAndresSeatB1").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("sanAndresSeatB1").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
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

                    }

                    cancel.setOnClickListener {
                        dialog.dismiss()
                    }

                    close.setOnClickListener {
                        dialog.dismiss()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}