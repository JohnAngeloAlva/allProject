package ph.edu.mseuf.vango.driver

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import ph.edu.mseuf.vango.R
import ph.edu.mseuf.vango.databinding.ActivityDriverViewVanListBinding
import ph.edu.mseuf.vango.databinding.BottomAddrouteBinding
import ph.edu.mseuf.vango.databinding.SuccessAddrouteBinding
import ph.edu.mseuf.vango.databinding.VanListBinding
import ph.edu.mseuf.vango.driver.DriverAtimonan
import ph.edu.mseuf.vango.driver.DriverMauban
import ph.edu.mseuf.vango.driver.DriverSanAndres
import ph.edu.mseuf.vango.driver.DriverTayabas
import ph.edu.vangodriver.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DriverViewVanList : AppCompatActivity() {
    private lateinit var binding: ActivityDriverViewVanListBinding
    private lateinit var dbref: DatabaseReference
    private lateinit var userRef: DatabaseReference
    private lateinit var vanRecyclerView: RecyclerView
    private lateinit var vanList: ArrayList<Route>
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDriverViewVanListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.ic_home -> startActivity(Intent(this, MainActivity::class.java))
                R.id.ic_profile -> startActivity(Intent(this, DriverProfile::class.java))

            }
            true
        }

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
        if(vanTerminal == "SM Lucena City Terminal"){
            var adapter = AdapterVan(vanList)

            dbref = FirebaseDatabase.getInstance().getReference("smTerminal")
            userRef = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
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
                        this@DriverViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })

            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener{
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    userRef.addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val dest = snapshot.child(auth.currentUser!!.uid).child("destination").value

                            if(dest == "Tayabas City"){

                                var intent = Intent(this@DriverViewVanList, DriverTayabas::class.java)
                                intent.putExtra("Id", vanList[position].id)
                                intent.putExtra("Name", vanList[position].driverName)
                                intent.putExtra("Fare", vanList[position].fareRate)
                                intent.putExtra("Destination", vanList[position].destination)
                                intent.putExtra("EmailAccount", vanList[position].emailAccount)
                                intent.putExtra("PlateNumber", vanList[position].plateNumber)
                                intent.putExtra("FcmNumber", vanList[position].fcmNumber)
                                intent.putExtra("user_account", userAccount)
                                intent.putExtra("user_id", userID)
                                intent.putExtra("vanTerminal", vanTerminal)
                                intent.putExtra("arrived_id", arrivedID)
                                startActivity(intent)
                            }
                            if(dest == "Mauban City"){

                                var intent = Intent(this@DriverViewVanList, DriverMauban::class.java)
                                intent.putExtra("Id", vanList[position].id)
                                intent.putExtra("Name", vanList[position].driverName)
                                intent.putExtra("Fare", vanList[position].fareRate)
                                intent.putExtra("Destination", vanList[position].destination)
                                intent.putExtra("EmailAccount", vanList[position].emailAccount)
                                intent.putExtra("PlateNumber", vanList[position].plateNumber)
                                intent.putExtra("FcmNumber", vanList[position].fcmNumber)
                                intent.putExtra("user_account", userAccount)
                                intent.putExtra("user_id", userID)
                                intent.putExtra("vanTerminal", vanTerminal)
                                intent.putExtra("arrived_id", arrivedID)
                                startActivity(intent)
                            }
                            if(dest == "San Andres City"){

                                var intent = Intent(this@DriverViewVanList, DriverSanAndres::class.java)
                                intent.putExtra("Id", vanList[position].id)
                                intent.putExtra("Name", vanList[position].driverName)
                                intent.putExtra("Fare", vanList[position].fareRate)
                                intent.putExtra("Destination", vanList[position].destination)
                                intent.putExtra("EmailAccount", vanList[position].emailAccount)
                                intent.putExtra("PlateNumber", vanList[position].plateNumber)
                                intent.putExtra("FcmNumber", vanList[position].fcmNumber)
                                intent.putExtra("user_account", userAccount)
                                intent.putExtra("user_id", userID)
                                intent.putExtra("vanTerminal", vanTerminal)
                                intent.putExtra("arrived_id", arrivedID)
                                startActivity(intent)
                            }
                            if(dest == "Atimonan City"){

                                var intent = Intent(this@DriverViewVanList, DriverAtimonan::class.java)
                                intent.putExtra("Id", vanList[position].id)
                                intent.putExtra("Name", vanList[position].driverName)
                                intent.putExtra("Fare", vanList[position].fareRate)
                                intent.putExtra("Destination", vanList[position].destination)
                                intent.putExtra("EmailAccount", vanList[position].emailAccount)
                                intent.putExtra("PlateNumber", vanList[position].plateNumber)
                                intent.putExtra("FcmNumber", vanList[position].fcmNumber)
                                intent.putExtra("user_account", userAccount)
                                intent.putExtra("user_id", userID)
                                intent.putExtra("vanTerminal", vanTerminal)
                                intent.putExtra("arrived_id", arrivedID)
                                startActivity(intent)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })


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
                        this@DriverViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener{
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    var intent = Intent(this@DriverViewVanList, ShowRouteInfo::class.java)
                    intent.putExtra("Id", vanList[position].id)
                    intent.putExtra("Name", vanList[position].driverName)
                    intent.putExtra("Fare", vanList[position].fareRate)
                    intent.putExtra("Destination", vanList[position].destination)
                    intent.putExtra("EmailAccount", vanList[position].emailAccount)
                    intent.putExtra("PlateNumber", vanList[position].plateNumber)
                    intent.putExtra("FcmNumber", vanList[position].fcmNumber)
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
                        this@DriverViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener{
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    var intent = Intent(this@DriverViewVanList, ShowRouteInfo::class.java)
                    intent.putExtra("Id", vanList[position].id)
                    intent.putExtra("Name", vanList[position].driverName)
                    intent.putExtra("Fare", vanList[position].fareRate)
                    intent.putExtra("Destination", vanList[position].destination)
                    intent.putExtra("EmailAccount", vanList[position].emailAccount)
                    intent.putExtra("PlateNumber", vanList[position].plateNumber)
                    intent.putExtra("FcmNumber", vanList[position].fcmNumber)
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
                        this@DriverViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener{
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    var intent = Intent(this@DriverViewVanList, ShowRouteInfo::class.java)
                    intent.putExtra("Id", vanList[position].id)
                    intent.putExtra("Name", vanList[position].driverName)
                    intent.putExtra("Fare", vanList[position].fareRate)
                    intent.putExtra("Destination", vanList[position].destination)
                    intent.putExtra("EmailAccount", vanList[position].emailAccount)
                    intent.putExtra("PlateNumber", vanList[position].plateNumber)
                    intent.putExtra("FcmNumber", vanList[position].fcmNumber)
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
        val userAccount = intent.getStringExtra("user_account")
        val userID = intent.getStringExtra("user_id")
        val arrivedID = intent.getStringExtra("arrived_id")

        val intent = Intent(this@DriverViewVanList, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("user_id", userID)
        intent.putExtra("user_account", userAccount)
        intent.putExtra("arrived_id", arrivedID)
        startActivity(intent)
    }


}