package com.example.notelist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notelist.R
import com.example.notelist.database.User
import com.example.notelist.database.UserDatabase
import com.example.notelist.databinding.FragmentMainBinding
import com.example.notelist.user.AdapterUser
import com.example.notelist.user.UserViewModel
import com.example.notelist.user.UserViewModelFactory

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: AdapterUser
    private var oldMyNotes = arrayListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        initViewModel()
        init()
        showUsersList()

        return binding.root
    }

    private fun initViewModel() {
        val dao = UserDatabase.getInstance(requireContext()).userDAO
        val viewModelFactory = UserViewModelFactory(dao)
        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }

    private fun init() {
        binding.floatActButton.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_mainFragment_to_addFragment)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFiltering(p0)
                return true
            }

        })

    }

    private fun notesFiltering(p0: String?) {
        val newFilteredList = arrayListOf<User>()

        oldMyNotes.forEach {
            if (it.title.contains(p0.toString()) || it.note.contains(p0.toString())) {
                newFilteredList.add(it)
            }
        }

        adapter.filtering(newFilteredList)
    }

    private fun showUsersList() {
        userViewModel.users.observe(viewLifecycleOwner) { userList ->
            oldMyNotes = userList as ArrayList<User>
            adapter = AdapterUser(requireContext(), userList)
            binding.recyclerViewMain.adapter = adapter

        }
    }

}