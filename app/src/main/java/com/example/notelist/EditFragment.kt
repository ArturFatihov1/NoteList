package com.example.notelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notelist.database.User
import com.example.notelist.database.UserDatabase
import com.example.notelist.databinding.FragmentEditBinding
import com.example.notelist.user.UserViewModel
import com.example.notelist.user.UserViewModelFactory

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditBinding.inflate(layoutInflater, container, false)

        initViewModel()
        init()

        return binding.root
    }

    private fun init() {
        binding.floatActButtonAddNote.setOnClickListener {
            val user = User(0, binding.edTitle.text.toString(), binding.edNote.text.toString())
            userViewModel.insert(user)
            val controller = findNavController()
            controller.navigate(R.id.action_editFragment_to_mainFragment)
        }
    }


    private fun initViewModel() {
        val dao = UserDatabase.getInstance(requireContext()).userDAO
        val viewModelFactory = UserViewModelFactory(dao)
        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }



}