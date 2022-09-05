package mx.itesm.bamx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var botomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dineroFragment = DineroFragment()
        var postulacionesFragment = PostulacionesFragment()
        var homeFragment = HomeFragment()

        botomNav = findViewById(R.id.bottomNavigationView2)


        botomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_coin -> {
                    serCurrentFragment(dineroFragment)
                    true
                }

                R.id.nav_person -> {
                    serCurrentFragment(postulacionesFragment)
                    true
                }


                R.id.nav_home -> {
                    serCurrentFragment(homeFragment)
                    true
                }
                else -> false
            }
        }

    }


    private  fun serCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView, fragment)
            commit()
        }


    }

}