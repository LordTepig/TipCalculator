package com.bowen.tipcalculatorpart3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.bowen.tipcalculatorpart1.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}