package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.mseuf.vango.databinding.ActivityLogSignChooseUserBinding
import ph.edu.mseuf.vango.databinding.ActivitySignChooseUserBinding
import ph.edu.vangodriver.DriverSignup

class SignChooseUser : AppCompatActivity() {
    private lateinit var binding : ActivitySignChooseUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignChooseUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCommuter.setOnClickListener {
            startActivity(Intent(this, full::class.java))
        }

        binding.btnDriver.setOnClickListener {
            startActivity(Intent(this, DriverSignup::class.java))
        }
    }
}