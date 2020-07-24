package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.soundhub.R.id.search_button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.search_button)
        val button2 = findViewById<Button>(R.id.button2)
        button1.setOnClickListener {


                // ここで画面遷移を行う
                val intent = Intent(this, SearchActivity::class.java)

                startActivity(intent)
        }

        button2.setOnClickListener {

            // ここで画面遷移を行う
            val intent = Intent(this, EditActivity::class.java)

            startActivity(intent)
        }

    }
}