package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import ph.edu.mseuf.vango.databinding.ActivityVerifyEmailBinding
import ph.edu.mseuf.vango.databinding.FullBinding


class full : AppCompatActivity() {
    private lateinit var binding: FullBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FullBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        supportActionBar?.hide()

        binding.termsServices.setOnClickListener {
            startActivity(Intent(this, TermsAndCondition::class.java))
        }

        binding.privacyPolicy.setOnClickListener {
            startActivity(Intent(this, PrivacyPolicy::class.java))
        }

        binding.btnSignup.setOnClickListener{
            signUpUser()
        }
        binding.loginBackButton.setOnClickListener{
            startActivity(Intent(this, CommuterLogins::class.java))
        }
    }

    fun signUpUser() {
        val email = binding.textInputEmail.text.toString()
        val fullName = binding.textInputName.text.toString()
        val age = binding.textInputAge.text.toString()
        val address = binding.textInputAddress.text.toString()
        val phoneNum = binding.textInputPhoneNum.text.toString()
        val seatID = "0"
        if(binding.textInputEmail.text.toString().isEmpty()){
            binding.textInputEmail.error = "Please enter email"
            binding.textInputEmail.requestFocus()
            return
        }


        // email pattern
        if(!Patterns.EMAIL_ADDRESS.matcher(binding.textInputEmail.text.toString()).matches()){
            binding.textInputEmail.error = "Please enter valid email"
            binding.textInputEmail.requestFocus()
            return
        }

        // if password is empty
        if(binding.textInputPassword.text.toString().isEmpty()){
            binding.textInputPassword.error = "Please enter password"
            binding.textInputPassword.requestFocus()
            return
        }

        // if both password are not the same
        if(binding.textInputCPassword.text.toString() != binding.textInputPassword.text.toString()){
            binding.textInputCPassword.error = "Password do not match"
            binding.textInputCPassword.requestFocus()
            return
        }

        if (fullName.isEmpty()) {
            binding.textInputName.error = "Please enter name"
            binding.textInputName.requestFocus()
            return
        }

        if (age.isEmpty()) {
            binding.textInputAge.error = "Please enter your age"
            binding.textInputAge.requestFocus()
            return
        }

        if (address.isEmpty()) {
            binding.textInputAddress.error = "Please enter your address"
            binding.textInputAddress.requestFocus()
            return
        }

        if (phoneNum.isEmpty()) {
            binding.textInputPhoneNum.error = "Please enter your phone number"
            binding.textInputPhoneNum.requestFocus()
            return
        }
        // create account
        auth.createUserWithEmailAndPassword(binding.textInputEmail.text.toString(),  binding.textInputPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->

                            // if create account successful open additional info activity

                            val uid: String? = auth.uid
                            val user = User(email, fullName, age, address, phoneNum, seatID)
                            database = FirebaseDatabase.getInstance().getReference("Users")
                            if (uid != null) {
                                database.child(uid).setValue(user).addOnCompleteListener {

                                    if (task.isSuccessful) {
                                        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                                            database.child(auth.currentUser!!.uid).child("fcmNumber").setValue(task.result)

                                        }
                                        val view = ActivityVerifyEmailBinding.inflate(layoutInflater)

                                        val builder = AlertDialog.Builder(this)
                                        builder.setView(view.root)

                                        val dialog = builder.create()
                                        dialog.show()
                                        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                                        dialog.setCanceledOnTouchOutside(true)


                                        view.btnVerify.setOnClickListener {
                                            val i =
                                                packageManager.getLaunchIntentForPackage("com.google.android.gm")
                                            if (i != null) {
                                                startActivity(i)
                                            }
                                        }

                                        view.btnCancel.setOnClickListener {
                                            dialog.dismiss()
                                        }
                                        view.btnLoginPage.setOnClickListener {
                                            startActivity(Intent(this, CommuterLogins::class.java))
                                        }
                                    }else {
                                        Toast.makeText(
                                            baseContext, "Sign up failed",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                }else {
                    Toast.makeText(
                        baseContext, "Task failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }


}





