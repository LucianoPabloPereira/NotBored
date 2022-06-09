package com.example.notbored.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notbored.data.RemoteDataSource
import com.example.notbored.data.model.ActivityModel
import com.example.notbored.domain.Repository
import com.example.notbored.domain.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelActivity(private val repo: Repository) : ViewModel() {

    val activityLiveData = MutableLiveData<ActivityModel?>()
    val errorMessage = MutableLiveData<String>()

    fun getActivityRandom() {
        val response = repo.getActivityRandom()
        response.enqueue(object : Callback<ActivityModel> {
            override fun onResponse(call: Call<ActivityModel>, response: Response<ActivityModel>) {
                activityLiveData.value = response.body()
            }
            override fun onFailure(call: Call<ActivityModel>, t: Throwable) {
                errorMessage.postValue(t.message)
                activityLiveData.value = null
            }
        })
    }

    fun getActivityByType(type:String) {
        val response = repo.getActivityByType(type)
        response.enqueue(object : Callback<ActivityModel> {
            override fun onResponse(call: Call<ActivityModel>, response: Response<ActivityModel>) {
                activityLiveData.value = response.body()
            }
            override fun onFailure(call: Call<ActivityModel>, t: Throwable) {
                errorMessage.postValue(t.message)
                activityLiveData.value = null
            }
        })
    }

    fun getActivityByParticipant(participants : Int) {
        val response = repo.getActivityByParticipants(participants)
        response.enqueue(object : Callback<ActivityModel> {
            override fun onResponse(call: Call<ActivityModel>, response: Response<ActivityModel>) {
                activityLiveData.value = response.body()
            }
            override fun onFailure(call: Call<ActivityModel>, t: Throwable) {
                errorMessage.postValue(t.message)
                activityLiveData.value = null
            }
        })
    }

    fun getActivityByParticipantAndType(type:String, participants : Int,) {
        val response = repo.getActivityByTypeAndParticipants(type,participants)
        response.enqueue(object : Callback<ActivityModel> {
            override fun onResponse(call: Call<ActivityModel>, response: Response<ActivityModel>) {
                activityLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<ActivityModel>, t: Throwable) {
                errorMessage.postValue(t.message)
                activityLiveData.value = null
            }
        })
    }

    class Factory() : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val remoteDataSource= RemoteDataSource()
            val repository = RepositoryImpl(remoteDataSource)
            return ViewModelActivity(repository) as T
        }
    }
}