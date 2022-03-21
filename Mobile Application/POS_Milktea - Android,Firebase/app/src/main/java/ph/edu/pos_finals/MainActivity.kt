package ph.edu.pos_finals

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Button
import android.widget.Toast
import ph.edu.pos_finals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    //Next Function
    fun Next(view: View){

        //DatePicker
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, yy, mm, dd ->
            var dt = "$mm/$dd/$yy"
            binding.editTextDate.setText(dt)
        },  2020,6,27).show()

        var name = binding.editTextName.text
        var pass = binding.editTextPassword.text
        var date = binding.editTextDate.text
        var login: Button = binding.buttonLogin

        //Login Function
        login.setOnClickListener {

            when {
                TextUtils.isEmpty(name.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Enter Name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(pass.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Enter Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    var email: String = name.toString().trim {it <= ' '}
                    var password : String = pass.toString().trim {it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener{ task ->

                            if (task.isSuccessful){
                                var builder = AlertDialog.Builder(this)
                                builder.setTitle("Login Successfully")
                                builder.setPositiveButton("Continue") { _: DialogInterface, i: Int ->
                                    var intent = Intent(this, POS::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("Name", "$email")
                                    intent.putExtra("Date", "$date")

                                    startActivity(intent)
                                }
                                builder.show()
                            } else {

                                Toast.makeText(
                                    this@MainActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
    }

    //OnBackpress
    override fun onBackPressed() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure!")
        builder.setMessage("Do you want to close the app?")
        builder.setPositiveButton("Yes") { _: DialogInterface, i: Int ->
            finish()
        }
        builder.setNegativeButton("No",{dialogInterface: DialogInterface, i: Int ->})
        builder.show()
    }
}