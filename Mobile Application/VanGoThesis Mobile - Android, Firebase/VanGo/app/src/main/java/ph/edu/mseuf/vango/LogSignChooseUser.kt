package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import ph.edu.mseuf.vango.databinding.ActivityLogSignChooseUserBinding
import ph.edu.mseuf.vango.databinding.OperatorTerminalAlertBinding
import ph.edu.vangodriver.DriverLoginEmail
import ph.edu.vangodriver.DriverSignup
import ph.edu.vangodriver.OperatorHomepage

class LogSignChooseUser : AppCompatActivity() {
    private lateinit var binding : ActivityLogSignChooseUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogSignChooseUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCommuter.setOnClickListener {
            startActivity(Intent(this, CommuterLogins::class.java))
        }
        binding.btnDriver.setOnClickListener{
            startActivity(Intent(this, DriverLoginEmail::class.java))
        }
        binding.btnOperator.setOnClickListener{
            val mDialogView = OperatorTerminalAlertBinding.inflate(layoutInflater)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView.root)

            mBuilder.show()

            val smTerminal = "SM Lucena City Terminal"
            val pacficTerminal = "Pacific Lucena City Terminal"
            val talipapaTerminal = "Ibabang Dupay Talipapa Lucena City Terminal"
            val grandTerminal = "Grand Terminal Lucena City"

            mDialogView.sm.setOnClickListener {
                val intent = Intent(this, OperatorHomepage::class.java)
                intent.putExtra("Title", smTerminal)
                startActivity(intent)
            }
            mDialogView.talipapa.setOnClickListener {
                val intent = Intent(this, OperatorHomepage::class.java)
                intent.putExtra("Title", talipapaTerminal)
                startActivity(intent)
            }
            mDialogView.pacific.setOnClickListener {
                val intent = Intent(this, OperatorHomepage::class.java)
                intent.putExtra("Title", pacficTerminal)
                startActivity(intent)
            }
            mDialogView.grand.setOnClickListener {
                val intent = Intent(this, OperatorHomepage::class.java)
                intent.putExtra("Title", grandTerminal)
                startActivity(intent)
            }
        }


    }
}