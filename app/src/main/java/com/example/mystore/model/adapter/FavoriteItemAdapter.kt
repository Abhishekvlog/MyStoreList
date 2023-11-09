package com.example.mystore.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.databinding.FavItemLayoutBinding
import com.example.tummoc_project.model.local.FavoriteItemEntity

class FavoriteItemAdapter(private val favItem : List<FavoriteItemEntity>) : RecyclerView.Adapter<FavoriteItemAdapter.FavoriteItemViewHolder>() {
    inner  class FavoriteItemViewHolder(val binding : FavItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        val binding = FavItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  FavoriteItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favItem.size
    }

    override fun onBindViewHolder(holder: FavoriteItemViewHolder, position: Int) {
       val currentFavItem = favItem[position]
        holder.binding.apply {
            favItemName.text = currentFavItem.name
            favItemImage.setImageResource(currentFavItem.imageResource)
            favItemPrize.text = currentFavItem.price.toString()
            favItemUnit.text = currentFavItem.unit.toString()
        }
    }
}