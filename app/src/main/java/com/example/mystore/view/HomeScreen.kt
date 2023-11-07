package com.example.mystore.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mystore.databinding.FragmentHomeScreenBinding
import com.example.mystore.viewModel.MainViewModel

class HomeScreen : Fragment() {
    private lateinit var binding : FragmentHomeScreenBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater,container,false)

        viewModel.getDataFromJsonFile(resources)
       val foodList =  viewModel.getItemList(resources,"Food")
        Log.d("abhi", "onCreateView: food list in fragment --------\n${foodList}")

        return binding.root
    }
}