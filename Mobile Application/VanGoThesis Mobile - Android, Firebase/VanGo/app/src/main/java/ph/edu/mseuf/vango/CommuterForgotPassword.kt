package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ph.edu.mseuf.vango.databinding.ActivityCommuterForgotPasswordBinding
import ph.edu.vangodriver.DriverLoginEmail

class CommuterForgotPassword : AppCompatActivity() {
    private lateinit var binding : ActivityCommuterForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommuterForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnForgot.setOnClickListener {
            val email = binding.txtEmail.text.toString()

            if(binding.txtEmail.text.toString().isEmpty()){
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
                            startActivity(Intent(this, CommuterLogins::class.java))
                            finish()
                            Toast.makeText(
                                this,
                                "Email send successfully to reset your password",
                                Toast.LENGTH_LONG
                            ).show()

                            finish()
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