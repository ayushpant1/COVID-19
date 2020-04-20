package com.covid.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.covid.Model.HomeListModel
import com.covid.R
import com.covid.Util.Constants
import com.google.gson.Gson

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailsActivity : AppCompatActivity(), View.OnClickListener {
    private var toolbar: Toolbar? = null
    private var tvName: TextView? = null
    private var tvQuantity: TextView? = null
    private var tvPrice: TextView? = null
    private var imgShoppingCart: ImageView? = null
    private var tvTotal: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        init()
        showUI()
        onClickListener()
    }

    private fun init() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        tvName = findViewById<View>(R.id.tv_name) as TextView
        tvQuantity = findViewById<View>(R.id.tv_quantity) as TextView
        tvPrice = findViewById<View>(R.id.tv_price) as TextView
        imgShoppingCart = findViewById<View>(R.id.img_shopping_cart) as ImageView
        tvTotal = findViewById<View>(R.id.tv_total) as TextView
    }

    private fun showUI() {
        setUpToolbar()

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun onClickListener() {
        imgShoppingCart!!.setOnClickListener(this)
        toolbar!!.setNavigationOnClickListener(this)
        val gson = Gson()
        val homeStringObject = intent.getStringExtra(Constants.HOME_STRING_OBJECT)
        val homeListModel = gson.fromJson(homeStringObject, HomeListModel::class.java)
        tvName!!.text = homeListModel.brandName
        tvPrice!!.text = Constants.RUPEE_SYMBOL + " " + homeListModel.price
        tvQuantity!!.text = intent.getStringExtra(Constants.QUANTITY)
        tvTotal!!.text = Constants.RUPEE_SYMBOL + " " + Integer.parseInt(homeListModel.price) * Integer.parseInt(intent.getStringExtra(Constants.QUANTITY))
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.img_shopping_cart -> {
                val intent = Intent(this@DetailsActivity, CartActivity::class.java)
                startActivity(intent)
            }
            else -> onBackPressed()
        }
    }
}