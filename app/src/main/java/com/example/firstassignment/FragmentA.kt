package com.example.firstassignment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class   FragmentA: Fragment(R.layout.fragment_a) {
    interface EventHandler{
        fun goToB()

    }

    var eventHandler: EventHandler? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Preview", "Context in fragment: $context")

        require(context is EventHandler){

        }
        eventHandler = context

        eventHandler?.goToB()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.navigateButton).setOnClickListener(){
            eventHandler?.goToB()
        }
    }
}