package ph.edu.vangodriver

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import ph.edu.mseuf.vango.R
import ph.edu.mseuf.vango.User
import ph.edu.mseuf.vango.databinding.DriverAdditionalInfoBinding
import ph.edu.mseuf.vango.databinding.ProfileVerificationAlertBinding
import ph.edu.mseuf.vango.databinding.SuccessUseridBinding

class DriverAdditionalInfo : AppCompatActivity() {

    private lateinit var binding: DriverAdditionalInfoBinding
    private lateinit var auth: FirebaseAuth
    lateinit var filepathOR : Uri
    lateinit var filepathCR: Uri
    lateinit var filepathLicense : Uri
    lateinit var filepathProfile : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DriverAdditionalInfoBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        supportActionBar?.hide()


        val driverAccount = intent.getStringExtra("user_account")
        binding.textInputUsername.setText(driverAccount)
        binding.spinnerDestination.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.destinations,
            android.R.layout.simple_spinner_dropdown_item
        )
        binding.spinnerTerminal.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.terminals,
            android.R.layout.simple_spinner_dropdown_item )


        binding.btnSign.setOnClickListener {
            addInfo()
        }
        binding.uploadOR.setOnClickListener {
            uploadOR()
        }
        binding.uploadCR.setOnClickListener {
            uploadCR()
        }
        binding.uploadLicense.setOnClickListener {
            uploadLicense()
        }
        binding.uploadProfile.setOnClickListener {
            uploadProfileImage()
        }
    }

    //Choose Picture Profile
    private fun uploadProfileImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Choose Picture"), 114)

    }

    //Choose Picture License
    private fun uploadLicense() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Choose Picture"), 113)

    }

    //Choose Picture CR
    private fun uploadCR() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Choose Picture"), 112)
    }

    //Choose Picture OR
    private fun uploadOR() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Choose Picture"), 111)
    }

    //Uploading Image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Implement Chosen OR picture
        if(requestCode == 111 && resultCode == Activity.RESULT_OK && data != null){
            filepathOR = data.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepathOR)
            binding.imageOR.setImageBitmap(bitmap)
        }

        //Implement Chosen CR picture
        if(requestCode == 112 && resultCode == Activity.RESULT_OK && data != null){
            filepathCR = data.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepathCR)
            binding.imageCR.setImageBitmap(bitmap)
        }

        //Implement Chosen License picture
        if(requestCode == 113 && resultCode == Activity.RESULT_OK && data != null){
            filepathLicense = data.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepathLicense)
            binding.imageLicense.setImageBitmap(bitmap)
        }

        //Implement Chosen Profile picture
        if(requestCode == 114 && resultCode == Activity.RESULT_OK && data != null){
            filepathProfile  = data.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepathProfile )
            binding.imageProfile.setImageBitmap(bitmap)
        }
    }

    private fun addInfo() {

        val name = binding.textInputUsername.text.toString()
        if(filepathOR != null && filepathCR != null && filepathLicense != null){
            val pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()

            //Save image OR
            val imageRefOR = FirebaseStorage.getInstance().reference.child("imageOR/OR_of_${name}.jpg")
            imageRefOR.putFile(filepathOR)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()
                }
                .addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext,p0.message, Toast.LENGTH_SHORT).show()

                }
                .addOnProgressListener { p0 ->
                    val progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")
                }

            //Save image CR
            val imageRefCR = FirebaseStorage.getInstance().reference.child("imageCR/CR_of_${name}.jpg")
            imageRefCR.putFile(filepathCR)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()
                }
                .addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext,p0.message, Toast.LENGTH_SHORT).show()

                }
                .addOnProgressListener { p0 ->
                    val progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")
                }

            //Save image License
            val imageRefLicense = FirebaseStorage.getInstance().reference.child("imageLicense/License_of_${name}.jpg")
            imageRefLicense.putFile(filepathLicense)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()

                }
                .addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext,p0.message, Toast.LENGTH_SHORT).show()

                }
                .addOnProgressListener { p0 ->
                    val progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")
                }

            //Save image Profile
            val imageRefProfile = FirebaseStorage.getInstance().reference.child("imageProfile/Profile_of_${name}.jpg")
            imageRefProfile.putFile(filepathProfile)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()

                    Toast.makeText(applicationContext,"Credentials of van are Successfully Uploaded", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext,p0.message, Toast.LENGTH_SHORT).show()

                }
                .addOnProgressListener { p0 ->
                    val progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")
                }
        }
        val usernameID = FirebaseAuth.getInstance().currentUser!!.uid.toString()
        val username = binding.textInputUsername.text.toString()
        val fullName = binding.textInputName.text.toString()
        val age = binding.textInputAge.text.toString()
        val address = binding.textInputAddress.text.toString()
        val phoneNum = binding.textInputPhoneNum.text.toString()
        val vanModel = binding.textInputVanModel.text.toString()
        val plateNumber = binding.textInputPlateNumber.text.toString()
        val capacityVan = binding.textInputVanCapacity.text.toString()
        val destination = binding.spinnerDestination.selectedItem.toString()
        val terminal = binding.spinnerTerminal.selectedItem.toString()
        val imageOR : ImageView = binding.imageOR
        val imageCR : ImageView = binding.imageCR
        val imageLicense : ImageView = binding.imageLicense
        val imageProfile : ImageView = binding.imageProfile
        val id = "0"
        val verificationID = "0"

        if(username.isEmpty()){
            binding.textInputUsername.error = "Please enter username"
            binding.textInputName.requestFocus()
            return
        }
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
        if(vanModel.isEmpty()){
            binding.textInputVanModel.error = "Please enter your van model"
            binding.textInputVanModel.requestFocus()
            return
        }
        if(plateNumber.isEmpty()){
            binding.textInputPlateNumber.error = "Please enter your van plate number"
            binding.textInputPlateNumber.requestFocus()
            return
        }
        if(capacityVan.isEmpty()){
            binding.textInputVanCapacity.error = "Please enter your van capacity"
            binding.textInputVanCapacity.requestFocus()
            return
        }
        if(imageOR.drawable == null){
            binding.imageOR.requestFocus()
            return
        }
        if(imageCR.drawable == null){
            binding.imageCR.requestFocus()
            return
        }
        if(imageLicense.drawable == null){
            binding.imageLicense.requestFocus()
            return
        }
        if(imageProfile.drawable == null){
            binding.imageProfile.requestFocus()
            return
        }


        //Save in Database Function
        if (terminal == "SM City Lucena Terminal"){
            val user = auth.currentUser
            user?.sendEmailVerification()?.addOnCompleteListener { task ->
                val ref = FirebaseDatabase.getInstance().getReference("SmDriverProfile")
                val profiles = Profile(usernameID, username, fullName, age, address, phoneNum, id, vanModel, plateNumber, capacityVan, destination, verificationID, terminal)
                ref.child(usernameID).setValue(profiles)
                    .addOnCompleteListener { task ->
                        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                            ref.child(FirebaseAuth.getInstance().currentUser!!.uid).child("fcmNumber").setValue(task.result)
                        }



                        Toast.makeText(
                            this@DriverAdditionalInfo,
                            "Your Profile is Successfully Save ",
                            Toast.LENGTH_SHORT
                        ).show()
                        val mDialogView = ProfileVerificationAlertBinding.inflate(layoutInflater)
                        val mBuilder =
                            AlertDialog.Builder(this@DriverAdditionalInfo).setView(mDialogView.root)

                        val alert = mBuilder.show()
                        alert .window?.setBackgroundDrawableResource(android.R.color.transparent)

                        mDialogView.verification.setOnClickListener {
                            FirebaseAuth.getInstance().signOut()
                            val intent = Intent(this@DriverAdditionalInfo, DriverLoginEmail::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this@DriverAdditionalInfo,
                            "Your Profile is Failed to Save ",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
            }
                ?.addOnFailureListener {
                    Toast.makeText(
                        this@DriverAdditionalInfo,
                        "Your Profile is Failed to Save ",
                        Toast.LENGTH_SHORT
                    ).show()

                }
        }

        if (terminal == "Ibabang Dupay Talipapa Lucena City Terminal"){
            val ref = FirebaseDatabase.getInstance().getReference("TalipapaDriverProfile")
            val profiles = Profile(usernameID, username, fullName, age, address, phoneNum, id, vanModel, plateNumber, capacityVan, destination, verificationID, terminal)
            ref.child(usernameID).setValue(profiles)
                .addOnCompleteListener {
                    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                        ref.child(FirebaseAuth.getInstance().currentUser!!.uid).child("fcmNumber").setValue(task.result)
                    }
                    Toast.makeText(
                        this@DriverAdditionalInfo,
                        "Your Profile is Successfully Save ",
                        Toast.LENGTH_SHORT
                    ).show()
                    val mDialogView = ProfileVerificationAlertBinding.inflate(layoutInflater)
                    val mBuilder =
                        AlertDialog.Builder(this@DriverAdditionalInfo).setView(mDialogView.root)

                    val alert = mBuilder.show()
                    alert .window?.setBackgroundDrawableResource(android.R.color.transparent)

                    mDialogView.verification.setOnClickListener {
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this@DriverAdditionalInfo, DriverLoginEmail::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this@DriverAdditionalInfo,
                        "Your Profile is Failed to Save ",
                        Toast.LENGTH_SHORT
                    ).show()

                }
        }

        if (terminal == "Pacific Lucena City Terminal"){
            val ref = FirebaseDatabase.getInstance().getReference("PacificDriverProfile")
            val profiles = Profile(usernameID, username, fullName, age, address, phoneNum, id, vanModel, plateNumber, capacityVan, destination, verificationID, terminal)
            ref.child(usernameID).setValue(profiles)
                .addOnCompleteListener {
                    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                        ref.child(FirebaseAuth.getInstance().currentUser!!.uid).child("fcmNumber").setValue(task.result)
                    }
                    Toast.makeText(
                        this@DriverAdditionalInfo,
                        "Your Profile is Successfully Save ",
                        Toast.LENGTH_SHORT
                    ).show()
                    val mDialogView = ProfileVerificationAlertBinding.inflate(layoutInflater)
                    val mBuilder =
                        AlertDialog.Builder(this@DriverAdditionalInfo).setView(mDialogView.root)

                    val alert = mBuilder.show()
                    alert .window?.setBackgroundDrawableResource(android.R.color.transparent)

                    mDialogView.verification.setOnClickListener {
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this@DriverAdditionalInfo, DriverLoginEmail::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this@DriverAdditionalInfo,
                        "Your Profile is Failed to Save ",
                        Toast.LENGTH_SHORT
                    ).show()

                }
        }

        if (terminal == "Grand Terminal Lucena City"){
            val ref = FirebaseDatabase.getInstance().getReference("GrandDriverProfile")
            val profiles = Profile(usernameID, username, fullName, age, address, phoneNum, id, vanModel, plateNumber, capacityVan, destination, verificationID, terminal)
            ref.child(usernameID).setValue(profiles)
                .addOnCompleteListener {
                    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                        ref.child(FirebaseAuth.getInstance().currentUser!!.uid).child("fcmNumber").setValue(task.result)
                    }
                    Toast.makeText(
                        this@DriverAdditionalInfo,
                        "Your Profile is Successfully Save ",
                        Toast.LENGTH_SHORT
                    ).show()
                    val mDialogView = ProfileVerificationAlertBinding.inflate(layoutInflater)
                    val mBuilder =
                        AlertDialog.Builder(this@DriverAdditionalInfo).setView(mDialogView.root)

                    val alert = mBuilder.show()
                    alert .window?.setBackgroundDrawableResource(android.R.color.transparent)
                    mDialogView.verification.setOnClickListener {
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this@DriverAdditionalInfo, DriverLoginEmail::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this@DriverAdditionalInfo,
                        "Your Profile is Failed to Save ",
                        Toast.LENGTH_SHORT
                    ).show()

                }
        }

    }



}