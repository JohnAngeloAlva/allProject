package ph.edu.vangodriver

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ph.edu.mseuf.vango.databinding.OperatorShowDriverInfoBinding
import ph.edu.mseuf.vango.databinding.OperatorVerificationAlertBinding
import java.io.File

class ShowDriverInfo : AppCompatActivity() {

    private lateinit var binding: OperatorShowDriverInfoBinding
    val TAG = "OperatorShowRoute"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OperatorShowDriverInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val driverID = intent.getStringExtra("user_id")
        binding.userId.text = driverID
        val driverName = intent.getStringExtra("Name")
        binding.name.text = driverName
        val driverUsername = intent.getStringExtra("Email")
        binding.userName.text = driverUsername

        binding.verify.setOnClickListener {
            val mDialogView = OperatorVerificationAlertBinding.inflate(layoutInflater)
            val mBuilder =
                AlertDialog.Builder(this@ShowDriverInfo).setView(mDialogView.root)

            mBuilder.show()

            val id = binding.userId.text.toString()
            val name = binding.name.text.toString()
            val account = binding.userName.text.toString()
            val terminal = intent.getStringExtra("Title")

            mDialogView.userId.text = id
            mDialogView.name.text = name
            mDialogView.userName.text = account

            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Fetching Image.....")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val storageRefProfile = FirebaseStorage.getInstance().reference.child("imageProfile/Profile_of_${driverUsername}.jpg")
            val localFileProfile = File.createTempFile("tempImage", "jpg")
            storageRefProfile.getFile(localFileProfile).addOnSuccessListener {

                if(progressDialog.isShowing){
                    progressDialog.dismiss()
                }

                val bitmap = BitmapFactory.decodeFile(localFileProfile.absolutePath)
                mDialogView.imageProfile.setImageBitmap(bitmap)

            }.addOnFailureListener{
                Toast.makeText(
                    this@ShowDriverInfo,
                    "Failed to retrieve image",
                    Toast.LENGTH_SHORT
                ).show()
                progressDialog.dismiss()
            }

            mDialogView.verify.setOnClickListener {
                if (terminal == "SM Lucena City Terminal") {
                    val ref = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
                    ref.child(id).child("verifificationID").setValue("1")

                    FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                    val title = "Verify Profile"
                    val message = "Your Profile to the $terminal has been verified"
                    val recipient = intent.getStringExtra("FcmNumber")
                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                        PushNotification(
                            NotificationData(title, message),
                            recipient!!
                        ).also {
                            sendNotification(it)
                        }

                    }
                    val intent = Intent(this@ShowDriverInfo, OperatorHomepage::class.java)
                    intent.putExtra("Title", terminal)
                    startActivity(intent)
                }
                else if (terminal == "Pacific Lucena City Terminal") {
                    val ref = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
                    ref.child(id).child("verifificationID").setValue("1")

                    FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                    val title = "Verify Profile"
                    val message = "Your Profile to the $terminal has been verified"
                    val recipient = intent.getStringExtra("FcmNumber")
                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                        PushNotification(
                            NotificationData(title, message),
                            recipient!!
                        ).also {
                            sendNotification(it)
                        }

                    }

                    val intent = Intent(this@ShowDriverInfo, OperatorHomepage::class.java)
                    intent.putExtra("Title", terminal)
                    startActivity(intent)
                }
                else if (terminal == "Ibabang Dupay Talipapa Lucena City Terminal") {
                    val ref = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")
                    ref.child(id).child("verifificationID").setValue("1")

                    FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                    val title = "Verify Profile"
                    val message = "Your Profile to the $terminal has been verified"
                    val recipient = intent.getStringExtra("FcmNumber")
                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                        PushNotification(
                            NotificationData(title, message),
                            recipient!!
                        ).also {
                            sendNotification(it)
                        }

                    }
                    val intent = Intent(this@ShowDriverInfo, OperatorHomepage::class.java)
                    intent.putExtra("Title", terminal)
                    startActivity(intent)
                }
                else if (terminal == "Grand Terminal Lucena City") {
                    val ref = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
                    ref.child(id).child("verifificationID").setValue("1")

                    FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                    val title = "Verify Profile"
                    val message = "Your Profile to the $terminal has been verified"
                    val recipient = intent.getStringExtra("FcmNumber")
                    if(title.isNotEmpty() && message.isNotEmpty() ) {
                        PushNotification(
                            NotificationData(title, message),
                            recipient!!
                        ).also {
                            sendNotification(it)
                        }

                    }
                    val intent = Intent(this@ShowDriverInfo, OperatorHomepage::class.java)
                    intent.putExtra("Title", terminal)
                    startActivity(intent)
                }
            }
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

        val intent = Intent(this@ShowDriverInfo, ViewDriverList::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        intent.putExtra("Title", terminal)

        startActivity(intent)
    }
}