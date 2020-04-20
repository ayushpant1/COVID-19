package com.covid.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.covid.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var mGoogleSignInClient: GoogleSignInClient? = null

    private var tilUsername: TextInputLayout? = null
    private val tilPassword: TextInputLayout? = null
    private var etUsername: EditText? = null
    private var etPassword: EditText? = null
    private var btnSubmit: Button? = null
    private val llSubmit: LinearLayout? = null
    private var tvForgotYourPassword: TextView? = null
    private var tvSignUp: TextView? = null
    private var imgFacebookSignIn: ImageView? = null
    private var imgTwitterSignIn: ImageView? = null
    private var imgGoogleSignIn: ImageView? = null
    private lateinit var auth: FirebaseAuth
    private var callBackManager: CallbackManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        configureGooggleSignIn()
        showUI()
        onClickListener()
    }

    private fun configureGooggleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun init() {
        tilUsername = findViewById<View>(R.id.til_username) as TextInputLayout
        tilUsername = findViewById<View>(R.id.til_password) as TextInputLayout

        etUsername = findViewById<View>(R.id.et_username) as EditText
        etPassword = findViewById<View>(R.id.et_password) as EditText

        btnSubmit = findViewById<View>(R.id.btn_submit) as Button
        //llSubmit = (LinearLayout) findViewById(R.id.ll_submit);

        imgFacebookSignIn = findViewById<View>(R.id.img_facebook_sign_in) as ImageView
        imgTwitterSignIn = findViewById<View>(R.id.img_twitter_sign_in) as ImageView
        imgGoogleSignIn = findViewById<View>(R.id.img_google_sign_in) as ImageView

        tvForgotYourPassword = findViewById<View>(R.id.tv_forgot_password) as TextView
        tvSignUp = findViewById<View>(R.id.tv_sign_up) as TextView

        auth = FirebaseAuth.getInstance()
    }

    private fun showUI() {

    }

    private fun onClickListener() {
        btnSubmit!!.setOnClickListener(this)
        imgFacebookSignIn!!.setOnClickListener(this)
        imgTwitterSignIn!!.setOnClickListener(this)
        imgGoogleSignIn!!.setOnClickListener(this)
        tvForgotYourPassword!!.setOnClickListener(this)
        tvSignUp!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        var intent: Intent? = null
        when (view.id) {
            R.id.btn_submit -> {
                intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }

            R.id.img_facebook_sign_in -> {
            }
            R.id.img_twitter_sign_in -> {
            }
            R.id.img_google_sign_in -> googleSignIn()
            R.id.tv_forgot_password -> {
            }
            R.id.tv_sign_up -> {
                intent = Intent(this@LoginActivity, SignupActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    private fun googleSignIn() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    companion object {
        private val RC_SIGN_IN = 1
    }


    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(this, user?.displayName, Toast.LENGTH_LONG).show()
                    } else {
                    }
                }
    }


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
    }
}
