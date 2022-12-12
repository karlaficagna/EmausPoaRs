package com.ficagna.tcc2.ui.activitys

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ficagna.tcc2.R
import com.ficagna.tcc2.databinding.ActivityContribuaBinding

class ContribuaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContribuaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContribuaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btPixLink.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://sejavero.com.br/verowallet/IO9_inT-ZpHry1BU5qX2Ug")
                )
            )
        }
    }


}
