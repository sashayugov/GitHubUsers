package com.example.githubusers.ui.userslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.databinding.FragmentUsersListBinding
import com.example.githubusers.domain.UserResponseModel
import com.example.githubusers.domain.UsersData
import com.example.githubusers.ui.UsersListViewModel
import com.example.githubusers.ui.reposlist.ReposListFragment

class UsersListFragment : Fragment() {

    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UsersListViewModel>()

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = binding.userListProgressBar
        recyclerView = binding.recyclerViewUsersList
        renderData()
    }

    private fun renderData() {
        viewModel.usersLiveData.observe(viewLifecycleOwner) { usersData ->
            when (usersData) {
                is UsersData.Success -> {
                    setRecyclerView(usersData)
                    progressBar.isVisible = false
                }
                is UsersData.Error -> {
                    Toast.makeText(requireContext(), "Error, no users", Toast.LENGTH_LONG).show()
                }
                is UsersData.Loading -> {
                    progressBar.isVisible = usersData.progress
                }
            }
        }
    }

    private fun setRecyclerView(usersData: UsersData.Success) {
        adapter = UsersListAdapter(usersData.users,
            onUserClickListener = { userResponseModel -> onOpenReposListFragment(userResponseModel) })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onOpenReposListFragment(user: UserResponseModel) {
        requireActivity().supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container, ReposListFragment.newInstance(user.login))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}