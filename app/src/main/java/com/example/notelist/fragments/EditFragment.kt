package com.example.notelist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notelist.R
import com.example.notelist.database.User
import com.example.notelist.database.UserDatabase
import com.example.notelist.databinding.FragmentEditBinding
import com.example.notelist.user.UserViewModel
import com.example.notelist.user.UserViewModelFactory


class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var userViewModel: UserViewModel
    private val oldNotes by navArgs<EditFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        binding.updateTitle.setText(oldNotes.data.title)
        binding.updateNote.setText(oldNotes.data.note)

        initViewModel()
        initUi()

        return binding.root
    }

    private fun initUi() {
        val controller = findNavController()

        binding.floatActButtonUpdateNote.setOnClickListener {
            val user = createUser()
            userViewModel.update(user)
            controller.navigate(R.id.action_editFragment_to_mainFragment)
        }
        binding.includeEdit.delete.setOnClickListener {
            val user = createUser()
            userViewModel.delete(user)
            controller.navigate(R.id.action_editFragment_to_mainFragment)
        }
        binding.includeEdit.backArrow.setOnClickListener {
            controller.navigate(R.id.action_editFragment_to_mainFragment)
        }
    }

    private fun createUser(): User {
        return User(
            oldNotes.data.userId,
            binding.updateTitle.text.toString(),
            binding.updateNote.text.toString()
        )
    }

    private fun initViewModel() {
        val dao = UserDatabase.getInstance(requireContext()).userDAO
        val viewModelFactory = UserViewModelFactory(dao)
        userViewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }
<<<<<<< HEAD
=======

>>>>>>> 40765c2154c8332b134076b7d952567fbcbc7f0a
}