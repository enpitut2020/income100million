package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore

class SearchActivity : AppCompatActivity() {
    private var searchT = ""
    private val db = FirebaseFirestore.getInstance()

    private val TAG = "DocSnippets"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //検索ボタンを押して検索ワードに引っかかるリストの項目を表示する
        val button = findViewById<Button>(R.id.AddList_s)
        button.setOnClickListener {
            val searchText = findViewById<EditText>(R.id.editPlaylistName_s)
            searchT = searchText.text.toString()
            show()
        }

        /*
        //リストのアイテムがタップされたとき画面推移処理する
        val listI = findViewById<ListView>(R.id.listView_s)
        listI.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ListItem::class.java)
            val item = parent.getItemAtPosition(position).toString()
            intent.putExtra("position", item)
            startActivity(intent)
        }
         */

        show()
    }

    private fun show() {
        val listView = findViewById<ListView>(R.id.listView_s)

        if(searchT.equals("")) {
            db.collection("playLists3")
                .get()
                .addOnSuccessListener { result ->
                    val listtt = mutableListOf<String>()
                    for (document in result) {
                        val title = document.toObject(DataItems::class.java).title
                        listtt.add(title)
                    }
                    listView.adapter = ArrayAdapter<String>(
                        this,
                        R.layout.list_text_row, R.id.textView, listtt
                    )
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }
        }else{
            db.collection("playLists3")
                .whereArrayContains("title", searchT)
                .get()
                .addOnSuccessListener { result ->
                    val listtt = mutableListOf<String>()
                    for (document in result) {
                        val title = document.toObject(DataItems::class.java).title
                        listtt.add(title)
                    }
                    listView.adapter = ArrayAdapter<String>(
                        this,
                        R.layout.list_text_row, R.id.textView, listtt
                    )
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }
        }


        val texts = searchTexts(this, searchT)

    }
}