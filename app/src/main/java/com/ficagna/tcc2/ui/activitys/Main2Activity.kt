package com.ficagna.tcc2.ui.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ficagna.tcc2.R
import com.ficagna.tcc2.databinding.ActivityMain2Binding
import com.ficagna.tcc2.ui.activitys.fragments.EventosFragment
import com.ficagna.tcc2.ui.activitys.fragments.HomeFragment
import com.ficagna.tcc2.ui.activitys.fragments.NoticiasFragment
import com.ficagna.tcc2.ui.activitys.fragments.ProfileFragment


class Main2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

//       replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> replaceFragment(HomeFragment())
                R.id.item_perfil -> replaceFragment(ProfileFragment())
                R.id.item_eventos -> replaceFragment(EventosFragment())
                R.id.item_noticias -> replaceFragment(NoticiasFragment())
                else -> {
                    replaceFragment(HomeFragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.constraint, fragment)
        fragmentTransaction.commit()
    }

}