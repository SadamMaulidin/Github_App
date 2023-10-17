package com.example.githubappsekian.ui.detail.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubappsekian.R
import com.example.githubappsekian.databinding.FragmentFollowingBinding
import com.example.githubappsekian.ui.UserAdapter
import com.example.githubappsekian.ui.detail.DetailActivity
import com.example.githubappsekian.ui.detail.follower.FollowersViewModel

class FollowingFragment : Fragment(R.layout.fragment_following) {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowingBinding.bind(view)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.getListFollowing().observe(viewLifecycleOwner){
            if (it != null){
                adapter.setList(it)
                showLoading(false)
            }
        }
        viewModel.setListFollowings(username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}