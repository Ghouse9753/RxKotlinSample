package com.example.rxkotlinsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        btnCreateOperators.setOnClickListener {
            startActivity(CreateActivity::class.java)
        }
        btnFilterOperators.setOnClickListener {
            startActivity(FilterOperatorsActivity::class.java)
        }
        btnTransformOperators.setOnClickListener {
            startActivity(TransformationOperatorsActivity::class.java)
        }
    }

    private fun startActivity(activityClass: Class<out Activity>) {
        startActivity(Intent(this, activityClass))
    }
}