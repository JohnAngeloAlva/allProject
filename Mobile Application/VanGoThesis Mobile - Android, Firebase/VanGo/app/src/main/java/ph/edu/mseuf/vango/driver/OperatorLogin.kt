package ph.edu.vangodriver

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import ph.edu.mseuf.vango.databinding.DriverLoginBinding
import java.util.concurrent.TimeUnit

class OperatorLogin : AppCompatActivity() {

    private lateinit var binding: DriverLoginBinding
    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken?=null
    private var mcallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks?=null
    private var mVerificationID: String?=null
    private lateinit var firebaseAuth: FirebaseAuth

    private val TAG ="MAIN_TAG"

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DriverLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLl.visibility = View.VISIBLE
        binding.codeLl.visibility = View.GONE


        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        mcallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                Log.d(TAG,"onVerificationCompleted: ")
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                progressDialog.dismiss()
                Log.d(TAG,"onVerificationfailed: ${e.message} ")
                Toast.makeText(this@OperatorLogin, "${e.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(TAG,"onCodeSent: $verificationId")
                mVerificationID = verificationId
                forceResendingToken = token
                progressDialog.dismiss()

                Log.d(TAG,"onCodeSent: $verificationId")

                binding.phoneLl.visibility = View.GONE
                binding.codeLl.visibility = View.VISIBLE
                Toast.makeText(this@OperatorLogin, "Verification Code Sent", Toast.LENGTH_SHORT).show()
                binding.codeSentDescriptionTv.text = "Please enter the verification code we sent to ${binding.phonET.text.toString().trim()}"
            }
        }

        binding.phoneContinueBtn.setOnClickListener {
            val phone = binding.phonET.text.toString().trim()

            if (TextUtils.isEmpty(phone)){
                Toast.makeText(this@OperatorLogin, "Please Enter Phone Number", Toast.LENGTH_SHORT).show()
            }
            else {

                startPhoneNumberVerification(phone)
            }
        }

        binding.resendCode.setOnClickListener {
            val phone = binding.phonET.text.toString().trim()
            if (TextUtils.isEmpty(phone)){
                Toast.makeText(this@OperatorLogin, "Please Enter Phone Number", Toast.LENGTH_SHORT).show()
            }
            else {
                forceResendingToken?.let { it1 -> resendVerificationCode(phone, it1) }
            }
        }

        binding.codeSubmitBtn.setOnClickListener {
            val code = binding.codeET.text.toString().trim()
            if (TextUtils.isEmpty(code)){
                Toast.makeText(this@OperatorLogin, "Please Enter verification code", Toast.LENGTH_SHORT).show()
            }
            else {
                verifyPhoneNumberWithCode(mVerificationID, code)
            }
        }

    }

    private fun startPhoneNumberVerification(phone:String){
        Log.d(TAG,"startPhoneNumberVerification: $phone")
        progressDialog.setMessage("Verifying Phone Number...")
        progressDialog.show()

        val options = mcallBacks?.let {
            PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(it)
                .build()
        }

        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    private fun resendVerificationCode(phone:String, token: PhoneAuthProvider.ForceResendingToken){
        progressDialog.setMessage("Resending Code...")
        progressDialog.show()

        Log.d(TAG,"resendVerificationCode: $phone")

        val options = mcallBacks?.let {
            PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(it)
                .setForceResendingToken(token)
                .build()
        }

        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code:String ){
        Log.d(TAG,"verifyPhoneNumberWithCode: $verificationId $code")
        progressDialog.setMessage("Verifying Code...")
        progressDialog.show()

        val credential = verificationId?.let { PhoneAuthProvider.getCredential(it,code) }
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential?) {
        Log.d(TAG,"signInWithPhoneAuthCredential: ")
        progressDialog.setMessage("Logging In...")

        if (credential != null) {
            firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    val phone = firebaseAuth.currentUser?.phoneNumber
                    Toast.makeText(this, "Logged In as $phone", Toast.LENGTH_SHORT).show()

                    val terminal = intent.getStringExtra("Title")

                    var intent = Intent(this@OperatorLogin, OperatorHomepage::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("Title", terminal)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {e->
                    progressDialog.dismiss()
                    Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

}