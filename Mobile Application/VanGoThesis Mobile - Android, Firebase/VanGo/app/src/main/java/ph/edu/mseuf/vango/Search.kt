package ph.edu.mseuf.vango

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.mseuf.vango.databinding.ActivitySearchBinding

class Search : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


    }
}