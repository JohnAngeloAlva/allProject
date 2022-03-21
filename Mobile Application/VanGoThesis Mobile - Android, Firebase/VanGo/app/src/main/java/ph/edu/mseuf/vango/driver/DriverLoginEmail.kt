package ph.edu.vangodriver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import ph.edu.mseuf.vango.CommuterForgotPassword
import ph.edu.mseuf.vango.CommuterHome
import ph.edu.mseuf.vango.WelcomeScreens
import ph.edu.mseuf.vango.databinding.DriverLoginEmailBinding
import ph.edu.mseuf.vango.databinding.ProfileVerificationAlertBinding
import ph.edu.mseuf.vango.driver.DriverForgotPassword

class DriverLoginEmail : AppCompatActivity() {
    private lateinit var binding: DriverLoginEmailBinding
    private lateinit var auth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DriverLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this@DriverLoginEmail, DriverSignup::class.java)
            startActivity(intent)
        }
        binding.btnforgotPassword.setOnClickListener{
            val intent = Intent(this@DriverLoginEmail, DriverForgotPassword::class.java)
            startActivity(intent)
        }
        binding.btnsignIn.setOnClickListener{
            Login()
        }

        binding.loginBackButton.setOnClickListener {
            startActivity(Intent(this, WelcomeScreens::class.java))
        }

    }

    private fun Login() {
        // if email is empty show error
        if(binding.textInputEmail.text.toString().isEmpty()){
            binding.textInputEmail.error = "Please enter email"
            binding.textInputEmail.requestFocus()
            return
        }

        // email pattern
        if(!Patterns.EMAIL_ADDRESS.matcher(binding.textInputEmail.text.toString()).matches()){
            binding.textInputEmail.error = "Please enter valid email"
            binding.textInputEmail.requestFocus()
            return
        }

        // if password is empty
        if(binding.textInputPassword.text.toString().isEmpty()){
            binding.textInputPassword.error = "Please enter password"
            binding.textInputPassword.requestFocus()
            return
        }

        val email : String = binding.textInputEmail.text.toString().trim{it <= ' '}
        val password : String = binding.textInputPassword.text.toString().trim{it <= ' '}

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(
                        this,
                        task.exception!!.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)

    }
    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                Toast.makeText(
                    this,
                    "You login successfully",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@DriverLoginEmail, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                intent.putExtra("user_account", FirebaseAuth.getInstance().currentUser!!.email)
                startActivity(intent)
            }
        }
    }


}