package com.example.parstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Check if user is logged in
        // if they are, bring to MainActivity

        if (ParseUser.getCurrentUser() != null){
            goToMainActivity()
        }

        findViewById<Button>(R.id.btLogin).setOnClickListener {
            val username = findViewById<EditText>(R.id.etUser).text.toString()
            val password = findViewById<EditText>(R.id.etPass).text.toString()
            userLog(username, password)

        }

        findViewById<Button>(R.id.btnSignup).setOnClickListener {
            val username = findViewById<EditText>(R.id.etUser).text.toString()
            val password = findViewById<EditText>(R.id.etPass).text.toString()
            userSignUp(username, password)

        }
    }

    private fun userSignUp(username: String, password: String){
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // User has created an account
                //TODO:Show logcat/ toast
                Toast.makeText(this,"Sign Up was Successful! Please Log in", Toast.LENGTH_SHORT). show()
            } else {
                // TODO: Show Toast for error
                e.printStackTrace()
                Toast.makeText(this,"Failed to Sign up. Please Try again", Toast.LENGTH_SHORT). show()
            }
        }
    }



    private fun userLog(username: String, password: String){
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                // Hooray!  The user is logged in.
                Log.i(TAG, "Successfully logged in")
                goToMainActivity()
            } else {
                // Signup failed.  Look at the ParseException to see what happened.
                e.printStackTrace()
                Toast.makeText(this,"Error logging in", Toast.LENGTH_SHORT). show()
            }})
        )

    }

    private fun goToMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity:: class.java)
        startActivity(intent)
        finish()

    }


    companion object{
        const val TAG = "LoginActivity"
    }
}