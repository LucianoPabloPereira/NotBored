package com.example.notbored.domain

import com.example.notbored.data.model.ActivityModel
import retrofit2.Call

interface Repository {

    fun getActivityRandom() : Call<ActivityModel>

    fun getActivityByParticipants(cantPart: Int) : Call<ActivityModel>

    fun getActivityByTypeAndParticipants(type:String , cantParticip: Int) : Call<ActivityModel>
}