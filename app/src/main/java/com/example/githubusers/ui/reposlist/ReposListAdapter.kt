package com.example.githubusers.ui.reposlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.RecyclerViewRepoItemBinding
import com.example.githubusers.domain.RepoResponseModel

class ReposListAdapter(
    private val reposArray: Array<RepoResponseModel>
) : RecyclerView.Adapter<ReposListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerViewRepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reposArray[position])
    }

    override fun getItemCount(): Int = reposArray.size

    inner class ViewHolder(itemBinding: RecyclerViewRepoItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var repoName = itemBinding.repoName

        fun bind(repoResponseModel: RepoResponseModel) {
            repoName.text = repoResponseModel.name
        }
    }
}
