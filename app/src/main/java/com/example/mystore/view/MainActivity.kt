package com.example.mystore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mystore.R
import com.example.mystore.databinding.ActivityMainBinding
import com.example.mystore.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        mainViewModel.initializeDataFromJson(resources)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentContainer.id, HomeScreen())
        transaction.commit()
    }
}