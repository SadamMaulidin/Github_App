package com.example.githubappsekian.ui.detail.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubappsekian.api.RetrofitClient
import com.example.githubappsekian.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel: ViewModel() {

    val listFollower = MutableLiveData<ArrayList<User>>()

    fun setListFollowers(username: String){
        RetrofitClient.apiInstance
            .getFollower(username)
            .enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if(response.isSuccessful)
                    {
                        listFollower.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getListFollowers(): LiveData<ArrayList<User>>{
        return listFollower
    }
}