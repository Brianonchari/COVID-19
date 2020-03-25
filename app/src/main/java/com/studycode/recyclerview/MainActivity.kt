package com.studycode.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.studycode.recyclerview.ui.countries.CountriesFragment
import com.studycode.recyclerview.ui.global.GlobalFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val onNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            R.id.countries->{
                println("Pressed")
                replaceFragment(CountriesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.global->{
                println("Pressed")
                replaceFragment(GlobalFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(GlobalFragment())
        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener)

//        val repository = CountriesRepository(Api())
//
//        GlobalScope.launch(Dispatchers.Main){
//            val  data = repository.getCountriesCases()
//            Toast.makeText(this@MainActivity, data.toString(), Toast.LENGTH_LONG).show()
//        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction=supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }
}
