package com.ficagna.emausPoaRs.ui.activitys

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ficagna.emausPoaRs.R
import com.ficagna.emausPoaRs.databinding.ActivityMainBinding
import com.ficagna.emausPoaRs.ui.fragments.CalendarFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        return when (item.itemId) {
            R.id.item_home -> {
                navController.navigate(R.id.homeFragment)
                true
            }

            R.id.item_musicas -> {
                navController.navigate(R.id.musicasFragment)
                true
            }

            R.id.item_oracoes -> {
                navController.navigate(R.id.oracoesFragment)
                true
            }

            R.id.item_eventos -> {
                navController.navigate(R.id.eventosFragment)
                true
            }

            R.id.item_perfil -> {
                navController.navigate(R.id.profileFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}






