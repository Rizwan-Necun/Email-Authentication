package com.example.emailauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.security.PrivateKey

class SignUpActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var userPassword: EditText
    lateinit var signup: Button
    lateinit var auth: FirebaseAuth
    lateinit var login: TextView
    private lateinit var username: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        email = findViewById(R.id.etEmail)
        userPassword = findViewById(R.id.etPassword)
        signup = findViewById(R.id.btnSignup)
        login = findViewById(R.id.txtLogin)
        username = findViewById(R.id.etUsername)
        auth = FirebaseAuth.getInstance()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        signup.setOnClickListener {
            val mail = email.text.toString().trim { it <= ' ' }
            val password = userPassword.text.toString().trim { it <= ' ' }
            if (mail.isEmpty()) {
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Password length must be 6 characters", Toast.LENGTH_SHORT)
                    .show()
            } else {
                signUp(mail, password)
            }
        }

        login.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
    private fun signUp(mail: String, password: String) {
        auth.createUserWithEmailAndPassword(mail, password)
            .addOnCompleteListener(this) { it ->
                if (it.isSuccessful) {
//                    addUserToDatabase(username.text.toString(), mail, auth.currentUser?.uid!!)
                    val intent2 = Intent(this, SignInActivity::class.java)
                    finish()
                    intent2.putExtra("name", mail)
                    startActivity(intent2)
                } else {
                    Toast.makeText(this, "You already have an account", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
