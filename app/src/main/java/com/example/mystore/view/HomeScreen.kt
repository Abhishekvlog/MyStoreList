package com.example.mystore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystore.R
import com.example.mystore.databinding.FragmentHomeScreenBinding
import com.example.mystore.model.adapter.StoreItemAdapter
import com.example.mystore.viewModel.StoreViewModel
import com.example.tummoc_project.model.local.FavoriteItemEntity
import com.example.tummoc_project.model.local.Item
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : Fragment() {
    private lateinit var binding : FragmentHomeScreenBinding

    @Inject
    lateinit var viewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater,container,false)

        viewModel.getDataFromJsonFile(resources)
       val foodList =  viewModel.getItemList(resources,"Food")
       val giftList =  viewModel.getItemList(resources,"Gift")
       val drinkList =  viewModel.getItemList(resources,"Beverages")


        binding.btnCart.setOnClickListener {
            NavHostController(requireContext()).navigate(R.id.action_homeScreen_to_cardFragment)
        }
        binding.btnFav.setOnClickListener {
            NavHostController(requireContext()).navigate(R.id.action_homeScreen_to_favoriteFragment)
        }

        // for food item view
        val foodAdapter = StoreItemAdapter(foodList,onFavoriteClick)
        binding.foodRecyclerView.adapter = foodAdapter
        binding.foodRecyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // for gifts item view
        val giftAdapter = StoreItemAdapter(giftList,onFavoriteClick)
        binding.giftRecyclerView.adapter = giftAdapter
        binding.giftRecyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        // for drinks item view
        val drinkAdapter = StoreItemAdapter(drinkList,onFavoriteClick)
        binding.drinksRecyclerView.adapter = drinkAdapter
        binding.drinksRecyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

    private val onFavoriteClick: (Item) -> Unit = { item ->
        // Create a FavoriteItem instance from the clicked item
        val favoriteItem = FavoriteItemEntity(
            name = item.name,
            imageResource = item.icon.toInt(),
            price = item.price,
            unit = 1 // You can set the quantity as desired
        )

        // Insert the favorite item into the Room database
        viewModel.insertFavoriteItem(favoriteItem)
    }
}