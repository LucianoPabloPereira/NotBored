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
    fun getActivityByType(@Query("type") type : String): Call<ActivityModel>

    @GET("activity")
    fun getActivityByParticipants(@Query("participants") participants : Int) : Call<ActivityModel>

    @GET("activity")
    fun getActivityByTypeAndParticipants(@Query("type")  value : String, @Query ("participants") value2 : Int) : Call<ActivityModel>
}