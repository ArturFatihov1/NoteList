package com.example.notelist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notelist.R
import com.example.notelist.database.User
import com.example.notelist.database.UserDatabase
import com.example.notelist.databinding.FragmentAddBinding
import com.example.notelist.user.UserViewModel
import com.example.notelist.user.UserViewModelFactory

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initViewModel()
    }

    private fun initUi() {
        val controller = findNavController()

        binding.floatActButtonAddNote.setOnClickListener {
            val user = User(null, binding.edTitle.text.toString(), binding.edNote.text.toString())
            userViewModel.insert(user)
            controller.navigate(R.id.action_addFragment_to_mainFragment)
        }
        binding.includeAdd.backArrow.setOnClickListener {
            controller.navigate(R.id.action_addFragment_to_mainFragment)
        }
    }

    private fun initViewModel() {
        val dao = UserDatabase.getInstance(requireContext()).userDAO
        val viewModelFactory = UserViewModelFactory(dao)
        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }

}