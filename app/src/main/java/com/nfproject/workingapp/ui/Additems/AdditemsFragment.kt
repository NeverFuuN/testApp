package com.nfproject.workingapp.ui.Additems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.nfproject.workingapp.R
import com.nfproject.workingapp.data.databaseModel
import kotlinx.android.synthetic.main.fragment_additems.*

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

        btn_send.setOnClickListener {
            sendData()
        }

    }
    fun sendData() {
        var name = et_name.text.toString().trim()
        var email = et_email.text.toString().trim()
        var msg = et_msg.text.toString().trim()

        if (name.isNotEmpty() && email.isNotEmpty() && msg.isNotEmpty()) {

            var mode = databaseModel(name, email, msg)

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