package com.example.firstassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
    private var foodList: List<FoodItem>,
    private val context: Context
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodList[position]

        holder.foodImage.setImageResource(foodItem.imageResId)
        holder.foodTitle.text = foodItem.title
        holder.foodDescription.text = foodItem.description

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "You clicked on: ${foodItem.title}", Toast.LENGTH_SHORT).show()
        }

        holder.foodImage.setOnClickListener {
            Toast.makeText(context, "You pressed ${foodItem.title} image", Toast.LENGTH_SHORT).show()
        }

        holder.likeButton.setOnClickListener {
            Toast.makeText(context, "You liked ${foodItem.title}", Toast.LENGTH_SHORT).show()
        }

        holder.shareButton.setOnClickListener {
            Toast.makeText(context, "You shared ${foodItem.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = foodList.size

    fun updateData(newFoodList: List<FoodItem>) {
        foodList = newFoodList
        notifyDataSetChanged()
    }

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.item_image)
        val foodTitle: TextView = itemView.findViewById(R.id.item_title)
        val foodDescription: TextView = itemView.findViewById(R.id.item_description)
        val likeButton: ImageButton = itemView.findViewById(R.id.like_button)
        val shareButton: ImageButton = itemView.findViewById(R.id.share_button)

    }
}
