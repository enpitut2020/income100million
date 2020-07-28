package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_edit_play_list.*

class EditPlayList : AppCompatActivity() {
    private val songsList = mutableListOf<String>()

    private val TAG = "DocSnippets"

    private val mDocRef = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_play_list)
        //listTitleName.text = intent.getIntExtra("potition", 0).toString()

        //保存ボタンをタップすると、情報が保存される
        save_button.setOnClickListener {
            saveQuote(it)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //AddListボタンで楽曲の保存
        AddList.setOnClickListener {
            val song = findViewById<EditText>(R.id.addSongArtist).text.toString()
            if(song != "") saveSongs(it, song)
            findViewById<EditText>(R.id.addSongArtist).text = null
            show()
        }

        show()
    }

    private fun saveSongs(view: View, songName: String){
        songsList.add(songName)
    }

    private fun show() {
        val listView = findViewById<ListView>(R.id.songList)
        listView.adapter = ArrayAdapter<String>(
            this,
            R.layout.list_text_row, R.id.textView, songsList
        )
    }

    private fun saveQuote(view : View){
        val titleView = findViewById<EditText>(R.id.listTitleName).text.toString()
        val tag1View = findViewById<EditText>(R.id.tag1Name).text.toString()
        val tag2View = findViewById<EditText>(R.id.tag2Name).text.toString()
        val tag3View = findViewById<EditText>(R.id.tag3Name).text.toString()
        val tag4View = findViewById<EditText>(R.id.tag4Name).text.toString()
        val tag5View = findViewById<EditText>(R.id.tag5Name).text.toString()

        if (titleView.isEmpty()){
            return
        }

        val saveMap = DataItems(
            titleView,
            tag1View,
            tag2View,
            tag3View,
            tag4View,
            tag5View,
            songsList
        )


        mDocRef.collection("playLists3")
            .add(saveMap)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}\"")
                Toast.makeText(applicationContext, "保存しました", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                Toast.makeText(applicationContext, "保存できませんでした", Toast.LENGTH_LONG).show()
            }

    }
}