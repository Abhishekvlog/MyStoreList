package com.example.mystore.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.R
import com.example.mystore.databinding.ItemLayoutBinding
import com.example.tummoc_project.model.local.Item

class StoreItemAdapter(private  val itemList : List<Item>,   private val onFavoriteClick: (Item) -> Unit) : RecyclerView.Adapter<StoreItemAdapter.StoreItemViewHolder>() {
    inner  class StoreItemViewHolder(val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  StoreItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.binding.apply {
            itemName.text = currentItem.name
            itemPrize.text = currentItem.price.toString()
            itemImage.setImageResource(currentItem.icon.toInt())
            itemFavBtn.setOnClickListener {

                // Toggle the isFavorite property
            currentItem.isFavorite = !currentItem.isFavorite

                // Change the color of the "itemFavBtn" based on the isFavorite property
                if (currentItem.isFavorite){
                    itemFavBtn.setImageResource(R.drawable.favorite_red)
                }
                else{
                    itemFavBtn.setImageResource(R.drawable.unfavorite)
                }
                // Notify the adapter of the data change
                notifyChange()

                // Handle storing the item in the Room database (you can call onFavoriteClick here)
                onFavoriteClick(currentItem)
            }
        }

    }


}