package com.example.mystore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Insert
import com.example.mystore.R
import com.example.mystore.databinding.FragmentFavoriteBinding
import com.example.mystore.model.adapter.FavoriteItemAdapter
import com.example.mystore.viewModel.FavoriteViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragment : Fragment() {
    private lateinit var binding : FragmentFavoriteBinding

    @Inject
    private lateinit var favoriteViewModel : FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       lifecycleScope.launch {
           favoriteViewModel.favoriteItemsList.collect{favoriteItem ->
               val favAdapter = FavoriteItemAdapter(favoriteItem)
               binding.favItemRecyclerview.adapter = favAdapter
               binding.favItemRecyclerview.layoutManager = LinearLayoutManager(requireContext())
           }
       }

    }
}