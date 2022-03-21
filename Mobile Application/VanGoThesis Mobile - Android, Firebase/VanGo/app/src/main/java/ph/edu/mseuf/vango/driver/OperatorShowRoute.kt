package ph.edu.vangodriver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ph.edu.mseuf.vango.databinding.DeleteRouteAlertBinding
import ph.edu.mseuf.vango.databinding.OperatorShowRouteBinding

class OperatorShowRoute : AppCompatActivity() {
    private lateinit var binding: OperatorShowRouteBinding
    lateinit var database: DatabaseReference
    lateinit var mDatabase: DatabaseReference
    val TAG = "OperatorShowRoute"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OperatorShowRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        val driverID = intent.getStringExtra("Id")
        binding.id.text = driverID
        val driverName = intent.getStringExtra("Name")
        binding.driverName.text = driverName
        val driverFare = intent.getStringExtra("Fare")
        binding.fareRate.text = driverFare
        val driverDestination = intent.getStringExtra("Destination")
        binding.destination.text = driverDestination
        val driverEmailAccount = intent.getStringExtra("EmailAccount")
        binding.emailAccount.text = driverEmailAccount
        val driverPlateNumber = intent.getStringExtra("PlateNumber")
        binding.plateNumber.text = driverPlateNumber
        val driverFcmNumber = intent.getStringExtra("FcmNumber")
        binding.fcmNumber.text = driverFcmNumber


        val terminal = intent.getStringExtra("vanTerminal")

