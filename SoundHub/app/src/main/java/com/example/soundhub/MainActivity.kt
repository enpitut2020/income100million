package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val mDocRef = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
=======
package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar

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
            val intent2 = Intent(this, EditActivity::class.java)

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
            val intent2 = Intent(this, EditPlayList::class.java)

            startActivity(intent)
        }
        return true
    }
}