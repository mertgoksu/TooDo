package com.mertg.toodo.view

import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mertg.toodo.R
import com.mertg.toodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.darkThemeColor)))

    }

    /*private fun addTaskPage(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val currentFragment = fragmentManager.findFragmentById(R.id.mainFragment)

        val addTaskFragment = AddTaskFragment()
        if (currentFragment != null) {
            println("saaaa")
            fragmentTransaction.hide(currentFragment).commit()
        }
        fragmentTransaction.replace(R.id.frameLayout, addTaskFragment).commit()
    }*/





}