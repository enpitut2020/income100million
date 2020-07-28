package com.example.soundhub

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_list_item.*

class ListItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_item)
        val id = intent.getStringExtra("position")
        listName.text = id

        textView6.setOnClickListener {
            val uri = Uri.parse("https://music.apple.com/jp/album/%E3%81%A4%E3%81%AA%E3%81%8C%E3%83%AA%E3%83%BC%E3%83%A8/1444855586?i=1444855593")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        textView17.setOnClickListener {
            val uri = Uri.parse("https://music.apple.com/jp/album/%E7%AB%8B%E3%81%A1%E4%B8%8A%E3%81%8C%E3%83%AA%E3%83%BC%E3%83%A8/1444855586?i=1444855587")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        textView31.setOnClickListener {
            val uri = Uri.parse("https://music.apple.com/jp/album/%E5%8B%9D%E3%81%A3%E3%81%A6%E6%B3%A3%E3%81%93%E3%81%86%E3%82%BC%E3%83%83/1444855586?i=1444855594")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun show() {
        val texts = queryTexts(this)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = ArrayAdapter<String>(this,
            R.layout.list_text_row, R.id.textView, texts
        )
    }

}