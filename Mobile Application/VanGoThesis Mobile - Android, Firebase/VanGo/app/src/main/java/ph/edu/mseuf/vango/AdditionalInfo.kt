package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ph.edu.mseuf.vango.databinding.ActivityAdditionalInfoBinding
import ph.edu.mseuf.vango.databinding.ActivityVerifyEmailBinding

class AdditionalInfo : AppCompatActivity() {
    private lateinit var binding: ActivityAdditionalInfoBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        database = FirebaseDatabase.getInstance().getReference("Users")
        //sign in
        binding.btnSign.setOnClickListener {
            addInfo()

        }
    }
    fun addInfo(){
        var fullName = binding.textInputName.text.toString()
        var age = binding.textInputAge.text.toString()
        var address = binding.textInputAddress.text.toString()
        var phoneNum = binding.textInputPhoneNum.text.toString()

        if(fullName.isEmpty()){
            binding.textInputName.error = "Please enter name"
            binding.textInputName.requestFocus()
            return
        }

        if(age.isEmpty()){
            binding.textInputAge.error = "Please enter your age"
            binding.textInputAge.requestFocus()
            return
        }

        if(address.isEmpty()){
            binding.textInputAddress.error = "Please enter your address"
            binding.textInputAddress.requestFocus()
            return
        }

        if(phoneNum.isEmpty()){
            binding.textInputPhoneNum.error = "Please enter your phone number"
            binding.textInputPhoneNum.requestFocus()
            return
        }

        // saving data in database
        /** val user = User(fullName, age, address, phoneNum)
        database.child(fullName).setValue(user).addOnCompleteListener {

            if (it.isSuccessful) {
                // for dialog box of email verification
                val view = ActivityVerifyEmailBinding.inflate(layoutInflater)

                val builder = AlertDialog.Builder(this)
              builder.setView(view.root)

        val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                dialog.setCanceledOnTouchOutside(true)


                view.btnVerify.setOnClickListener {
                    val i = packageManager.getLaunchIntentForPackage("com.google.android.gm")
                    if (i != null) {
                        startActivity(i)
                    }
                }

                view.btnCancel.setOnClickListener{
                    dialog.dismiss()
                }
                view.btnLoginPage.setOnClickListener{
                    startActivity(Intent(this, CommuterLogin::class.java))
                }
            } else {
                Toast.makeText(baseContext, "Sign up failed", Toast.LENGTH_SHORT).show()
            }


        }*/
    }
}


