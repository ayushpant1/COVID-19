package com.covid.Activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

import com.covid.R
import com.google.android.material.textfield.TextInputLayout

import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity(), View.OnClickListener {

    private var tilName: TextInputLayout? = null
    private var tilEmail: TextInputLayout? = null
    private var tilPassword: TextInputLayout? = null
    private var tilConfirmPassword: TextInputLayout? = null

    private var etName: EditText? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var etConfirmPassword: EditText? = null

    private var llSignin: LinearLayout? = null

    private var btnSignUp: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        init()
        showUI()
        setOnClickListener()
    }

    private fun init() {
        tilName = findViewById<View>(R.id.til_name) as TextInputLayout
        tilEmail = findViewById<View>(R.id.til_email) as TextInputLayout
        tilPassword = findViewById<View>(R.id.til_password) as TextInputLayout
        tilConfirmPassword = findViewById<View>(R.id.til_confirm_password) as TextInputLayout

        etName = findViewById<View>(R.id.et_name) as EditText
        etEmail = findViewById<View>(R.id.et_email) as EditText
        etPassword = findViewById<View>(R.id.et_password) as EditText
        etConfirmPassword = findViewById<View>(R.id.et_confirm_password) as EditText

        btnSignUp = findViewById<View>(R.id.btn_sign_up) as Button

        llSignin = findViewById<View>(R.id.ll_sign_in) as LinearLayout
    }

    private fun showUI() {

    }

    private fun setOnClickListener() {
        btnSignUp!!.setOnClickListener(this)
        llSignin!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_sign_up -> {
                Toast.makeText(this, resources.getString(R.string.sign_up_successfully), Toast.LENGTH_LONG).show()
                finish()
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
            R.id.ll_sign_in -> onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
