package com.example.soundhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_songs_list.*

class songsListActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private var listId = ""
    private val TAG = "DocSnippets"


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs_list)

        playListTitle.text = intent.getStringExtra("title")
        show()
    }

    private fun show(){
        val listView = findViewById<ListView>(R.id.songListN)

        db.collection("playLists").document(intent.getStringExtra("id").toString())
            .get()
            .addOnSuccessListener { document ->
                val songAndArtist = document.toObject(DataItems::class.java)?.songAndArtist
                listView.adapter = songAndArtist?.let {
                    ArrayAdapter<String>(
                        this,
                        R.layout.list_text_row, R.id.textView, it
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }
}