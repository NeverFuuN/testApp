package com.nfproject.workingapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.nfproject.workingapp.R
import com.nfproject.workingapp.models.databaseModel
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class HomeFragment : Fragment() {
    lateinit var database: FirebaseFirestore
    var list = arrayListOf<databaseModel>()
    var adapter = dataAdapter(list)
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

    //Retrieving data from a database

    fun getData() {
        GlobalScope.launch {

            database.collection("Users")
                .get()
                .addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
                    override fun onComplete(p0: Task<QuerySnapshot>) {

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

                            recyclerView.adapter = adapter
                            progressBar.visibility = View.GONE
                        }

                    }
                })
        }
    }
}