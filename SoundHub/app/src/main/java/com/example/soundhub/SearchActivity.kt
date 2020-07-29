package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.*
import androidx.core.view.get
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SearchActivity : AppCompatActivity() {
    private var searchT = ""
    private val db = FirebaseFirestore.getInstance()
    private var playListId = mutableListOf<String>()

    private val TAG = "DocSnippets"

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //検索ボタンを押して検索ワードに引っかかるリストの項目を表示する
        val button = findViewById<Button>(R.id.AddList_s)
        button.setOnClickListener {
            val searchText = findViewById<EditText>(R.id.editPlaylistName_s)
            searchT = searchText.text.toString()
            show()
        }


        //リストのアイテムがタップされたとき画面推移処理する
        val listI = findViewById<ListView>(R.id.listView_s)
        listI.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, songsListActivity::class.java)
            intent.putExtra("id", playListId[position])
            intent.putExtra("title", parent.getItemAtPosition(position).toString())
            startActivity(intent)
        }


        show()
    }

    private fun show() {
        val listView = findViewById<ListView>(R.id.listView_s)

        if(searchT.equals("")) {
            db.collection("playLists3")
                .orderBy("title", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    val listtt = mutableListOf<String>()
                    val newSongsId = mutableListOf<String>()
                    for (document in result) {
                        val title = document.toObject(DataItems::class.java).title
                        newSongsId.add(document.id)
                        listtt.add(title)
                    }
                    listView.adapter = ArrayAdapter<String>(
                        this,
                        R.layout.list_text_row, R.id.textView, listtt
                    )
                    playListId = newSongsId
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }
        }else{
            db.collection("playLists3")
                .get()
                .addOnSuccessListener { result ->
                    val listtt = mutableListOf<String>()
                    val newSongsId = mutableListOf<String>()
                    for (document in result) {
                        val title = document.toObject(DataItems::class.java).title
                        val regexTitle = Regex(searchT)
                        if(regexTitle.containsMatchIn(title)) {
                            listtt.add(title)
                            newSongsId.add(document.id)
                            continue
                        }

                        val tag1 = document.toObject(DataItems::class.java).tag1
                        val regexTag1 = Regex(searchT)
                        if(regexTag1.containsMatchIn(tag1)) {
                            listtt.add(title)
                            newSongsId.add(document.id)
                            continue
                        }

                        val tag2 = document.toObject(DataItems::class.java).tag2
                        val regexTag2 = Regex(searchT)
                        if(regexTag2.containsMatchIn(tag2)) {
                            listtt.add(title)
                            newSongsId.add(document.id)
                            continue
                        }

                        val tag3 = document.toObject(DataItems::class.java).tag3
                        val regexTag3 = Regex(searchT)
                        if(regexTag3.containsMatchIn(tag3)) {
                            listtt.add(title)
                            newSongsId.add(document.id)
                            continue
                        }

                        val tag4 = document.toObject(DataItems::class.java).tag4
                        val regexTag4 = Regex(searchT)
                        if(regexTag4.containsMatchIn(tag4)) {
                            listtt.add(title)
                            newSongsId.add(document.id)
                            continue
                        }

                        val tag5 = document.toObject(DataItems::class.java).tag5
                        val regexTag5 = Regex(searchT)
                        if(regexTag5.containsMatchIn(tag5)) {
                            listtt.add(title)
                            newSongsId.add(document.id)
                            continue
                        }

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