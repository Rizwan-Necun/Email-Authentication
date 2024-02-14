package com.example.emailauthentication

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the logout button by its id
        val logoutButton: Button = findViewById(R.id.logoutButton)

        // Set onClick listener for the logout button
        logoutButton.setOnClickListener {
            // Sign out the user from Firebase Auth
            FirebaseAuth.getInstance().signOut()

            // Navigate to the sign-in screen
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to it using the back button
        }
    }
}
