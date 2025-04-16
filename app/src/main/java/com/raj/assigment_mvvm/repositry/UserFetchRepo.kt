package com.raj.assigment_mvvm.repositry

import com.raj.assigment_mvvm.Api.RetrofitInstance
import com.raj.assigment_mvvm.model.UserResponse
import com.raj.assigment_mvvm.model.User_Model
import retrofit2.Response

class UserFetchRepo{

    suspend fun  fetchUser(): Response<UserResponse> {
        return RetrofitInstance.api.getUsers()

    }
}