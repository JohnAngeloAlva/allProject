package ph.edu.vangodriver

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.mseuf.vango.databinding.VanListBinding
import ph.edu.mseuf.vango.driver.DriverAtimonan
import ph.edu.mseuf.vango.driver.DriverMauban
import ph.edu.mseuf.vango.driver.DriverSanAndres
import ph.edu.mseuf.vango.driver.DriverTayabas


class ViewVanList: AppCompatActivity() {
    private lateinit var binding : VanListBinding
    private lateinit var dbref :DatabaseReference
    private lateinit var userRef :DatabaseReference
    private lateinit var vanRecyclerView : RecyclerView
    private lateinit var vanList :ArrayList<Route>
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = VanListBinding.inflate(layoutInflater)
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
                        this@ViewVanList,
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
                                var intent = Intent(this@ViewVanList, DriverTayabas::class.java)
                                startActivity(intent)
                            }
                            if(dest == "Mauban City"){

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
                                var intent = Intent(this@ViewVanList, DriverMauban::class.java)
                                startActivity(intent)
                            }
                            if(dest == "San Andres City"){

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
                                var intent = Intent(this@ViewVanList, DriverSanAndres::class.java)
                                startActivity(intent)
                            }
                            if(dest == "Atimonan City"){

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
                                var intent = Intent(this@ViewVanList, DriverAtimonan::class.java)
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
                        this@ViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener{
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    var intent = Intent(this@ViewVanList, ShowRouteInfo::class.java)
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
                        this@ViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener{
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    var intent = Intent(this@ViewVanList, ShowRouteInfo::class.java)
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
                        this@ViewVanList,
                        error.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setOnItemClickListener(object : AdapterVan.onItemClickListener{
                override fun onItemClick(position: Int) {

                    val userAccount = intent.getStringExtra("user_account")
                    val userID = intent.getStringExtra("user_id")
                    val arrivedID = intent.getStringExtra("arrived_id")

                    var intent = Intent(this@ViewVanList, ShowRouteInfo::class.java)
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

        val intent = Intent(this@ViewVanList, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("user_id", userID)
        intent.putExtra("user_account", userAccount)
        intent.putExtra("arrived_id", arrivedID)
        startActivity(intent)
    }


}


