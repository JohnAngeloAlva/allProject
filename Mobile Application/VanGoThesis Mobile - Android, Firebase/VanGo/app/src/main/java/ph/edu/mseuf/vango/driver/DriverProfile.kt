package ph.edu.vangodriver

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import ph.edu.mseuf.vango.R
import ph.edu.mseuf.vango.WelcomeScreens
import ph.edu.mseuf.vango.databinding.DriverProfileBinding
import ph.edu.mseuf.vango.databinding.EditProfileBinding
import java.io.File

class DriverProfile : AppCompatActivity() {
    private lateinit var binding: DriverProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var database: DatabaseReference
    private lateinit var vanRecyclerView : RecyclerView
    private lateinit var vanArrivedList :ArrayList<SettingArrivedVan>
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DriverProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnEdit.setOnClickListener {
            editProfile()
        }
        binding.btnLogOut.setOnClickListener {
            startActivity(Intent(this, WelcomeScreens::class.java))
        }

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_home -> {
                    var intent = Intent(this@DriverProfile, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                R.id.ic_profile -> {
                    var intent = Intent(this@DriverProfile, DriverProfile::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }

            }
            true
        }
        /*
        binding.history.setOnClickListener {
            val userName = intent.getStringExtra("user_id")
            serviceHistory(userName)
        }*/

        //Terminal Reference
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        val databaseSM = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
        databaseSM.child(userId).get().addOnSuccessListener {
            if (it.exists()) {

                val ref = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
                validateProfile(userId,ref)

            }
        }

        val databasePacific = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
        databasePacific.child(userId).get().addOnSuccessListener {
            if (it.exists()) {

                val ref = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
                validateProfile(userId,ref)

            }
        }
        val databaseTalipapa = FirebaseDatabase.getInstance().getReference("TalipapacDriverProfile")
        databaseTalipapa.child(userId).get().addOnSuccessListener {
            if (it.exists()) {

                val ref = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")
                validateProfile(userId,ref)

            }
        }
        val databaseGrand = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
        databaseGrand.child(userId).get().addOnSuccessListener {
            if (it.exists()) {

                val ref = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
                validateProfile(userId,ref)

            }
        }


    }

