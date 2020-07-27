package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_edit_play_list.*

class EditPlayList : AppCompatActivity() {
    private var tagN1 = ""
    private var tagN2 = ""
    private var tagN3 = ""
    private var tagN4 = ""
    private var tagN5 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_play_list)
        listTitleName.text = intent.getIntExtra("potition", 0).toString()

        //保存ボタンをタップすると、情報が保存される
        save_button.setOnClickListener {
            tagN1 = findViewById<EditText>(R.id.tag1Name).toString()
            tagN2 = findViewById<EditText>(R.id.tag2Name).toString()
            tagN3 = findViewById<EditText>(R.id.tag3Name).toString()
            tagN4 = findViewById<EditText>(R.id.tag4Name).toString()
            tagN5 = findViewById<EditText>(R.id.tag5Name).toString()
            val intent = Intent(this, EditActivity::class.java)
            editPlayListTags(this, tagN1, tagN2, tagN3, tagN4, tagN5, listTitleName.text.toString())
            startActivity(intent)
        }
    }
}