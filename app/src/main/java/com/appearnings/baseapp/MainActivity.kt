package com.appearnings.baseapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.appearnings.baseapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel.state.handleEvent(MyEvent.Loading)
        binding.btnClickMe.setOnClickListener {
            viewModel.clickOnSingleData()
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            viewModel.state.collect {
                Log.e("STATE", Gson().toJson(it))
            }
        }
    }
}