package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ph.edu.mseuf.vango.databinding.ActivityCommuterLoginsBinding

class CommuterLogins : AppCompatActivity() {
    private lateinit var binding : ActivityCommuterLoginsBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommuterLoginsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnForgotPassword.setOnClickListener {
            startActivity(Intent(this, CommuterForgotPassword::class.java))
        }
        binding.btnsignIn.setOnClickListener{
            doLogin()
        }
        binding.btnSignUp.setOnClickListener{
            startActivity(Intent(this, full::class.java))

        }
        binding.loginBackButton.setOnClickListener {
            startActivity(Intent(this, WelcomeScreens::class.java))
        }
    }

    fun doLogin(){
        if(binding.textInputEmail.text.toString().isEmpty()){
            binding.textInputEmail.error = "Please enter email"
            binding.textInputEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(binding.textInputEmail.text.toString()).matches()){
            binding.textInputEmail.error = "Please enter valid email"
            binding.textInputEmail.requestFocus()
            return
        }

        if(binding.textInputPassword.text.toString().isEmpty()){
            binding.textInputPassword.error = "Please enter password"
            binding.textInputPassword.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(binding.textInputEmail.text.toString(), binding.textInputPassword.text.toString())
            .addOnCompleteListener { task ->
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
                startActivity(Intent(this, CommuterHome::class.java))
                finish()
            }
        }

    }

}