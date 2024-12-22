package com.example.firstassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var titleList = mutableListOf<String>()
    private var descriptionList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        postToList()

        val recyclerView = findViewById<RecyclerView>(R.id.food_list_RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(titleList, descriptionList, imageList)
    }

    private fun postToList() {
        val foodTitles = resources.getStringArray(R.array.food_titles)
        val foodDescriptions = resources.getStringArray(R.array.food_descriptions)

        for (i in foodTitles.indices) {
            addToList(
                title = foodTitles[i],
                description = foodDescriptions[i],
                image = getImage(i)
            )
        }
    }

    private fun addToList(title: String, description: String, image: Int) {
        titleList.add(title)
        descriptionList.add(description)
        imageList.add(image)
    }

    private fun getImage(index: Int): Int {
        return when (index) {
            0 -> R.drawable.food_item_1
            1 -> R.drawable.food_item_2
            2 -> R.drawable.food_item_3
            3 -> R.drawable.food_item_4
            4 -> R.drawable.food_item_5
            else -> R.mipmap.ic_launcher_round
        }
    }
}