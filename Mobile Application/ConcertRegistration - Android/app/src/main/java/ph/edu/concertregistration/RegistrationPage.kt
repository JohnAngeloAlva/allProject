package ph.edu.concertregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import ph.edu.concertregistration.databinding.RegistrationPageBinding

class RegistrationPage:  AppCompatActivity() {
    private lateinit var binding: RegistrationPageBinding

    //Initialize database
    private val db = DBOperation(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter.createFromResource(this,R.array.seat,android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSeat.adapter = adapter
    }

    //Save Data
    fun save (view: View){

        var name = binding.editTextName.text
        var seat = binding.spinnerSeat.selectedItem
        var categories = when {
            binding.radioButtonVIP.isChecked -> binding.radioButtonVIP.text
            else -> binding.radioButtonStandard.text
        }
        var pnumber = binding.editTextPNumber.text
        var totalNumber = binding.editTextTotalPeople.text
        var gmail = binding.editTextGmail.text


        if (name.toString().isNotEmpty() && seat.toString().isNotEmpty()&& categories.toString().isNotEmpty()&& pnumber.toString().isNotEmpty()&& totalNumber.toString().isNotEmpty()&& gmail.toString().isNotEmpty()
        ) {
            val registration = Registration(name.toString(), seat.toString(), categories.toString(), pnumber.toString(), totalNumber.toString().toInt(),gmail.toString())
            db.insertData(registration)
            clearField()
        }
        else {
            Toast.makeText(this, "Please Fill All Data", Toast.LENGTH_SHORT).show()
        }
    }

    //Read Data
    fun nextData(view: View){
        val intent = Intent(this, ViewData::class.java)
        startActivity(intent)
    }

    private fun clearField() {
        binding.editTextName.text.clear()
        binding.editTextPNumber.text.clear()
        binding.editTextTotalPeople.text.clear()
        binding.editTextGmail.text.clear()
    }
}