    private fun validateProfile(userId: String, ref: DatabaseReference) {
        ref.child(userId).get().addOnSuccessListener {

            if(it.exists()){

                val userID = it.child("userID").value
                val username = it.child("userName").value
                val name = it.child("fullName").value
                val age = it.child("age").value
                val address = it.child("address").value
                val number = it.child("phoneNum").value
                val vanModel = it.child("vanModel").value
                val plateNumber = it.child("plateNumber").value
                val vanCapacity = it.child("vanCapacity").value
                val destination = it.child("destination").value
                Toast.makeText(
                    this@DriverProfile,
                    "Read successfully",
                    Toast.LENGTH_SHORT
                ).show()
                binding.email.text = username.toString()
                binding.name.text = name.toString()
                binding.age.text = age.toString()
                binding.address.text = address.toString()
                binding.number.text = number.toString()
            }
            else{
                Toast.makeText(
                    this@DriverProfile,
                    "Username doest not exists",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.addOnFailureListener{
            Toast.makeText(
                this@DriverProfile,
                "Failed",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.textORVan.visibility = View.GONE
        binding.textCRVan.visibility = View.GONE
        binding.textLicenseDriver.visibility = View.GONE

        binding.imageOR.visibility = View.GONE
        binding.imageCR.visibility = View.GONE
        binding.imageLicense.visibility = View.GONE
        binding.imageProfile.visibility = View.GONE

        binding.getImage.setOnClickListener {

            binding.textORVan.visibility = View.VISIBLE
            binding.textCRVan.visibility = View.VISIBLE
            binding.textLicenseDriver.visibility = View.VISIBLE

            binding.imageOR.visibility = View.VISIBLE
            binding.imageCR.visibility = View.VISIBLE
            binding.imageLicense.visibility = View.VISIBLE
            binding.imageProfile.visibility = View.VISIBLE

            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Fetching Image.....")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val imageName = binding.email.text.toString()

            val storageRefOR =
                FirebaseStorage.getInstance().reference.child("imageOR/OR_of_${imageName.toString()}.jpg")
            val localFileOR = File.createTempFile("tempImage", "jpg")
            storageRefOR.getFile(localFileOR).addOnSuccessListener {

                val bitmap = BitmapFactory.decodeFile(localFileOR.absolutePath)
                binding.imageOR.setImageBitmap(bitmap)

            }
            val storageRefCR =
                FirebaseStorage.getInstance().reference.child("imageCR/CR_of_${imageName}.jpg")
            val localFileCR = File.createTempFile("tempImage", "jpg")
            storageRefCR.getFile(localFileCR).addOnSuccessListener {

                val bitmap = BitmapFactory.decodeFile(localFileCR.absolutePath)
                binding.imageCR.setImageBitmap(bitmap)

            }

            val storageRefLicense =
                FirebaseStorage.getInstance().reference.child("imageLicense/License_of_${imageName}.jpg")
            val localFileLicense = File.createTempFile("tempImage", "jpg")
            storageRefLicense.getFile(localFileLicense).addOnSuccessListener {

                val bitmap = BitmapFactory.decodeFile(localFileLicense.absolutePath)
                binding.imageLicense.setImageBitmap(bitmap)

            }

            val storageRefProfile =
                FirebaseStorage.getInstance().reference.child("imageProfile/Profile_of_${imageName}.jpg")
            val localFileProfile = File.createTempFile("tempImage", "jpg")
            storageRefProfile.getFile(localFileProfile).addOnSuccessListener {

                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }

                val bitmap = BitmapFactory.decodeFile(localFileProfile.absolutePath)
                binding.imageProfile.setImageBitmap(bitmap)

            }.addOnFailureListener {
                Toast.makeText(
                    this@DriverProfile,
                    "Failed to retrieve image",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
    /*
    private fun serviceHistory(userName: String?) {
        vanRecyclerView = findViewById(R.id.vanArrivedList)
        vanRecyclerView.layoutManager = LinearLayoutManager(this)
        vanRecyclerView.setHasFixedSize(true)

        vanArrivedList = arrayListOf()

        val adapter = AdapterArrivedVan(vanArrivedList)

        database = FirebaseDatabase.getInstance().getReference("ArrivedVan")
        val uid = firebaseAuth.uid

        val childEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                        val van = snapshot.getValue(SettingArrivedVan::class.java)
                        vanArrivedList.add(van!!)

                    vanRecyclerView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@DriverProfile,
                    error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        adapter.setOnItemClickListener(object : AdapterArrivedVan.onItemClickListener{
            override fun onItemClick(position: Int) {

                val userAccount = intent.getStringExtra("user_account")
                val userID = intent.getStringExtra("user_id")
                val arrivedID = intent.getStringExtra("arrived_id")

                var intent = Intent(this@DriverProfile, MainActivity::class.java)
                intent.putExtra("Id", vanArrivedList[position].id)
                intent.putExtra("user_account", userAccount)
                intent.putExtra("user_id", userID)
                intent.putExtra("arrived_id", arrivedID)
                startActivity(intent)
            }
        })

    }*/




    private fun editProfile() {
        val account = binding.email.text.toString()
        val fullname = binding.name.text.toString()
        val age = binding.age.text.toString()
        val address = binding.address.text.toString()
        val number = binding.number.text.toString()

        val mDialogView = EditProfileBinding.inflate(layoutInflater)
        val mBuilder = AlertDialog.Builder(this@DriverProfile).setView(mDialogView.root)

        val alertDialog = mBuilder.show()

        mDialogView.name.setText(fullname)
        mDialogView.age.setText(age)
        mDialogView.address.setText(address)
        mDialogView.number.setText(number)

        mDialogView.btnEdit.setOnClickListener {

            val name = mDialogView.name.text.toString()
            val ages = mDialogView.age.text.toString()
            val addresss = mDialogView.address.text.toString()
            val numbers = mDialogView.number.text.toString()

            mDatabase = FirebaseDatabase.getInstance().getReference("driverProfile")
            val user = mapOf<String,String>(
                "fullName" to name,
                "age" to ages,
                "address" to addresss,
                "phoneNum" to numbers,
            )
            mDatabase.child(firebaseAuth.currentUser!!.uid).updateChildren(user).addOnSuccessListener {
                Toast.makeText(
                    this@DriverProfile,
                    "Successfully Updated Profile",
                    Toast.LENGTH_SHORT
                ).show()
                alertDialog.dismiss()
                val intent = Intent(this@DriverProfile, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }.addOnFailureListener{
                Toast.makeText(
                    this@DriverProfile,
                    "Failed to updated Data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        mDialogView.cancel.setOnClickListener {
            alertDialog.dismiss()
        }

    }

    override fun onBackPressed() {
        val userName = intent.getStringExtra("user_id")
        val userAccount = intent.getStringExtra("user_account")

        val intent = Intent(this@DriverProfile, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("user_id", userName)
        intent.putExtra("user_account", userAccount)
        startActivity(intent)
    }



}


