package com.ficagna.emausPoaRs.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ficagna.emausPoaRs.databinding.FragmentFormCadastroBinding
import com.ficagna.emausPoaRs.helper.FirebaseHelper
import com.ficagna.emausPoaRs.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class FormCadastroFragment : Fragment() {

    private var _binding: FragmentFormCadastroBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFormCadastroBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        initClicks()

    }

    private fun initClicks() {
        binding.btCadastrar.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val nome = binding.etNome.text.toString().trim()
        val email = binding.etEmailCadastro.text.toString().trim()
        val senha = binding.etSenhaCadastro.text.toString().trim()

        if (email.isNotEmpty()) {
            if (senha.isNotEmpty()) {

                cadastrarUser(nome, email, senha)

            } else {
                Toast.makeText(requireContext(), "Informe seu e-mail", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Informe sua senha", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cadastrarUser(nome: String, email: String, senha: String) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_formCadastroFragment_to_formLoginFragment)
                } else {
                    Toast.makeText(
                        requireContext(), FirebaseHelper.validError(task.exception?.message ?: ""),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}