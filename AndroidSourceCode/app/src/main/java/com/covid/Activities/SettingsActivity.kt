package com.covid.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View

import com.covid.R

class SettingsActivity : AppCompatActivity(), View.OnClickListener {
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
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
        onBackPressed()
    }
}
