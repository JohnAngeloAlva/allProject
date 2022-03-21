package ph.edu.pos_finals

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.pos_finals.databinding.ViewDataBinding


class ViewData: AppCompatActivity() {
    private lateinit var binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var ref = FirebaseDatabase.getInstance().getReference("sold")

        //Read Data
        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for(i in snapshot.children){
                    var orderNum = i.child("id").getValue()
                    var dateP = i.child("date").getValue()
                    var nameEmp = i.child("empName").getValue()
                    var flavorP = i.child("flavor").getValue()
                    var quantityP = i.child("quanty").getValue()
                    var sizeP = i.child("size").getValue()
                    var totalP = i.child("total").getValue()
                    sb.append("OrderNum: $orderNum \n\tDate: $dateP \n\tName: $nameEmp" +
                            "\n\tFlavor: $flavorP \n\tQuantity: $quantityP \n\tSize: $sizeP \n\tTotal: $totalP \n\n")
                }
                var data =  binding.textData
                data.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        ref.addValueEventListener(getData)
        ref.addListenerForSingleValueEvent(getData)

        //Void Data
        binding.buttonVoid.setOnClickListener {

            var builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure!")
            builder.setMessage("Do you want void this purchased milktea?")
            builder.setPositiveButton("Yes") { _: DialogInterface, i: Int ->
                var search = binding.editTextSearch.text.toString()
                if(search.isNotEmpty()){
                    deleteData(search)
                }else{
                    Toast.makeText(
                        this@ViewData,
                        "Please Fill the Data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            builder.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int ->})
            builder.show()


        }

    }
    
    //Delete Function
    private fun deleteData(search: String) {

        var database = FirebaseDatabase.getInstance().getReference("sold")
        database.child(search).removeValue().addOnSuccessListener {

            binding.editTextSearch.text.clear()
            Toast.makeText(
                this@ViewData,
                "Void Purchased",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener {
            Toast.makeText(
                this@ViewData,
                "Failed to void ",
                Toast.LENGTH_SHORT
            ).show()
        }

    }


}



