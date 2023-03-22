package com.example.githubusers.ui.userslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.app
import com.example.githubusers.databinding.FragmentUsersListBinding
import com.example.githubusers.di.ViewModelFactory
import com.example.githubusers.domain.UserResponseModel
import com.example.githubusers.domain.UsersData
import com.example.githubusers.ui.UsersListViewModel
import com.example.githubusers.ui.reposlist.ReposListFragment
import javax.inject.Inject
import javax.inject.Singleton

class UsersListFragment : Fragment() {

    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by activityViewModels<UsersListViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        requireContext().app.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderData()
    }

    private fun renderData() {
        viewModel.usersLiveData.observe(viewLifecycleOwner) { usersData ->
            when (usersData) {
                is UsersData.Success -> {
                    setRecyclerView(usersData)
                    binding.userListProgressBar.isVisible = false
                }
                is UsersData.Error -> {
                    Toast.makeText(requireContext(), "Error, no users", Toast.LENGTH_LONG).show()
                }
                is UsersData.Loading -> {
                    binding.userListProgressBar.isVisible = usersData.progress
                }
            }
        }
    }

    private fun setRecyclerView(usersData: UsersData.Success) {
        binding.recyclerViewUsersList.adapter = UsersListAdapter(
            usersArray = usersData.users,
            onUserClickListener = { userResponseModel ->
                onOpenReposListFragment(userResponseModel)
            })
        binding.recyclerViewUsersList.layoutManager = LinearLayoutManager(requireContext())
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