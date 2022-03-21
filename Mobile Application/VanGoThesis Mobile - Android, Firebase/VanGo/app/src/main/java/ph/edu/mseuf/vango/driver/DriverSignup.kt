package ph.edu.vangodriver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ph.edu.mseuf.vango.databinding.DriverSignupBinding

class DriverSignup : AppCompatActivity() {
    private lateinit var binding: DriverSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DriverSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.login.setOnClickListener {
            val intent = Intent(this@DriverSignup,DriverLoginEmail ::class.java)
            startActivity(intent)
        }

        // call function signup user
        binding.btnSignup.setOnClickListener{
            signUpUser()
        }
    }

    private fun signUpUser() {

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

        // if both password are not the same
        if(binding.textInputCPassword.text.toString() != binding.textInputPassword.text.toString()){
            binding.textInputCPassword.error = "Password do not match"
            binding.textInputCPassword.requestFocus()
            return
        }

        val email : String = binding.textInputEmail.text.toString().trim{it <= ' '}
        val password : String = binding.textInputPassword.text.toString().trim{it <= ' '}

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->

                    //If the signup is successfully done

                    if(task.isSuccessful){

                        //firebase registered user
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        Toast.makeText(
                            this@DriverSignup,
                            "You signup successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@DriverSignup, DriverAdditionalInfo::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user_id", firebaseUser.uid)
                        intent.putExtra("user_account", email)
                        startActivity(intent)
                        finish()
                    }
                    else{

                        Toast.makeText(
                            this@DriverSignup,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

    }
}