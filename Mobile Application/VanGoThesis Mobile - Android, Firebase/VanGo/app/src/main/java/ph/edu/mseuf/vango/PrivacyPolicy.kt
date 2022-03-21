package ph.edu.mseuf.vango

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.mseuf.vango.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicy : AppCompatActivity() {
    private lateinit var binding: ActivityPrivacyPolicyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReturn.setOnClickListener{
            onBackPressed()
        }

    }
}