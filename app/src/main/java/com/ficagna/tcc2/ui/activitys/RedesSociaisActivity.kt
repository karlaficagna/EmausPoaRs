package com.ficagna.tcc2.ui.activitys

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ficagna.tcc2.databinding.ActivityRedesSociaisBinding


class RedesSociaisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRedesSociaisBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedesSociaisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getLinks()
    }

    private fun getLinks() {
        binding.btInstagram.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/emauspoa/")
                )
            )
            binding.btInstagram1.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/emauspoa/")
                    )
                )
                binding.btFacebook.setOnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/EmausPortoAlegre/")
                        )
                    )
                }
                binding.btFacebook1.setOnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/EmausPortoAlegre/")
                        )
                    )
                }
                binding.btYoutube.setOnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/emauspoa")
                        )
                    )
                }
                binding.btYoutube1.setOnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/emauspoa")
                        )
                    )
                }
                binding.btHomepage.setOnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://www.emauspoa.com.br/index2.html")
                        )
                    )
                }
                binding.btHomepage1.setOnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://www.emauspoa.com.br/index2.html")
                        )
                    )
                }


            }

        }
    }
}