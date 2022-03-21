package ph.edu.vangodriver

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ph.edu.mseuf.vango.databinding.EditRouteLoginBinding
import ph.edu.mseuf.vango.databinding.ShowRouteBinding

class ShowRouteInfo: AppCompatActivity() {
    private lateinit var binding: ShowRouteBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)


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

        binding.btnEdit.setOnClickListener{
            val userAccount = intent.getStringExtra("user_account")
            val emailAccount = binding.emailAccount.text.toString()
            val mDialogView = EditRouteLoginBinding.inflate(layoutInflater)
            val mBuilder = AlertDialog.Builder(this@ShowRouteInfo).setView(mDialogView.root)

            val alertDialog = mBuilder.show()
            mDialogView.userAccount.text = emailAccount

            mDialogView.btnCancel.setOnClickListener {
                alertDialog.dismiss()
            }
            mDialogView.btnLogin.setOnClickListener {

                if(mDialogView.textInputPassword.text.toString().isEmpty()){
                    Toast.makeText(
                        this@ShowRouteInfo,
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
                                        this@ShowRouteInfo,
                                        "You login successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val id = binding.id.text.toString()
                                    val terminal = intent.getStringExtra("vanTerminal")
                                    val userID = intent.getStringExtra("user_id")
                                    val arrivedID = intent.getStringExtra("arrived_id")

                                    val intent = Intent(this@ShowRouteInfo, DriverEditRoute::class.java)
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
                                        this@ShowRouteInfo,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                        )
                }
            }
        }



    }
    override fun onBackPressed() {
        val terminal = intent.getStringExtra("vanTerminal")
        val userAccount = intent.getStringExtra("user_account")
        val userID = intent.getStringExtra("user_id")
        val arrivedID = intent.getStringExtra("arrived_id")

        val intent = Intent(this@ShowRouteInfo, ViewVanList::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("Title", terminal)
        intent.putExtra("user_id", userID)
        intent.putExtra("user_account", userAccount)
        intent.putExtra("arrived_id", arrivedID)
        startActivity(intent)
    }

}