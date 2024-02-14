package com.example.emailauthentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var login: Button
    private var auth: FirebaseAuth? = null
    lateinit var register: TextView
    lateinit var userName: String
    lateinit var userPassword: String
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        sharedPreferences = getSharedPreferences("Messenger Preferences", Context.MODE_PRIVATE)
        if (intent != null) {
            val i = intent.getStringExtra("logout")
            if (i == "a") {
//                editPreferences()
            }
        }
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        email = findViewById(R.id.etLoginEmail)
        password = findViewById(R.id.etLoginPassword)
        login = findViewById(R.id.btnLogin)
        register = findViewById(R.id.txtRegister)
        auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            userName = email.text.toString().trim { it <= ' ' }
            userPassword = password.text.toString().trim { it <= ' ' }
            if (userName.isEmpty()) {
                Toast.makeText(this, "Please Enter email address", Toast.LENGTH_SHORT).show()
            } else if (userPassword.isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            } else {
                doLogin()
            }
        }

        register.setOnClickListener {
            val i = Intent(this, SignUpActivity::class.java)
            startActivity(i)
        }
    }

    private fun doLogin() {
        auth!!.signInWithEmailAndPassword(userName, userPassword)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this,"Logged in Successfully",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
//                    savePreferences()
//                    val i = Intent(this, ChatActivity5::class.java)
//                    startActivity(i)
                } else {
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            }
    }

//    private fun savePreferences() {
//        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
//    }
//
//    private fun editPreferences() {
//        sharedPreferences.edit().putBoolean("isLoggedIn",false).apply()
//    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}