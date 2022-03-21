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
import ph.edu.mseuf.vango.databinding.ActivityDriverMaubanBinding
import ph.edu.mseuf.vango.databinding.ActivityShowReserveBinding
import ph.edu.mseuf.vango.databinding.EditRouteLoginBinding
import ph.edu.vangodriver.DriverEditRoute
import ph.edu.vangodriver.MainActivity

class DriverMauban : AppCompatActivity() {
    private lateinit var binding : ActivityDriverMaubanBinding
    private lateinit var database: DatabaseReference
    private lateinit var database1: DatabaseReference
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverMaubanBinding.inflate(layoutInflater)
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
        binding.btnD1.setOnClickListener {
            startActivity(Intent(this, DriverEditRoute::class.java))
        }

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
        val mBuilder = AlertDialog.Builder(this@DriverMauban).setView(mDialogView.root)

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
                    this@DriverMauban,
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
                                    this@DriverMauban,
                                    "You login successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val terminal = intent.getStringExtra("vanTerminal")
                                val userID = intent.getStringExtra("user_id")
                                val arrivedID = intent.getStringExtra("arrived_id")
                                val id = intent.getStringExtra("Id")

                                val intent = Intent(this@DriverMauban, DriverEditRoute::class.java)
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
                                    this@DriverMauban,
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

                    if (seat == "maubanSeatA1") {
                        view.txtName.text = fName
                        view.txtSeat.text = seat
                    } else if(seat == "0") {
                        view.txtName.text = message
                        view.txtSeat.text = message
                    }

                    del.setOnClickListener {
                        dialog.dismiss()
                        database1.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (smTerminalSnapshot in snapshot.children) {
                                    val seatCheck = smTerminalSnapshot.child("Seats")
                                        .child("maubanSeatA1").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("maubanSeatA1").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
                                                    if (user == "maubanSeatA1") {
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

                    if (seat == "maubanSeatA2") {
                        view.txtName.text = fName
                        view.txtSeat.text = seat

                    } else if(seat == "0") {
                        view.txtName.text = message
                        view.txtSeat.text = message
                    }

                    del.setOnClickListener {
                        dialog.dismiss()
                        database1.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (smTerminalSnapshot in snapshot.children) {
                                    val seatCheck = smTerminalSnapshot.child("Seats")
                                        .child("maubanSeatA2").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("maubanSeatA2").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
                                                    if (user == "maubanSeatA2") {
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

                    if (seat != "maubanSeatA3") {
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
                                        .child("maubanSeatA3").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("maubanSeatA3").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
                                                    if (user == "maubanSeatA3") {
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

                    if (seat != "maubanSeatA4") {
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
                                        .child("maubanSeatA4").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("maubanSeatA4").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
                                                    if (user == "maubanSeatA4") {
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

                    if (seat != "maubanSeatB1") {
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
                                        .child("maubanSeatB1").value
                                    if (seatCheck == "Reserved") {
                                        smTerminalSnapshot.ref.child("Seats")
                                            .child("maubanSeatB1").setValue("Available")

                                        database.addValueEventListener(object :
                                            ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                for (userSeat in snapshot.children) {
                                                    val user = userSeat.child("seatID").value
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