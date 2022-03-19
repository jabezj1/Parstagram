package com.example.parstagram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.parstagram.fragments.ComposeFragment
import com.example.parstagram.fragments.FeedFragment
import com.example.parstagram.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File


/**
 * let user create a post by taking a photo with there camera
 */

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

//        val firstObject = ParseObject("FirstClass")
//        firstObject.put("message","Hey ! First message from android. Parse is now connected")
//        firstObject.saveInBackground {
//            if (it != null){
//                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
//            }else{
//                Log.d("MainActivity","Object saved.")
//            }
//        }

        //1. Setting the Description of the Post
        //2. A Button to launch the camera to take a picture
        //3. An Image to show the  picture that user has taken
        //4. A button to save and send the post to our parse server
//--------------------------------listeners -------------------------------------------
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener{
            item->

            var fragmentToShow: Fragment? = null
            when(item.itemId){
                R.id.action_home -> {
                    fragmentToShow = FeedFragment()
                }
                R.id.action_compose -> {
                    fragmentToShow = ComposeFragment()
                }
                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()
                }
            }

            if(fragmentToShow !=null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            //Return tru to say that we have handled the user interaction
            true
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home

        findViewById<Button>(R.id.btnLogOut).setOnClickListener {
            ParseUser.logOut()
            val currentUser = ParseUser.getCurrentUser() // this will now be null
            gotoLoginActivity()
        }
//--------------------------------------------------------------------------------------------------

        //queryPost()
    }


    private fun gotoLoginActivity(){
        val intent = Intent(this@MainActivity, LoginActivity:: class.java)
        startActivity(intent)
        finish()

    }
    companion object{
        const val TAG = "MainActivity"
    }


}