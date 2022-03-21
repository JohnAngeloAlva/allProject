package ph.edu.mseuf.vango.driver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ph.edu.mseuf.vango.R
import ph.edu.mseuf.vango.databinding.ActivityDriverForgotPasswordBinding
import ph.edu.vangodriver.DriverLogin
import ph.edu.vangodriver.DriverLoginEmail

class DriverForgotPassword : AppCompatActivity() {
    private lateinit var binding : ActivityDriverForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnForgotPass.setOnClickListener {
            val email = binding.txtDriverEmail.text.toString()

            if(binding.txtDriverEmail.text.toString().isEmpty()){
                Toast.makeText(
                    this,
                    "Please Enter email address",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            startActivity(Intent(this, DriverLoginEmail::class.java))
                            finish()
                            Toast.makeText(
                                this,
                                "Email send successfully to reset your password",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                        else{
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

            }

        }
    }
}