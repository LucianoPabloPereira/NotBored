package com.example.notbored.data.service

import com.example.notbored.data.model.ActivityModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("activity")
    fun getActivityRandom(): Call<ActivityModel>

    @GET("activity")
    fun getActivityByParticipants(@Query("participants") participants : Int) : Call<ActivityModel>

    @GET("activity/type={value}&participants={value2}")
    fun getActivityByTypeAndParticipants(@Path ("value")  value : String, @Path ("value2") value2 : Int) : Call<ActivityModel>

}