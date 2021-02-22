package com.nfproject.workingapp.ui.fragments.map

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nfproject.workingapp.ui.activites.MapActivity
import com.nfproject.workingapp.R
import kotlinx.android.synthetic.main.fragment_map.*
import kotlin.concurrent.thread

class MapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_location.setOnClickListener {
            startActivity(Intent(activity, MapActivity::class.java))

        }
    }
}