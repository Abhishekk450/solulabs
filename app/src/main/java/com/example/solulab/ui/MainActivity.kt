package com.example.solulab.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.solulab.R
import com.example.solulab.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    var homeFragment = HomeFragment()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when(item.itemId) {
          R.id.home -> {
              supportFragmentManager.beginTransaction().replace(R.id.container,homeFragment).commit()
              return true
          }
      }
       return false
    }
}