package ph.edu.mseuf.vango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ph.edu.mseuf.vango.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lottie = binding.lottie
        val appName = binding.appName
        appName.animate().translationX((1500).toFloat()).setDuration(3000).startDelay = 0
        lottie.animate().translationX((2000).toFloat()).setDuration(3000).startDelay = 0


        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            startActivity(Intent(this, WelcomeScreens::class.java))
            finish()
        }, 3000)
    }
}

