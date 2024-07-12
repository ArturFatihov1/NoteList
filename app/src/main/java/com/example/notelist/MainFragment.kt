package com.example.notelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notelist.database.User
import com.example.notelist.database.UserDatabase
import com.example.notelist.databinding.FragmentMainBinding
import com.example.notelist.user.AdapterUser
import com.example.notelist.user.UserViewModel
import com.example.notelist.user.UserViewModelFactory

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
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

//        binding.btnClear.setOnClickListener {
//            userViewModel.clearAll()
//        }
    }

    private fun showUsersList() {
        userViewModel.users.observe(viewLifecycleOwner, Observer {
            setDataToRecyclerView(it)
        })
    }

    private fun setDataToRecyclerView(userList: List<User>) {
        binding.recyclerViewMain.apply {
            adapter = AdapterUser(userList)
        }
    }

}