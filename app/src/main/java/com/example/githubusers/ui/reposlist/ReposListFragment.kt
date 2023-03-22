package com.example.githubusers.ui.reposlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.app
import com.example.githubusers.databinding.FragmentReposListBinding
import com.example.githubusers.di.ViewModelFactory
import com.example.githubusers.domain.ReposData
import com.example.githubusers.ui.UsersListViewModel
import javax.inject.Inject
import javax.inject.Singleton

private const val USER_NAME = "USER_NAME"

class ReposListFragment : Fragment() {

    private var _binding: FragmentReposListBinding? = null
    private val binding get() = _binding!!

    private var userName: String? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by activityViewModels<UsersListViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        requireContext().app.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userName = it.getString(USER_NAME)
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
        userName?.let { viewModel.getReposByName(it) }
        binding.reposListOwner.text = userName
        renderData()
    }

    private fun renderData() {
        viewModel.reposLiveData.observe(viewLifecycleOwner) { reposData ->
            when (reposData) {
                is ReposData.Success -> {
                    setRecyclerView(reposData)
                    binding.reposListProgressBar.isVisible = false
                }
                is ReposData.Error -> {
                    Toast.makeText(requireContext(), "Error, no repos", Toast.LENGTH_LONG).show()
                }
                is ReposData.Loading -> {
                    binding.reposListProgressBar.isVisible = reposData.progress
                }
            }
        }
    }

    private fun setRecyclerView(reposData: ReposData.Success) {
        binding.recyclerViewReposList.adapter = ReposListAdapter(reposData.repos)
        binding.recyclerViewReposList.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(userName: String) = ReposListFragment().apply {
            arguments = Bundle().apply {
                putString(USER_NAME, userName)
            }
        }
    }
}