        binding.btnDelete.setOnClickListener {

            val destinationDelete = binding.destination.text.toString()
            val driverNameDelete = binding.driverName.text.toString()
            val fareRateDelete = binding.fareRate.text.toString()

            val mDialogViewDelete = DeleteRouteAlertBinding.inflate(layoutInflater)
            val mBuilderDelete =
                AlertDialog.Builder(this@OperatorShowRoute).setView(mDialogViewDelete.root)

            val alertDialogDelete = mBuilderDelete.show()

            mDialogViewDelete.destination.text = destinationDelete
            mDialogViewDelete.driverName.text = driverNameDelete
            mDialogViewDelete.fareRate.text = fareRateDelete

            val userId = intent.getStringExtra("DriverAccount")

            val databaseSM = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
            databaseSM.child(userId!!).get().addOnSuccessListener {
                if (it.exists()) {

                    smTerminal(terminal, mDialogViewDelete,userId,alertDialogDelete)


                }

            }

            val databaseTalipapa = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")
            databaseTalipapa.child(userId).get().addOnSuccessListener {
                if(it.exists()) {

                    talipapaTerminal(terminal, mDialogViewDelete,userId,alertDialogDelete)


                }
            }

            val databasePacific = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
            databasePacific.child(userId).get().addOnSuccessListener {
                if(it.exists()) {

                    pacificTerminal(terminal, mDialogViewDelete,userId,alertDialogDelete)


                }
            }

            val databaseGrand = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
            databaseGrand.child(userId).get().addOnSuccessListener {
                if(it.exists()) {

                    grandTerminal(terminal, mDialogViewDelete,userId,alertDialogDelete)


                }
            }
        }



    }



    private fun grandTerminal(
        terminal: String?,
        mDialogViewDelete: DeleteRouteAlertBinding,
        userId: String,
        alertDialogDelete: AlertDialog
    ) {
        mDialogViewDelete.btnDeleteRoute.setOnClickListener {
            val driverID = intent.getStringExtra("Id")
            database = FirebaseDatabase.getInstance().getReference("talipapaTerminal")
            database.child(driverID!!).removeValue().addOnSuccessListener {
                Toast.makeText(
                    this@OperatorShowRoute,
                    "Successfully to deleted the data",
                    Toast.LENGTH_SHORT
                ).show()


                val databaseSM =
                    FirebaseDatabase.getInstance().getReference("SmDriverProfile")

                databaseSM.child(userId).child("id").setValue("0")

                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                val title = "Deleted Route"
                val message = "Your Route has been deleted in the terminal"
                val reciepient = intent.getStringExtra("FcmNumber")
                if(title.isNotEmpty() && message.isNotEmpty() ) {
                    PushNotification(
                        NotificationData(title, message),
                        reciepient!!
                    ).also {
                        sendNotification(it)
                    }

                }

                val intent = Intent(this@OperatorShowRoute, OperatorVanList::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("Title", terminal)
                startActivity(intent)

            }.addOnFailureListener {
                Toast.makeText(
                    this@OperatorShowRoute,
                    "Failed to delete Data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        mDialogViewDelete.btnCancel.setOnClickListener {
            alertDialogDelete.dismiss()
        }
    }

    private fun pacificTerminal(
        terminal: String?,
        mDialogViewDelete: DeleteRouteAlertBinding,
        userId: String,
        alertDialogDelete: AlertDialog
    ) {
        mDialogViewDelete.btnDeleteRoute.setOnClickListener {
            val driverID = intent.getStringExtra("Id")
            database = FirebaseDatabase.getInstance().getReference("talipapaTerminal")
            database.child(driverID!!).removeValue().addOnSuccessListener {
                Toast.makeText(
                    this@OperatorShowRoute,
                    "Successfully to deleted the data",
                    Toast.LENGTH_SHORT
                ).show()


                val databaseSM =
                    FirebaseDatabase.getInstance().getReference("SmDriverProfile")

                databaseSM.child(userId).child("id").setValue("0")

                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                val title = "Deleted Route"
                val message = "Your Route has been deleted in the terminal"
                val reciepient = intent.getStringExtra("FcmNumber")
                if(title.isNotEmpty() && message.isNotEmpty() ) {
                    PushNotification(
                        NotificationData(title, message),
                        reciepient!!
                    ).also {
                        sendNotification(it)
                    }

                }

                val intent = Intent(this@OperatorShowRoute, OperatorVanList::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("Title", terminal)
                startActivity(intent)

            }.addOnFailureListener {
                Toast.makeText(
                    this@OperatorShowRoute,
                    "Failed to delete Data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        mDialogViewDelete.btnCancel.setOnClickListener {
            alertDialogDelete.dismiss()
        }
    }

    private fun talipapaTerminal(
        terminal: String?,
        mDialogViewDelete: DeleteRouteAlertBinding,
        userId: String,
        alertDialogDelete: AlertDialog
    ) {
        mDialogViewDelete.btnDeleteRoute.setOnClickListener {
            val driverID = intent.getStringExtra("Id")
            database = FirebaseDatabase.getInstance().getReference("talipapaTerminal")
            database.child(driverID!!).removeValue().addOnSuccessListener {
                Toast.makeText(
                    this@OperatorShowRoute,
                    "Successfully to deleted the data",
                    Toast.LENGTH_SHORT
                ).show()


                val databaseSM =
                    FirebaseDatabase.getInstance().getReference("SmDriverProfile")

                databaseSM.child(userId).child("id").setValue("0")

                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                val title = "Deleted Route"
                val message = "Your Route has been deleted in the terminal"
                val reciepient = intent.getStringExtra("FcmNumber")
                if(title.isNotEmpty() && message.isNotEmpty() ) {
                    PushNotification(
                        NotificationData(title, message),
                        reciepient!!
                    ).also {
                        sendNotification(it)
                    }

                }

                val intent = Intent(this@OperatorShowRoute, OperatorVanList::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("Title", terminal)
                startActivity(intent)

            }.addOnFailureListener {
                Toast.makeText(
                    this@OperatorShowRoute,
                    "Failed to delete Data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        mDialogViewDelete.btnCancel.setOnClickListener {
            alertDialogDelete.dismiss()
        }
    }

    private fun smTerminal(
        terminal: String?,
        mDialogViewDelete: DeleteRouteAlertBinding,
        userId: String,
        alertDialogDelete: AlertDialog
    ) {
        mDialogViewDelete.btnDeleteRoute.setOnClickListener {
            val driverID = intent.getStringExtra("Id")
            database = FirebaseDatabase.getInstance().getReference("smTerminal")
            database.child(driverID!!).removeValue().addOnSuccessListener {
                Toast.makeText(
                    this@OperatorShowRoute,
                    "Successfully to deleted the data",
                    Toast.LENGTH_SHORT
                ).show()


                val databaseSM =
                    FirebaseDatabase.getInstance().getReference("SmDriverProfile")

                databaseSM.child(userId).child("id").setValue("0")

                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                val title = "Deleted Route"
                val message = "Your Route has been deleted in the terminal"
                val reciepient = intent.getStringExtra("FcmNumber")
                if(title.isNotEmpty() && message.isNotEmpty() ) {
                    PushNotification(
                        NotificationData(title, message),
                        reciepient!!
                    ).also {
                        sendNotification(it)
                    }

                }

                val intent = Intent(this@OperatorShowRoute, OperatorVanList::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("Title", terminal)
                startActivity(intent)

            }.addOnFailureListener {
                Toast.makeText(
                    this@OperatorShowRoute,
                    "Failed to delete Data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        mDialogViewDelete.btnCancel.setOnClickListener {
            alertDialogDelete.dismiss()
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

    override fun onBackPressed() {
        val terminal = intent.getStringExtra("Title")

        val intent = Intent(this@OperatorShowRoute, OperatorHomepage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("Title", terminal)
        startActivity(intent)
    }
}