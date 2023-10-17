package com.example.githubappsekian.ui.detail.follower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubappsekian.R
import com.example.githubappsekian.data.model.DetailUserResponse
import com.example.githubappsekian.databinding.FragmentFollowersBinding
import com.example.githubappsekian.ui.UserAdapter
import com.example.githubappsekian.ui.detail.DetailActivity

class FollowersFragment : Fragment(R.layout.fragment_followers) {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowersBinding.bind(view)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.getListFollowers().observe(viewLifecycleOwner){
            if (it != null){
                adapter.setList(it)
                showLoading(false)
            }
        }
        viewModel.setListFollowers(username)
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