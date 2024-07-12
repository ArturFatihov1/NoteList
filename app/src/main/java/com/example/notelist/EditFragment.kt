package com.example.notelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notelist.database.User
import com.example.notelist.database.UserDatabase
import com.example.notelist.databinding.FragmentEditBinding
import com.example.notelist.user.UserViewModel
import com.example.notelist.user.UserViewModelFactory


class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var userViewModel: UserViewModel
    val oldNotes by navArgs<EditFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)

        binding.updateTitle.setText(oldNotes.data.title)
        binding.updateNote.setText(oldNotes.data.note)

        initViewModel()
        initUi()
        binding.include2.delete.setOnClickListener{
            val user = User(
                oldNotes.data.userId,
                binding.updateTitle.text.toString(),
                binding.updateNote.text.toString())
            userViewModel.delete(user)
            val controller = findNavController()
            controller.navigate(R.id.action_editFragment_to_mainFragment)
        }

        return binding.root
    }

    private fun initUi() {
        binding.floatActButtonUpdateNote.setOnClickListener {
            val user = User(
                oldNotes.data.userId,
                binding.updateTitle.text.toString(),
                binding.updateNote.text.toString())
            userViewModel.update(user)
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