package com.example.soundhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.AddList)
        button.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editPlaylistName)
            insertText(this, editText.text.toString())
            show()
        }
        show()
    }

    private fun show() {
        val texts = queryTexts(this)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = ArrayAdapter<String>(this,
            R.layout.list_text_row, R.id.textView, texts
        )
    }

}