package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //作成ボタンを押してリストの項目を作成する
        val button = findViewById<Button>(R.id.AddList)
        button.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editPlaylistName)
            insertText(this, editText.text.toString())
            show()
        }

        //リストのアイテムがタップされたとき画面推移処理する
        val listI = findViewById<ListView>(R.id.listView)
        listI.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, EditPlayList::class.java)
            val item = parent.getItemAtPosition(position).toString()
            intent.putExtra("position", item)
            startActivity(intent)
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