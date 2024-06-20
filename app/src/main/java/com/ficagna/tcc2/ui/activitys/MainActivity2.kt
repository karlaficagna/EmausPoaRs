package com.ficagna.tcc2.ui.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ficagna.tcc2.R
import com.ficagna.tcc2.databinding.Activity2MainBinding
import com.ficagna.tcc2.ui.fragments.AtividadesFragment
import com.ficagna.tcc2.ui.fragments.HomeFragment
import com.ficagna.tcc2.ui.fragments.NoticiasFragment
import com.ficagna.tcc2.ui.fragments.ProfileFragment


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: Activity2MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> replaceFragment(HomeFragment())
                R.id.item_perfil -> replaceFragment(ProfileFragment())
                R.id.item_eventos -> replaceFragment(AtividadesFragment())
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
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}