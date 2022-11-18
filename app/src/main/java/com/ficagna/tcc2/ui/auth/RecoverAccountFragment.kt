package com.ficagna.tcc2.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.ficagna.tcc2.databinding.FragmentRecoverAccountBinding
import com.ficagna.tcc2.helper.BaseFragment
import com.ficagna.tcc2.helper.FirebaseHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecoverAccountFragment : BaseFragment() {

    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        initClicks()
    }

    private fun initClicks() {
        binding.btEnviar.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val email = binding.etEmail.text.toString().trim()

        if (email.isNotEmpty()) {
            hideKeyboard()

            binding.progressbar.isVisible = true

            recoverAccountUser(email)
        } else {
            Toast.makeText(requireContext(), "Informe seu E-mal", Toast.LENGTH_SHORT).show()
        }
    }

    private fun recoverAccountUser(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(),"Verifique seu e-mail, acabamos de enviar um link para redefinição de senha",  Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(
                        requireContext(), FirebaseHelper.validError(task.exception?.message ?: ""),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                binding.progressbar.isVisible = false
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}