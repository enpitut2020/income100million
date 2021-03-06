package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.util.Log
import android.widget.Button
//import com.google.firebase.firestore.FirebaseFirestore
//import java.util.*
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {

    private var mToolbar: Toolbar? = null
    private val db = FirebaseFirestore.getInstance()
    private val TAG = "DocSnippets"
    private var playListId = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(mToolbar)

        val button2 = findViewById<Button>(R.id.edit_button)

        button2.setOnClickListener {

            // ここで画面遷移を行う
            val intent2 = Intent(this, EditPlayList::class.java)

            startActivity(intent2)
        }

        //リストのアイテムがタップされたら画面遷移
        val listI = findViewById<ListView>(R.id.topList)
        listI.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, songsListActivity::class.java)
            intent.putExtra("id", playListId[position])
            intent.putExtra("title", parent.getItemAtPosition(position).toString())
            startActivity(intent)
        }


        show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Top App Barのボタンから画面遷移する処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.main_menu_search_button) {

            // ここで画面遷移を行う
            val intent = Intent(this, SearchActivity::class.java)

            startActivity(intent)
        }
        return true
    }

    private fun show() {
        //最新のプレイリストを表示
        val listView = findViewById<ListView>(R.id.topList)

        db.collection("playLists")
            .orderBy("title", Query.Direction.DESCENDING)
            .limit(7)
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
    }
}