package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import ph.edu.mseuf.vango.databinding.ActivityChooseUserBinding
import ph.edu.mseuf.vango.databinding.ActivityWelcomeScreenBinding
import ph.edu.mseuf.vango.databinding.ActivityWelcomeScreensBinding
import ph.edu.mseuf.vango.databinding.OperatorTerminalAlertBinding
import ph.edu.vangodriver.*

class WelcomeScreens : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeScreensBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreensBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        // go to login page
        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this, LogSignChooseUser::class.java))

        }
        // go to sign up page
        binding.btnSignUp.setOnClickListener{
            startActivity(Intent(this, SignChooseUser::class.java))

        }

    }
}