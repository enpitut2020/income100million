package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
import android.widget.Button
//import com.google.firebase.firestore.FirebaseFirestore
//import java.util.*
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

/*
class MainActivity : AppCompatActivity() {
    private val TAG = "DocSnippets"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()
        //Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )


        db.collection("cities").document("new-city-id").set(user)
// Add a new document with a generated ID
            db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }


        val button1 = findViewById<Button>(R.id.search_button)
        val button2 = findViewById<Button>(R.id.edit_button)
        button1.setOnClickListener {


            // ここで画面遷移を行う
            val intent = Intent(this, SearchActivity::class.java)

            startActivity(intent)
        }

        button2.setOnClickListener {

            // ここで画面遷移を行う
            val intent2 = Intent(this, EditPlayList::class.java)

            startActivity(intent2)
        }

    }
*/


class MainActivity : AppCompatActivity() {

    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(mToolbar)

        val button2 = findViewById<Button>(R.id.edit_button)

        button2.setOnClickListener {

            // ここで画面遷移を行う
            val intent2 = Intent(this, EditPlayList::class.java)

            startActivity(intent2)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Top App Barのボタンから画面遷移する処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.main_menu_search_button) {

            // ここで画面遷移を行う
            val intent = Intent(this, SearchActivity::class.java)

            startActivity(intent)
        }
        return true
    }
}