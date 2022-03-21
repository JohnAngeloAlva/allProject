package ph.edu.mseuf.vango

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import ph.edu.mseuf.vango.databinding.VanListBinding
import ph.edu.vangodriver.Route
import ph.edu.vangodriver.ShowRouteInfo


class CommuterViewVanList: AppCompatActivity() {
    private lateinit var binding : VanListBinding
    private lateinit var dbref :DatabaseReference
    private lateinit var vanRecyclerView : RecyclerView
    private lateinit var vanList :ArrayList<Route>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VanListBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val terminal: String? = intent.getStringExtra("Title")
        val terminalMarker : TextView = binding.terminal
        terminalMarker.text = terminal


        vanRecyclerView = binding.vanList
        vanRecyclerView.layoutManager = LinearLayoutManager(this)
        vanRecyclerView.setHasFixedSize(true)

        vanList = arrayListOf<Route>()
        getVanData()


    }

    private fun getVanData() {

        val vanTerminal = binding.terminal.text.toString()


        if(vanTerminal == "SM Lucena City Terminal"){
            var adapter = AdapterVan(vanList)

            dbref = FirebaseDatabase.getInstance().getReference("smTerminal")
            dbref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    if(snapshot.exists()){
                        for(vanSnapshots in snapshot.children){
                            val van = vanSnapshots.getValue(Route::class.java)
                            vanList.add(van!!)
                        }

                        vanRecyclerView.adapter = adapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@CommuterViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })

            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener {
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")
                    val destination = vanList[position].destination

                    if(destination == "Tayabas City"){
                        var intent = Intent(this@CommuterViewVanList, TayabasReservations::class.java)
                        intent.putExtra("fcmNumber", vanList[position].fcmNumber)
                        intent.putExtra("id", vanList[position].id)
                        intent.putExtra("destination", vanList[position].destination)
                        intent.putExtra("driverName", vanList[position].driverName)
                        intent.putExtra("fare", vanList[position].fareRate)
                        startActivity(intent)
                        finish()
                    }
                    if(destination == "Mauban City"){
                        var intent = Intent(this@CommuterViewVanList, MaubanReservation::class.java)
                        intent.putExtra("fcmNumber", vanList[position].fcmNumber)
                        intent.putExtra("id", vanList[position].id)
                        intent.putExtra("destination", vanList[position].destination)
                        startActivity(intent)
                        finish()
                    }


                }
            })

        }

        else if(vanTerminal == "Pacific Lucena City Terminal"){
            var adapter = AdapterVan(vanList)

            dbref = FirebaseDatabase.getInstance().getReference("pacificTerminal")
            dbref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(vanSnapshots in snapshot.children){
                            val van = vanSnapshots.getValue(Route::class.java)
                            vanList.add(van!!)
                        }
                        vanRecyclerView.adapter = adapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@CommuterViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener {
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    var intent = Intent(this@CommuterViewVanList, ShowRouteInfo::class.java)
                    intent.putExtra("Id", vanList[position].id)
                    intent.putExtra("Name", vanList[position].driverName)
                    intent.putExtra("Fare", vanList[position].fareRate)
                    intent.putExtra("Destination", vanList[position].destination)
                    intent.putExtra("EmailAccount", vanList[position].emailAccount)
                    intent.putExtra("PlateNumber", vanList[position].plateNumber)
                    intent.putExtra("user_id", userID)
                    intent.putExtra("user_account", userAccount)
                    intent.putExtra("vanTerminal", vanTerminal)
                    intent.putExtra("arrived_id", arrivedID)
                    startActivity(intent)
                }

            })
        }

        else if(vanTerminal == "Ibabang Dupay Talipapa Lucena City Terminal"){
            var adapter = AdapterVan(vanList)

            dbref = FirebaseDatabase.getInstance().getReference("talipapaTerminal")
            dbref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(vanSnapshots in snapshot.children){
                            val van = vanSnapshots.getValue(Route::class.java)
                            vanList.add(van!!)
                        }
                        vanRecyclerView.adapter = adapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@CommuterViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener {
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    var intent = Intent(this@CommuterViewVanList, ShowRouteInfo::class.java)
                    intent.putExtra("Id", vanList[position].id)
                    intent.putExtra("Name", vanList[position].driverName)
                    intent.putExtra("Fare", vanList[position].fareRate)
                    intent.putExtra("Destination", vanList[position].destination)
                    intent.putExtra("EmailAccount", vanList[position].emailAccount)
                    intent.putExtra("PlateNumber", vanList[position].plateNumber)
                    intent.putExtra("user_id", userID)
                    intent.putExtra("user_account", userAccount)
                    intent.putExtra("vanTerminal", vanTerminal)
                    intent.putExtra("arrived_id", arrivedID)
                    startActivity(intent)
                }

            })
        }

        else if(vanTerminal == "Grand Terminal Lucena City"){
            var adapter = AdapterVan(vanList)

            dbref = FirebaseDatabase.getInstance().getReference("grandTerminal")
            dbref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(vanSnapshots in snapshot.children){
                            val van = vanSnapshots.getValue(Route::class.java)
                            vanList.add(van!!)
                        }
                        vanRecyclerView.adapter = adapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@CommuterViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener {
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    var intent = Intent(this@CommuterViewVanList, ShowRouteInfo::class.java)
                    intent.putExtra("Id", vanList[position].id)
                    intent.putExtra("Name", vanList[position].driverName)
                    intent.putExtra("Fare", vanList[position].fareRate)
                    intent.putExtra("Destination", vanList[position].destination)
                    intent.putExtra("EmailAccount", vanList[position].emailAccount)
                    intent.putExtra("PlateNumber", vanList[position].plateNumber)
                    intent.putExtra("user_id", userID)
                    intent.putExtra("user_account", userAccount)
                    intent.putExtra("vanTerminal", vanTerminal)
                    intent.putExtra("arrived_id", arrivedID)
                    startActivity(intent)
                }

            })
        }




    }

    override fun onBackPressed() {


        val intent = Intent(this@CommuterViewVanList, CommuterHome::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}


