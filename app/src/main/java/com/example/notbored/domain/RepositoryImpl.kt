package com.example.notbored.domain

import com.example.notbored.data.RemoteDataSource
import com.example.notbored.data.model.ActivityModel
import retrofit2.Call

class RepositoryImpl(private val dataSource: RemoteDataSource) : Repository {

    override fun getActivityRandom(): Call<ActivityModel> {
        return dataSource.getActivityRandom()
    }

    override fun getActivityByParticipants(cantPart: Int): Call<ActivityModel> {
        return dataSource.getActivityByParticipants(cantPart)
    }

    override fun getActivityByTypeAndParticipants(
        type: String,
        cantParticip: Int
    ): Call<ActivityModel> {
        return dataSource.getActivityByTypeAndParticipants(type, cantParticip)
    }


}