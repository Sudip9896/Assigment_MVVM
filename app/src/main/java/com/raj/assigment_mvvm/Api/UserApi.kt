package com.raj.assigment_mvvm.Api

import com.raj.assigment_mvvm.model.UserResponse
import com.raj.assigment_mvvm.model.User_Model
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("api/users?page=2")
    suspend fun getUsers(): Response<UserResponse>
}