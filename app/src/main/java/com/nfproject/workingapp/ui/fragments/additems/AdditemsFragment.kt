package com.nfproject.workingapp.ui.fragments.additems

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.nfproject.workingapp.R
import com.nfproject.workingapp.models.databaseModel
import kotlinx.android.synthetic.main.fragment_additems.*
import kotlinx.coroutines.*


class AdditemsFragment : Fragment() {
    lateinit var database: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_additems, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseFirestore.getInstance()
        sendData()
    }

    //Template for filling data to send to the database

    fun templateData() {

        var name = et_name.text.toString().trim()
        var email = et_email.text.toString().trim()
        var msg = et_msg.text.toString().trim()

        if (name.isEmpty() && email.isEmpty() && msg.isEmpty()) {
            Toast.makeText(activity, "Fill in all the lines", Toast.LENGTH_SHORT).show()
        }


        if (name.isNotEmpty() && email.isNotEmpty() && msg.isNotEmpty()) {

            var mode = databaseModel(name, email, msg)
            GlobalScope.launch {
                database.collection("Users")
                    .add(mode)
                    .addOnSuccessListener {
                        et_name.setText("")
                        et_email.setText("")
                        et_msg.setText("")

                    }
                    .addOnFailureListener {
                        Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show()
                    }

            }
        }
    }

    //Sending data to the database

    fun sendData() {

        btn_send.setOnClickListener {
            templateData()
            btn_send.setEnabled(false)
            btn_send.postDelayed(Runnable {
                btn_send.setEnabled(true)
                Log.d("TAG", "resend1")
            }, 1500)

        }
    }
}
