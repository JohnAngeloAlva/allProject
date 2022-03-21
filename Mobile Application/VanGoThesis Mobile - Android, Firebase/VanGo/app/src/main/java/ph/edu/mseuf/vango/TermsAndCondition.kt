package ph.edu.mseuf.vango

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.mseuf.vango.databinding.ActivityTermsAndConditionBinding

class TermsAndCondition : AppCompatActivity() {
    private lateinit var binding: ActivityTermsAndConditionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsAndConditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReturn.setOnClickListener{
            onBackPressed()
        }
    }
}