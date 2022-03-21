package ph.edu.vangodriver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import ph.edu.mseuf.vango.databinding.OperatorHomepageBinding
import java.lang.Exception

class OperatorHomepage : AppCompatActivity() {
    private lateinit var binding: OperatorHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OperatorHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val terminal = intent.getStringExtra("Title")

        // go to login page

        binding.btnDriver.setOnClickListener{
            val intent = Intent(this@OperatorHomepage, ViewDriverList::class.java)
            intent.putExtra("Title", terminal)
            startActivity(intent)

        }

        binding.btnListVan.setOnClickListener{
            val intent = Intent(this@OperatorHomepage, OperatorVanList::class.java)
            intent.putExtra("Title", terminal)
            startActivity(intent)

        }

    }
}