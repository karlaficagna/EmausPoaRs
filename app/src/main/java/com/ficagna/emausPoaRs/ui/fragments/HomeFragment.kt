package com.ficagna.emausPoaRs.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ficagna.emausPoaRs.R
import com.ficagna.emausPoaRs.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
          auth = FirebaseAuth.getInstance()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun initClicks() {
        binding.button1.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }
        binding.button2.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_homeFragment_to_calendarActivity)
        }
        binding.button3.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_homeFragment_to_groupListFragment)
        }
        binding.button4.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_homeFragment_to_redesSociaisActivity)
        }
        binding.button5.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_homeFragment_to_contribuaActivity)
        }
        binding.button6.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_homeFragment_to_productFragment)
        }
        binding.button7.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_homeFragment_to_eventosFragment)
        }
        binding.button8.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_homeFragment_to_musicasFragment)
        }
        binding.button9.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()

    }
}








