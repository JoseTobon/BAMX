package mx.itesm.bamx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun irADM(view : View?) {
        val intent = Intent(this, donacionesMonetarias::class.java)
        startActivity(intent)
    }

    fun irADE(view : View?) {
        val intent = Intent(this, donacionesEspecie::class.java)
        startActivity(intent)
    }
}