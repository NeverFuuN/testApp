package com.nfproject.workingapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.nfproject.workingapp.R
import com.nfproject.workingapp.data.databaseModel
import kotlinx.android.synthetic.main.fragment_additems.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    lateinit var database: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseFirestore.getInstance()
        getData()
    }

    fun getData() {


        database.collection("Users")
            .get()
            .addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
                override fun onComplete(p0: Task<QuerySnapshot>) {
                    var list = arrayListOf<databaseModel>()
                    if (p0.isSuccessful) {
                        for (data in p0.result!!) {
                            list.add(
                                databaseModel(
                                    data.get("name") as String,
                                    data.get("email") as String,
                                    data.get("msg") as String
                                )
                            )
                        }
                        var adapter = dataAdapter(list)
                        recyclerView.adapter = adapter
                        progressBar.visibility = View.GONE
                    }

                }

            })

    }
}