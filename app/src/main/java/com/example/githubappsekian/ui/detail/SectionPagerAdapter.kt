package com.example.githubappsekian.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubappsekian.R
import com.example.githubappsekian.ui.detail.follower.FollowersFragment
import com.example.githubappsekian.ui.detail.following.FollowingFragment

@Suppress("DEPRECATION")
class SectionPagerAdapter(private val context: Context, fragManager: FragmentManager, data: Bundle): FragmentPagerAdapter(fragManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle:Bundle? = null

    init {
        fragmentBundle = data
    }

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_1, R.string.tab_2)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 ->fragment = FollowersFragment()
            1 ->fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}