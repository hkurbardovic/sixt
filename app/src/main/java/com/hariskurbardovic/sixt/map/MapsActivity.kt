package com.hariskurbardovic.sixt.map

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hariskurbardovic.sixt.R
import com.hariskurbardovic.sixt.databinding.ActivityMapsBinding
import com.hariskurbardovic.sixt.map.fragments.ListFragment
import com.hariskurbardovic.sixt.map.fragments.MapFragment

class MapsActivity :
    AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMapsBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_maps
        )

        binding.bottomNavigationView?.setOnNavigationItemSelectedListener(this)

        // check if activity new instance
        if (resources.getBoolean(R.bool.isTablet)) {
            initBothFragments(MapFragment(), ListFragment())
        } else {
            if (savedInstanceState == null) {
                replaceFragment(MapFragment())
            }
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.map_fragment -> {
                replaceFragment(MapFragment())
            }
            R.id.list_fragment -> {
                replaceFragment(ListFragment())
            }
        }

        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }

    private fun initBothFragments(fragment: Fragment, fragment2: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.replace(R.id.nav_secondary_host_fragment, fragment2)
        fragmentTransaction.commit()
    }
}
