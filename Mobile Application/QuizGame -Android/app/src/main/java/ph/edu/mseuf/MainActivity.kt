package ph.edu.mseuf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import ph.edu.mseuf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter.createFromResource(this,R.array.year_array,android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerYear.adapter = adapter

    }

    fun Next(view: View){

        var name = binding.editTextName.text
        var year = binding.spinnerYear.selectedItem


        val intent = Intent(this, ActivityQuiz::class.java)
        intent.putExtra("Name","$name")
        intent.putExtra("Year",  "$year")

        startActivity(intent)
    }
}