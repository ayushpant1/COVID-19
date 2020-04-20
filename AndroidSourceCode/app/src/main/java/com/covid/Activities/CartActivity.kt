package com.covid.Activities

import android.os.Bundle
import android.view.View

import com.covid.R

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class CartActivity : AppCompatActivity(), View.OnClickListener {
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        init()
        showUI()
        onClickListener()
    }

    private fun init() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
    }

    private fun showUI() {
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun onClickListener() {
        toolbar!!.setNavigationOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.toolbar -> {
            }
            else -> onBackPressed()
        }
    }
}
