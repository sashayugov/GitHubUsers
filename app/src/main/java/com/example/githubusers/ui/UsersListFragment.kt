package com.example.githubusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.FragmentUsersListBinding
import com.example.githubusers.domain.UsersData
import com.example.githubusers.ui.adapter.UsersListAdapter

class UsersListFragment : Fragment() {

    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UsersListViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerViewUsersList
        renderData()
    }

    private fun renderData() {
        viewModel.usersLiveData.observe(viewLifecycleOwner) { usersData ->
            when (usersData) {
                is UsersData.Success -> {
                    setRecyclerView(usersData)
                }
                is UsersData.Error -> {
                    Toast.makeText(requireContext(), "Error, no users", Toast.LENGTH_LONG).show()
                }
                is UsersData.Loading -> {
                    // TODO
                }
            }
        }
    }

    private fun setRecyclerView(usersData: UsersData.Success) {
        adapter = UsersListAdapter(
            usersData.users,
            onUserClickListener = { userResponseModel -> viewModel.onUserClick(userResponseModel) })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}