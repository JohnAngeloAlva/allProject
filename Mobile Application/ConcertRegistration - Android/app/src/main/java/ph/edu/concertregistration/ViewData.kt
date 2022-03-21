package ph.edu.concertregistration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ph.edu.concertregistration.databinding.ViewDataBinding

class ViewData : AppCompatActivity() {
    private lateinit var binding: ViewDataBinding
    //Initialize database
    private val db = DBOperation(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = db.readData()
        binding.textViewData.text = ""
        for (i in 0 until data.size) {
            binding.textViewData.append(data[i].id.toString() + ", " + data[i].name + ", " + data[i].seat+ ", "  + data[i].category + ", " + data[i].pnumber + ", " + data[i].total + ", " + data[i].gmail +"\n")
        }
    }

    //Search Data by Name
    fun search(view: View){
        val data = db.searchRecords(binding.editTextSearch.text.toString())
        binding.textViewData.text = ""
        for (i in 0 until data.size) {
            binding.textViewData.append(data[i].id.toString() + ", " + data[i].name + ", " + data[i].seat+ ", "  + data[i].category + ", " + data[i].pnumber + ", " + data[i].total + ", " + data[i].gmail +"\n")
        }
    }

    //Delete All Data
    fun deleteD(view: View){
        val data = db.deleteRecords()
    }

    //Delete One Data
    fun deleteOneD(view: View){
        if(binding.editTextEditID.text.toString().isNotEmpty() && binding.editTextEditName.text.toString().isNotEmpty() &&
            binding.editTextEditSeat.text.toString().isNotEmpty() && binding.editTextEditCategory.text.toString().isNotEmpty() &&
            binding.editTextEditPnumber.toString().isNotEmpty() && binding.editTextEditTotalP.toString().isNotEmpty() && binding.editTextEditGmail.toString().isNotEmpty()) {

            val registration = Registration(binding.editTextEditName.text.toString(), binding.editTextEditSeat.text.toString(),
                binding.editTextEditCategory.text.toString(), binding.editTextEditPnumber.text.toString(),
                binding.editTextEditTotalP.text.toString().toInt(),binding.editTextEditGmail.text.toString(), binding.editTextEditID.text.toString().toInt())
            db.deleteOneRecords(registration)
            clearField()
        }
        else {
            Toast.makeText(this, "Please Fill Data", Toast.LENGTH_SHORT).show()
        }
    }

    //Refresh Data
    fun refresh(view: View){
        val data = db.readData()
        binding.textViewData.text = ""
        for (i in 0 until data.size) {
            binding.textViewData.append(data[i].id.toString() + ", " + data[i].name + ", " + data[i].seat+ ", "  + data[i].category + ", " + data[i].pnumber + ", " + data[i].total + ", " + data[i].gmail +"\n")
        }
    }

    //Edit Data
    fun editD(view: View){
        if(binding.editTextEditID.text.toString().isNotEmpty() && binding.editTextEditName.text.toString().isNotEmpty() &&
            binding.editTextEditSeat.text.toString().isNotEmpty() && binding.editTextEditCategory.text.toString().isNotEmpty() &&
            binding.editTextEditPnumber.toString().isNotEmpty() && binding.editTextEditTotalP.toString().isNotEmpty() && binding.editTextEditGmail.toString().isNotEmpty()) {

            val registration = Registration(binding.editTextEditName.text.toString(), binding.editTextEditSeat.text.toString(),
                binding.editTextEditCategory.text.toString(), binding.editTextEditPnumber.text.toString(),
                binding.editTextEditTotalP.text.toString().toInt(),binding.editTextEditGmail.text.toString(), binding.editTextEditID.text.toString().toInt())
            db.editData(registration)
            clearField()
        }
        else {
            Toast.makeText(this, "Please Fill Data", Toast.LENGTH_SHORT).show()
        }
    }
    private fun clearField() {
        binding.editTextEditID.text.clear()
        binding.editTextEditName.text.clear()
        binding.editTextEditSeat.text.clear()
        binding.editTextEditCategory.text.clear()
        binding.editTextEditPnumber.text.clear()
        binding.editTextEditTotalP.text.clear()
        binding.editTextEditGmail.text.clear()
    }
}