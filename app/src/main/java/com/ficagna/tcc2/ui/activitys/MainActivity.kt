package com.ficagna.tcc2.ui.activitys

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.ficagna.tcc2.R
import com.ficagna.tcc2.ui.fragments.HomeFragment

class MainActivity : AppCompatActivity(), HomeFragment.onClickSelected {

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
               setContentView(R.layout.activity_main)

    }

    override fun initClicks(button: Button) {
        TODO("Not yet implemented")
    }

}