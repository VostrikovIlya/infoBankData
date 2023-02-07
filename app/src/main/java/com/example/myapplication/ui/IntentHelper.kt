package com.example.myapplication.ui

import android.content.Context
import android.content.Intent
import android.net.Uri



class IntentHelper constructor(private val context: Context) {

    fun intentDial(phone: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_DIAL
        intent.data = Uri.parse("tel:$phone")
        context.startActivities(arrayOf(intent))
    }
    fun intentUrl(url: String){
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)
        context.startActivities(arrayOf(intent))
    }

    fun intentMap(city:String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("google.navigation:q=$city")
        context.startActivities(arrayOf(intent))
    }


    enum class Action{
        URL, PHONE, MAPS
    }
}