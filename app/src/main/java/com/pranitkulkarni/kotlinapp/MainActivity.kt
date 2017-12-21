package com.pranitkulkarni.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val items = listOf("Mon 6/23 - Sunny - 31/17",
    "Tue 6/24 - Foggy - 21/8",
    "Wed 6/25 - Cloudy - 22/17",
    "Thurs 6/26 - Rainy - 18/11",
    "Fri 6/27 - Foggy - 21/10",
    "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
    "Sun 6/29 - Sunny - 20/7")

    val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
            "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*messageText.text = "Hello Kotlin!! "+subtract(10,4)
        messageText.textSize = 24.0F
        toast(messageText.text.toString())*/

        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        forecastList.adapter = ForecastListAdapter(items)


        doAsync {
            Request(url).run()
            uiThread { longToast("Request performed by async - ") }
        }

    }




    fun subtract(x:Int,y:Int): Int = x - y

    fun toast(message:String,length:Int = Toast.LENGTH_LONG){

        Toast.makeText(this,message,length).show()
    }


    class Request(val url:String){

        fun run(){

            val jsonResponse = URL(url).readText()
            Log.d("Response",jsonResponse)

        }

    }


}
