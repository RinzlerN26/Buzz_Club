package com.example.buzzclub

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.buzzclub.Fragments.HomeFragment
import com.example.buzzclub.Fragments.NotificationFragment
import com.example.buzzclub.Fragments.ProfileFragment
import com.example.buzzclub.Fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    internal var selectedFragment:Fragment?=null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                moveToFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_search -> {
                moveToFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_addpost -> {
                item.isChecked=false
                startActivity(Intent(this@MainActivity,AddPostActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_notifications -> {
                moveToFragment(NotificationFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
              moveToFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.home_toolbar))

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val publisher = intent.getStringExtra("PUBLISHER_ID")
        if(publisher!=null) {
            val prefs: SharedPreferences.Editor? =
                getSharedPreferences("PREFS", Context.MODE_PRIVATE)
                    .edit().apply { putString("profileId", publisher); apply() }

            moveToFragment(ProfileFragment())
        }
        else
        //to call fragments
       moveToFragment(HomeFragment())
    }

    private fun moveToFragment(fragment:Fragment)
    {
        val fragmentTrans=supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.fragment_container,fragment)
        fragmentTrans.commit()
    }
}