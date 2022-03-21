package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.mseuf.vango.databinding.ActivityCommuterProfilesBinding

class CommuterProfiles : AppCompatActivity() {
    private lateinit var binding : ActivityCommuterProfilesBinding
    private lateinit var database : DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommuterProfilesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        database = FirebaseDatabase.getInstance().getReference("Users")
        auth = FirebaseAuth.getInstance()

        binding.btnLogOut.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this, CommuterLogins::class.java))
        }
        binding.bottomNavigationView.menu.getItem(1).isChecked = true
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.ic_profile -> startActivity(Intent(this, CommuterProfiles::class.java))
                R.id.ic_home -> startActivity(Intent(this, CommuterHome::class.java))


            }
            true
        }
        database.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child(auth.currentUser!!.uid).child("fullName").value
                val phone = snapshot.child(auth.currentUser!!.uid).child("phoneNum").value
                val age = snapshot.child(auth.currentUser!!.uid).child("age").value
                val address = snapshot.child(auth.currentUser!!.uid).child("address").value
                val email = snapshot.child(auth.currentUser!!.uid).child("email").value

                binding.txtFullName.text = name.toString()
                binding.txtPhone.text = phone.toString()
                binding.txtAge.text = age.toString()
                binding.textAddress.text = address.toString()
                binding.txtEmail.text = email.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

}