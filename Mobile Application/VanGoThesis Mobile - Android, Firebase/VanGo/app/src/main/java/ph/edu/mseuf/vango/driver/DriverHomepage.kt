package ph.edu.vangodriver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import ph.edu.mseuf.vango.databinding.DriverHomepageBinding
import ph.edu.mseuf.vango.databinding.DriverLoginChoiceBinding
import ph.edu.mseuf.vango.databinding.DriverSignupChoiceBinding
import ph.edu.mseuf.vango.databinding.OperatorTerminalAlertBinding
import java.lang.Exception

class DriverHomepage : AppCompatActivity() {
    private lateinit var binding: DriverHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DriverHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // go to login page

        binding.btnLogin.setOnClickListener{
            val mDialogView = DriverLoginChoiceBinding.inflate(layoutInflater)
            val mBuilder =
                AlertDialog.Builder(this@DriverHomepage).setView(mDialogView.root)

            mBuilder.show()

            mDialogView.phoneNumberLogin.setOnClickListener {
                startActivity(Intent(this, DriverLogin::class.java))
            }
            mDialogView.emailLogin.setOnClickListener {
                startActivity(Intent(this, DriverLoginEmail::class.java))
            }

        }

        // go to sign up page
        binding.btnSignUp.setOnClickListener{
            val mDialogView = DriverSignupChoiceBinding.inflate(layoutInflater)
            val mBuilder =
                AlertDialog.Builder(this@DriverHomepage).setView(mDialogView.root)

            mBuilder.show()

            mDialogView.phoneNumberSignup.setOnClickListener {
                startActivity(Intent(this, DriverSignupNumber::class.java))
            }
            mDialogView.emailSignup.setOnClickListener {
                startActivity(Intent(this, DriverSignup::class.java))
            }
        }

        //go to operator
        binding.logo.setOnClickListener {
            val mDialogView = OperatorTerminalAlertBinding.inflate(layoutInflater)
            val mBuilder =
                AlertDialog.Builder(this@DriverHomepage).setView(mDialogView.root)

            mBuilder.show()

            val smTerminal = "SM Lucena City Terminal"
            val pacficTerminal = "Pacific Lucena City Terminal"
            val talipapaTerminal = "Ibabang Dupay Talipapa Lucena City Terminal"
            val grandTerminal = "Grand Terminal Lucena City"

            mDialogView.sm.setOnClickListener {
                val intent = Intent(this@DriverHomepage, OperatorHomepage::class.java)
                intent.putExtra("Title", smTerminal)
                startActivity(intent)
            }
            mDialogView.talipapa.setOnClickListener {
                val intent = Intent(this@DriverHomepage, OperatorHomepage::class.java)
                intent.putExtra("Title", talipapaTerminal)
                startActivity(intent)
            }
            mDialogView.pacific.setOnClickListener {
                val intent = Intent(this@DriverHomepage, OperatorHomepage::class.java)
                intent.putExtra("Title", pacficTerminal)
                startActivity(intent)
            }
            mDialogView.grand.setOnClickListener {
                val intent = Intent(this@DriverHomepage, OperatorHomepage::class.java)
                intent.putExtra("Title", grandTerminal)
                startActivity(intent)
            }

        }

    }
}