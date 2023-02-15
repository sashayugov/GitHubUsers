package com.example.githubusers.ui.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.githubusers.databinding.RecyclerViewUserItemBinding
import com.example.githubusers.model.UserResponseModel

class UsersListAdapter(
    private val usersArray: Array<UserResponseModel>,
    private val onUserClickListener: ((item: UserResponseModel) -> Unit)
) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerViewUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onUserClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(usersArray[position])
    }

    override fun getItemCount(): Int = usersArray.size

    class ViewHolder(
        itemBinding: RecyclerViewUserItemBinding,
        private val onUserClickListener: (item: UserResponseModel) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        var userAvatar = itemBinding.userAvatar
        var userName = itemBinding.userName

        fun bind(userResponseModel: UserResponseModel) {
            userAvatar.load(userResponseModel.avatarUrl) {
                error(R.drawable.ic_menu_report_image)
                placeholder(R.drawable.ic_menu_gallery)
            }
            userName.text = userResponseModel.login
            itemView.setOnClickListener { onUserClickListener(userResponseModel) }
        }
    }
}