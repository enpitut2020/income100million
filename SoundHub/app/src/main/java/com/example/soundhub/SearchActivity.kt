package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //リストのアイテムがタップされたとき画面推移処理する
        val listI = findViewById<ListView>(R.id.listView_s)
        listI.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, listItem::class.java)
            intent.putExtra("position", position.toInt())
            startActivity(intent)
        }

        show()
    }

    private fun show() {
        val texts = queryTexts(this)
        val listView = findViewById<ListView>(R.id.listView_s)
        listView.adapter = ArrayAdapter<String>(this,
            R.layout.list_text_row, R.id.textView, texts
        )
    }
}