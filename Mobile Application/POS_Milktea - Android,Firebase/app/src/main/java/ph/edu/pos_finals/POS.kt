package ph.edu.pos_finals

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.pos_finals.databinding.PosBinding

class POS : AppCompatActivity() {
    private lateinit var binding: PosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var button:Button = binding.buttonViewData

        //ViewData Function
        button.setOnClickListener {
            startActivity(Intent(applicationContext,ViewData::class.java))
        }

        binding.textViewName.text = intent.getStringExtra("Name")
        binding.textViewDate.text = intent.getStringExtra("Date")

        var logout:Button = binding.buttonLogout

        //Logout Function
        logout.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure!")
            builder.setMessage("Do you want to Logout?")
            builder.setPositiveButton("Yes") { _: DialogInterface, i: Int ->
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@POS,MainActivity::class.java))
                finish()
            }
            builder.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int ->})
            builder.show()

        }

    }

    //Choco Button
    fun choco(view:View){
        var price = 45
        binding.textViewDisplay.text = " Chocolate Flavor "
        val quantity = binding.editTextQuanty.text.toString().toInt()

        var total = price * quantity
        var totalP = when {
            binding.radioButtonSmall.isChecked -> total + 10
            binding.radioButtonMed.isChecked -> total + 20
            else -> total + 30
        }
        binding.textViewTotal.text = "P"+totalP.toString() + ".00"
    }

    //Oreo button
    fun oreo(view:View){
        var price = 55
        binding.textViewDisplay.text = " Oreo Flavor "
        val quantity = binding.editTextQuanty.text.toString().toInt()

        var total = price * quantity
        var totalP = when {
            binding.radioButtonSmall.isChecked -> total + 15
            binding.radioButtonMed.isChecked -> total + 25
            else -> total + 35
        }
        binding.textViewTotal.text = "P"+totalP.toString() + ".00"
    }

    //Vanilla Button
    fun vanilla(view:View){
        var price = 65
        binding.textViewDisplay.text = " Vanilla Flavor "
        val quantity = binding.editTextQuanty.text.toString().toInt()

        var total = price * quantity
        var totalP = when {
            binding.radioButtonSmall.isChecked -> total + 20
            binding.radioButtonMed.isChecked -> total + 30
            else -> total + 40
        }
        binding.textViewTotal.text = "P"+totalP.toString() + ".00"
    }

    //Caramel Button
    fun caramel(view:View){
        var price = 75
        binding.textViewDisplay.text = " Caramel Flavor "
        val quantity = binding.editTextQuanty.text.toString().toInt()

        var total = price * quantity
        var totalP = when {
            binding.radioButtonSmall.isChecked -> total + 25
            binding.radioButtonMed.isChecked -> total + 35
            else -> total + 45
        }
        binding.textViewTotal.text = "P"+totalP.toString() + ".00"
    }

    //Matcha Button
    fun matcha(view:View){
        var price = 85
        binding.textViewDisplay.text = " Matcha Flavor "
        val quantity = binding.editTextQuanty.text.toString().toInt()

        var total = price * quantity
        var totalP = when {
            binding.radioButtonSmall.isChecked -> total + 30
            binding.radioButtonMed.isChecked -> total + 40
            else -> total + 50
        }
        binding.textViewTotal.text = "P"+totalP.toString() + ".00"
    }

    //Mocha Button
    fun mocha(view:View){
        var price = 95
        binding.textViewDisplay.text = " Mocha Flavor "
        val quantity = binding.editTextQuanty.text.toString().toInt()

        var total = price * quantity
        var totalP = when {
            binding.radioButtonSmall.isChecked -> total + 35
            binding.radioButtonMed.isChecked -> total + 45
            else -> total + 55
        }
        binding.textViewTotal.text = "P"+totalP.toString() + ".00"
    }

    //Save Function
    fun saveData(view: View){
        var date = binding.textViewDate.text.toString()
        var empName = binding.textViewName.text.toString()
        var flavor = binding.textViewDisplay.text.toString()
        var quanty = binding.editTextQuanty.text.toString()
        var size = when {
            binding.radioButtonSmall.isChecked -> binding.radioButtonSmall.text.toString()
            binding.radioButtonMed.isChecked -> binding.radioButtonMed.text.toString()
            else -> binding.radioButtonLarge.text.toString()
        }
        var total = binding.textViewTotal.text.toString()


        if(date.trim().isEmpty() && empName.trim().isEmpty() && flavor.trim().isEmpty()
            && quanty.trim().isEmpty() && size.trim().isEmpty() && total.trim().isEmpty()){
            Toast.makeText(
                this@POS,
                "Please Fill All Data",
                Toast.LENGTH_SHORT
            ).show()
        }
        var ref = FirebaseDatabase.getInstance().getReference("sold")
        var soldId: String? = ref.push().key

        var solds = Sold(soldId, date, empName, flavor ,quanty, size,total )

        if (soldId != null) {
            ref.child(soldId).setValue(solds).addOnCompleteListener{
                binding.editTextQuanty.text.clear()
                Toast.makeText(
                    this@POS,
                    "Purchased SuccessFully ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }





}



