package com.example.soundhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_play_list.*

class EditPlayList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_play_list)
        listTitleName.text = intent.getIntExtra("itemId", 0).toString()
    }
}