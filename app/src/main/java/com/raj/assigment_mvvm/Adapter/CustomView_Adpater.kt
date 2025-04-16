package com.raj.assigment_mvvm.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.raj.assigment_mvvm.R
import com.raj.assigment_mvvm.model.User_Model

import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class CustomView_Adpater(val context: Context?, var userlist: ArrayList<User_Model>) :
    BaseAdapter() {

    init {
        // Log the user data when the adapter is initialized
        Log.d("CustomView_Adpater", "User Data: $userlist")
    }

    override fun getCount() = userlist.size

    override fun getItem(postion: Int) = userlist[postion]

    override fun getItemId(postion: Int) = postion.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(postion: Int, Userview: View?, parent: ViewGroup?): View {
        val view =
            LayoutInflater.from(context).inflate(R.layout.custom_user_list_item, parent, false)
        val image = view.findViewById<ImageView>(R.id.img_user)
        val firstname = view.findViewById<TextView>(R.id.txt_FirstName)
        val lastname = view.findViewById<TextView>(R.id.txt_LastName)
        val email = view.findViewById<TextView>(R.id.txt_Email)

        val user = userlist[postion]
        firstname.text = user.first_name
        lastname.text = user.last_name
        email.text = user.email
                /// if image not load due to poor internet connection
        Picasso.get().load(user.avatar).placeholder(R.drawable.search).error(R.drawable.user)
            .into(image, object : Callback {
                override fun onSuccess() {
                    Log.d("Picasso", "onSuccess: Image Loaded ")
                }

                override fun onError(e: Exception?) {
                    image.postDelayed(
                        {
                            Picasso.get().load(user.avatar).placeholder(R.drawable.search)
                                .error(R.drawable.user).into(image)
                        }, 1000)
                }


            })
        return view



        }
    fun update_Data(newlist: ArrayList<User_Model>) {
        this.userlist = newlist
        notifyDataSetChanged()
    }
    }