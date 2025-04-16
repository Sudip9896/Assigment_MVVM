package com.raj.assigment_mvvm.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.raj.assigment_mvvm.Adapter.CustomView_Adpater
import com.raj.assigment_mvvm.R
import com.raj.assigment_mvvm.Util.ConnectionManager
import com.raj.assigment_mvvm.Viewmodel.UserViewmodel
import com.raj.assigment_mvvm.model.User_Model


class UserInfo : Fragment() {

    lateinit var listView: ListView
    lateinit var adapter: CustomView_Adpater
    lateinit var searchBox: EditText
    lateinit var progressBar: ProgressBar
    lateinit var userViewmodel: UserViewmodel
    val Userdata = arrayListOf<User_Model>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_info, container, false)

        listView = view.findViewById(R.id.listview)
        searchBox = view.findViewById(R.id.Et_SearchBox)
        progressBar = view.findViewById(R.id.ProgressBar)
        userViewmodel = ViewModelProvider(this)[UserViewmodel::class.java]
        progressBar.visibility = View.VISIBLE

        if (ConnectionManager().checkConnection(activity as Context)) {
            /// viewModel call
            userViewmodel.userlist.observe(viewLifecycleOwner) { users ->
                Userdata.addAll(users)
                adapter = CustomView_Adpater(context, users)
                listView.adapter = adapter
                progressBar.visibility = View.GONE

            }

            userViewmodel.loadUser()

            /// search functionality
            searchBox.addTextChangedListener {
                progressBar.visibility = View.VISIBLE
                val searchText = it.toString().lowercase()
                val filteredList = Userdata.filter { user ->
                    user.first_name.lowercase().startsWith(searchText)
                }
                if (filteredList.isNotEmpty()) {
                    adapter.update_Data(ArrayList(filteredList))
                } else {
                    Toast.makeText(context, "No users found", Toast.LENGTH_SHORT).show()
                }

                // Done searching, hide progress
                progressBar.visibility = View.GONE

            }
        } else {
            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection not found ")
            dialog.setPositiveButton("Open Internet") { text, listner ->
                val settingInternet = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingInternet)
                activity?.finish()
            }
            dialog.setNegativeButton("Exit") { text, listner ->
                activity?.finish()

            }
            dialog.create()
            dialog.show()


        }
        return view
    }
}





