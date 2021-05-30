package com.plete.otokko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.plete.otokko.fragment.AkunFragment
import com.plete.otokko.fragment.HomeFragment
import com.plete.otokko.fragment.KeranjangFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val home = HomeFragment()
    private val keranjang = KeranjangFragment()
    private val akun = AkunFragment()
    private val fm: FragmentManager = supportFragmentManager
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomNav()
    }

    private fun setCurrentFragment(fragment: Fragment) = fm.beginTransaction().apply{
        replace(R.id.container, fragment)
        commit()
    }

    private fun setBottomNav(){
        setCurrentFragment(home)
        bottomNavigationView = nav_view
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navigation_home -> setCurrentFragment(home)
                R.id.navigation_keranjang -> setCurrentFragment(keranjang)
                R.id.navigation_akun -> setCurrentFragment(akun)
            }
            true
        }
    }
}