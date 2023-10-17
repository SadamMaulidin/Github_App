package com.example.githubappsekian.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubappsekian.data.model.User
import com.example.githubappsekian.databinding.ItemUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val listUser = ArrayList<User>()
    private var onItemClickCallback: OnitemClickCallback? = null

    fun setList(users: ArrayList<User>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    interface OnitemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setOnItemClickCallback(onitemClickCallback: OnitemClickCallback){
        this.onItemClickCallback = onitemClickCallback
    }

    inner class ViewHolder(val binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User){
            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .circleCrop()
                    .into(imgUser)
                tvUsername.text = user.login
//                tvFollowers.text = user.followers.toString()
//                tvFollowing.text = user.following.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUser[position])
    }
}