package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.mseuf.vango.databinding.ActivityCommuterDashboardBinding
import ph.edu.mseuf.vango.databinding.VanListBinding
import ph.edu.mseuf.vango.driver.DriverAtimonan
import ph.edu.mseuf.vango.driver.DriverMauban
import ph.edu.mseuf.vango.driver.DriverSanAndres
import ph.edu.mseuf.vango.driver.DriverTayabas
import ph.edu.vangodriver.AdapterVan
import ph.edu.vangodriver.MainActivity
import ph.edu.vangodriver.Route
import ph.edu.vangodriver.ShowRouteInfo

class CommuterDashboard : AppCompatActivity() {
    private lateinit var binding : ActivityCommuterDashboardBinding
    private lateinit var dbref :DatabaseReference
    private lateinit var userRef : DatabaseReference
    private lateinit var vanRecyclerView : RecyclerView
    private lateinit var vanList :ArrayList<Route>
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCommuterDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        val terminal: String? = intent.getStringExtra("Title")
        val terminalMarker = binding.terminal
        terminalMarker.text = terminal

        vanRecyclerView = binding.vanList
        vanRecyclerView.layoutManager = LinearLayoutManager(this)
        vanRecyclerView.setHasFixedSize(true)
        vanList = arrayListOf<Route>()
        getVanData()
    }

    private fun getVanData() {

        val vanTerminal = binding.terminal.text
        var adapter = ph.edu.mseuf.vango.AdapterVan(vanList)

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
                    this@CommuterDashboard,
                    error.message,
                    Toast.LENGTH_SHORT).show()
            }
        })

        adapter.setOnItemClickListener(object : ph.edu.mseuf.vango.AdapterVan.onItemClickListener {
            override fun onItemClick(position: Int) {

                val userAccount = intent.getStringExtra("user_account")
                val userID = intent.getStringExtra("user_id")
                val arrivedID = intent.getStringExtra("arrived_id")
                val destination = vanList[position].destination

                if(destination == "Tayabas City"){
                    var intent = Intent(this@CommuterDashboard, TayabasReservations::class.java)
                    intent.putExtra("fcmNumber", vanList[position].fcmNumber)
                    intent.putExtra("id", vanList[position].id)
                    intent.putExtra("destination", vanList[position].destination)
                    intent.putExtra("driverName", vanList[position].driverName)
                    intent.putExtra("fare", vanList[position].fareRate)
                    startActivity(intent)
                    finish()
                }
                if(destination == "Mauban City"){
                    var intent = Intent(this@CommuterDashboard, MaubanReservation::class.java)
                    intent.putExtra("fcmNumber", vanList[position].fcmNumber)
                    intent.putExtra("id", vanList[position].id)
                    intent.putExtra("destination", vanList[position].destination)
                    startActivity(intent)
                    finish()
                }
                if(destination == "Atimonan City"){
                    var intent = Intent(this@CommuterDashboard, AtimonanReservation::class.java)
                    intent.putExtra("fcmNumber", vanList[position].fcmNumber)
                    intent.putExtra("id", vanList[position].id)
                    intent.putExtra("destination", vanList[position].destination)
                    startActivity(intent)
                    finish()
                }
                if(destination == "San Andres City"){
                    var intent = Intent(this@CommuterDashboard, SanAndresReservation::class.java)
                    intent.putExtra("fcmNumber", vanList[position].fcmNumber)
                    intent.putExtra("id", vanList[position].id)
                    intent.putExtra("destination", vanList[position].destination)
                    startActivity(intent)
                    finish()
                }


            }
        })



    }

    override fun onBackPressed() {
        val userAccount = intent.getStringExtra("user_account")
        val userID = intent.getStringExtra("user_id")
        val arrivedID = intent.getStringExtra("arrived_id")

        val intent = Intent(this@CommuterDashboard, CommuterDashboard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("user_id", userID)
        intent.putExtra("user_account", userAccount)
        intent.putExtra("arrived_id", arrivedID)
        startActivity(intent)
    }


}