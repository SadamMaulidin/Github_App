package com.example.githubappsekian.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubappsekian.R
import com.example.githubappsekian.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        viewModel.getUserDetail().observe(this){
            if (it != null){
                binding.apply {
                    showLoading(false)

                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = resources.getString(R.string.data_follower, it.followers)
                    tvFollowing.text = resources.getString(R.string.data_following, it.following)

                    Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
            }
        }
        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                showLoading(false)
            }
        }
        viewModel.setUserDetail(username.toString())

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun showLoading(state: Boolean){
        binding.progressBar.visibility = if(state) View.VISIBLE else View.GONE
    }
    
    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }
}
