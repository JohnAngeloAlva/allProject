package ph.edu.mseuf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ph.edu.mseuf.databinding.DisplayScoreBinding

class DisplayScore: AppCompatActivity() {
    private lateinit var binding: DisplayScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DisplayScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewResultName.text = intent.getStringExtra("Name")
        binding.textViewResultYear.text = intent.getStringExtra("Year")
        binding.textViewResponse3.text = intent.getStringExtra("Question1")
        binding.textViewResponse5.text = intent.getStringExtra("Question2")
        binding.textViewResponse7.text = intent.getStringExtra("Question3")

    }
}