package com.ficagna.emausPoaRs.ui.auth


import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ficagna.emausPoaRs.R
import com.ficagna.emausPoaRs.helper.FirebaseHelper
import com.ficagna.emausPoaRs.databinding.FragmentFormLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FormLoginFragment : Fragment() {

    private var _binding: FragmentFormLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFormLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("476072531837-ok1qojamfissbl9bbkq02ti4aujlubpr.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        initClicks()
    }

    private fun initClicks() {

        binding.btLogin.setOnClickListener {
            validateData()
            it.hideKeyboard()
        }
        binding.cadastro.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_formLoginFragment_to_formCadastroFragment)
        }
        binding.esqueciSenha.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_formLoginFragment_to_recoverAccountFragment)
        }
        binding.btGoogle.setOnClickListener {
            signIn()
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun validateData() {
        val email = binding.etEmail.text.toString().trim()
        val senha = binding.etSenha.text.toString().trim()

        if (email.isNotEmpty()) {
            if (senha.isNotEmpty()) {

                binding.progressbar.isVisible = true
                loginUser(email, senha)

            } else {
                Toast.makeText(requireContext(), "Digite sua senha", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Digite seu e-mail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_formLoginFragment_to_homeFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        FirebaseHelper.validError(task.exception?.message ?: ""),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                binding.progressbar.isVisible = false
            }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        abreActivity.launch(signInIntent)
    }

    var abreActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->

        if (result.resultCode == RESULT_OK) {
            val int = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(int)
            try {
                val conta = task.getResult(ApiException::class.java)
                loginGoogle(conta.idToken!!)

            } catch (e: ApiException) {

            }
        }
    }

    private fun loginGoogle(token: String) {
        val credencial = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credencial)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_formLoginFragment_to_homeFragment)
                    Log.d(TAG, "signInWithCredential:success")

                } else {
                    Toast.makeText(
                        requireContext(),
                        FirebaseHelper.validError(task.exception?.message ?: ""),
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








