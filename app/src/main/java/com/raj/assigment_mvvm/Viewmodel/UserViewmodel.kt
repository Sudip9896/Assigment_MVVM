package com.raj.assigment_mvvm.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raj.assigment_mvvm.model.User_Model
import com.raj.assigment_mvvm.repositry.UserFetchRepo
import kotlinx.coroutines.launch


class UserViewmodel : ViewModel() {

     private val repository = UserFetchRepo()
     val userlist = MutableLiveData< ArrayList<User_Model>>()

    fun loadUser(){

        viewModelScope.launch {

            try {
                val response = repository.fetchUser()
                if (response.isSuccessful && response.body() != null) {
                    userlist.value = ArrayList(response.body()!!.data)
                } else {
                    userlist.value = arrayListOf<User_Model>()
                }
            }catch (e:Exception){
             userlist.value = arrayListOf<User_Model>()
            }
        }
    }
}