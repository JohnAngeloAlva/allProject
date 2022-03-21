package ph.edu.vangodriver

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import ph.edu.mseuf.vango.databinding.OperatorDriverListBinding


class ViewDriverList: AppCompatActivity() {
    private lateinit var binding : OperatorDriverListBinding
    private lateinit var dbref :DatabaseReference
    private lateinit var driverRecyclerView : RecyclerView
    private lateinit var driverList :ArrayList<DriverListArray>


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = OperatorDriverListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val terminal: String? = intent.getStringExtra("Title")
        val terminalMarker = binding.terminal
        terminalMarker.text = terminal

        driverRecyclerView = binding.driverList
        driverRecyclerView.layoutManager = LinearLayoutManager(this)
        driverRecyclerView.setHasFixedSize(true)

        driverList = arrayListOf<DriverListArray>()
        getVanData()
    }

    private fun getVanData() {

        val vanTerminal = binding.terminal.text
        if(vanTerminal == "SM Lucena City Terminal"){
            var adapter = AdapterDriverList(driverList)

            dbref = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
            dbref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(vanSnapshots in snapshot.children){
                            val driver = vanSnapshots.getValue(DriverListArray::class.java)
                            driverList.add(driver!!)
                        }
                        driverRecyclerView.adapter = adapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@ViewDriverList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })

            adapter.setOnItemClickListener(object : AdapterDriverList.onItemClickListener{
                override fun onItemClick(position: Int) {

                    val intent = Intent(this@ViewDriverList, ShowDriverInfo::class.java)
                    intent.putExtra("user_id", driverList[position].userID)
                    intent.putExtra("Name", driverList[position].fullName)
                    intent.putExtra("Email", driverList[position].userName)
                    intent.putExtra("Title", vanTerminal)
                    startActivity(intent)

                }
            })

        }

        if(vanTerminal == "Pacific Lucena City Terminal"){
            var adapter = AdapterDriverList(driverList)

            dbref = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
            dbref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(vanSnapshots in snapshot.children){
                            val driver = vanSnapshots.getValue(DriverListArray::class.java)
                            driverList.add(driver!!)
                        }
                        driverRecyclerView.adapter = adapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@ViewDriverList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })

            adapter.setOnItemClickListener(object : AdapterDriverList.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@ViewDriverList, ShowDriverInfo::class.java)
                    intent.putExtra("user_id", driverList[position].userID)
                    intent.putExtra("Name", driverList[position].fullName)
                    intent.putExtra("Email", driverList[position].userName)
                    intent.putExtra("Title", vanTerminal)
                    startActivity(intent)

                }
            })
        }

        if(vanTerminal == "Ibabang Dupay Talipapa Lucena City Terminal"){
            var adapter = AdapterDriverList(driverList)

            dbref = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")
            dbref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(vanSnapshots in snapshot.children){
                            val driver = vanSnapshots.getValue(DriverListArray::class.java)
                            driverList.add(driver!!)
                        }
                        driverRecyclerView.adapter = adapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@ViewDriverList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })

            adapter.setOnItemClickListener(object : AdapterDriverList.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@ViewDriverList, ShowDriverInfo::class.java)
                    intent.putExtra("user_id", driverList[position].userID)
                    intent.putExtra("Name", driverList[position].fullName)
                    intent.putExtra("Email", driverList[position].userName)
                    intent.putExtra("Title", vanTerminal)
                    startActivity(intent)

                }
            })
        }

        if(vanTerminal == "Grand Terminal Lucena City"){
            var adapter = AdapterDriverList(driverList)

            dbref = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
            dbref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(vanSnapshots in snapshot.children){
                            val driver = vanSnapshots.getValue(DriverListArray::class.java)
                            driverList.add(driver!!)
                        }
                        driverRecyclerView.adapter = adapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@ViewDriverList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })

            adapter.setOnItemClickListener(object : AdapterDriverList.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@ViewDriverList, ShowDriverInfo::class.java)
                    intent.putExtra("user_id", driverList[position].userID)
                    intent.putExtra("Name", driverList[position].fullName)
                    intent.putExtra("Email", driverList[position].userName)
                    intent.putExtra("Title", vanTerminal)
                    startActivity(intent)

                }
            })
        }




    }

    override fun onBackPressed() {

        val vanTerminal = binding.terminal.text

        val intent = Intent(this@ViewDriverList, OperatorHomepage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        intent.putExtra("Title", vanTerminal)

        startActivity(intent)
    }


}


