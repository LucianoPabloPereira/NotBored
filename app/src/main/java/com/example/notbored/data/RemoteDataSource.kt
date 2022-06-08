package com.example.notbored.data

import com.example.notbored.data.model.ActivityModel
import com.example.notbored.data.service.APIService
import com.example.notbored.data.service.RetrofitClient
import retrofit2.Call

class RemoteDataSource {

    fun getActivityRandom(): Call<ActivityModel> {
        return RetrofitClient.getAPI(APIService::class.java).getActivityRandom()
    }

    fun getActivityByType(type:String): Call<ActivityModel> {
        return RetrofitClient.getAPI(APIService::class.java).getActivityByType(type)
    }

    fun getActivityByParticipants(cantParticip: Int): Call<ActivityModel> {
        return RetrofitClient.getAPI(APIService::class.java).getActivityByParticipants(cantParticip)
    }

    fun getActivityByTypeAndParticipants(type:String , cantParticip: Int): Call<ActivityModel> {
        return RetrofitClient.getAPI(APIService::class.java).getActivityByTypeAndParticipants(type, cantParticip)
    }

}