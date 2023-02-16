package com.example.githubusers.ui.reposlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.databinding.FragmentReposListBinding
import com.example.githubusers.databinding.FragmentUsersListBinding
import com.example.githubusers.domain.ReposData
import com.example.githubusers.ui.UsersListViewModel

private const val USER_NAME = "USER_NAME"
private const val USER_REPOS_URL = "USER_REPOS_URL"

class ReposListFragment : Fragment() {

    private var _binding: FragmentReposListBinding? = null
    private val binding get() = _binding!!

    private var userName: String? = null
    private var userReposUrl: String? = null

    private val viewModel by viewModels<UsersListViewModel>()

    private lateinit var progressBar: ProgressBar
    private lateinit var listOwner: TextView
    private lateinit var reposList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userName = it.getString(USER_NAME)
            userReposUrl = it.getString(USER_REPOS_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReposListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = binding.reposListProgressBar
        listOwner = binding.reposListOwner
        reposList = binding.recyclerViewReposList

        userReposUrl?.let { viewModel.getReposByUrl(it) }

        renderData()
    }

    private fun renderData() {
        viewModel.reposLiveData.observe(viewLifecycleOwner) { reposData ->
        when(reposData) {
            is ReposData.Success -> {
                setRecyclerView(reposData)
                progressBar.isVisible = false
            }
            is ReposData.Error -> {
                Toast.makeText(requireContext(), "Error, no repos", Toast.LENGTH_LONG).show()
            }
            is ReposData.Loading -> {
              progressBar.isVisible = reposData.progress
            }
        }
        }
    }

    private fun setRecyclerView(reposData: ReposData.Success) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(userName: String, userReposUrl: String) = ReposListFragment().apply {
            arguments = Bundle().apply {
                putString(USER_NAME, userName)
                putString(USER_REPOS_URL, userReposUrl)
            }
        }
    }
}