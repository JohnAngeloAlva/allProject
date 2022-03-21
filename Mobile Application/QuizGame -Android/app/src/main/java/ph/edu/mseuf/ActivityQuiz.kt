package ph.edu.mseuf

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ph.edu.mseuf.databinding.ActivityQuizBinding

class ActivityQuiz: AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewName.text = intent.getStringExtra("Name")
        binding.textViewYear.text = intent.getStringExtra("Year")
    }

    fun SubmitAnswer(view: View){

        var q1 = when {
            binding.radioButton1A.isChecked -> binding.radioButton1A.text
            binding.radioButton1B.isChecked -> binding.radioButton1B.text
            binding.radioButton1C.isChecked -> binding.radioButton1C.text
            else -> binding.radioButton1D.text
        }

        var q2 = when {
            (binding.checkBoxA.isChecked) -> binding.checkBoxA.text
            (binding.checkBoxB.isChecked) -> binding.checkBoxB.text
            (binding.checkBoxC.isChecked) -> binding.checkBoxC.text
            else -> binding.checkBoxD.text
        }


        var q3 = when {
            binding.radioButton3A.isChecked -> binding.radioButton3A.text
            binding.radioButton3B.isChecked -> binding.radioButton3B.text
            binding.radioButton3C.isChecked -> binding.radioButton3C.text
            else -> binding.radioButton3D.text
        }

        var name = binding.textViewName.text
        var year = binding.textViewYear.text

        val intent = Intent(this, DisplayScore::class.java)
        intent.putExtra("Name","$name")
        intent.putExtra("Year",  "$year")
        intent.putExtra("Question1","$q1")
        intent.putExtra("Question2","$q2")
        intent.putExtra("Question3","$q3")
        startActivity(intent)
    }

}