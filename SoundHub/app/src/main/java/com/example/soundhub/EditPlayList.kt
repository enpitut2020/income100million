package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_edit_play_list.*

class EditPlayList : AppCompatActivity() {
    private val TITLE_KEY = "title"
    private val TAG1_KEY = "tag1"
    private val TAG2_KEY = "tag2"
    private val TAG3_KEY = "tag3"
    private val TAG4_KEY = "tag4"
    private val TAG5_KEY = "tag5"
    private val MUSIC_KEY = "music"

    private val mDocRef = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_play_list)
        //listTitleName.text = intent.getIntExtra("potition", 0).toString()

        //保存ボタンをタップすると、情報が保存される
        save_button.setOnClickListener {
            saveQuote(it)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    fun saveQuote(view : View){
        val titleView = findViewById<EditText>(R.id.listTitleName).text.toString()
        val tag1View = findViewById<EditText>(R.id.tag1Name).text.toString()
        val tag2View = findViewById<EditText>(R.id.tag2Name).text.toString()
        val tag3View = findViewById<EditText>(R.id.tag3Name).text.toString()
        val tag4View = findViewById<EditText>(R.id.tag4Name).text.toString()
        val tag5View = findViewById<EditText>(R.id.tag5Name).text.toString()

        if (titleView.isEmpty()){
            return
        }

        val saveMap = hashMapOf(
            TITLE_KEY to titleView,
            TAG1_KEY to tag1View,
            TAG2_KEY to tag2View,
            TAG3_KEY to tag3View,
            TAG4_KEY to tag4View,
            TAG5_KEY to tag5View
        )

        mDocRef.collection("playLists")
            .add(saveMap)
            .addOnSuccessListener { documentReference ->
                Log.d("savePlayList", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("savePlayList", "Error adding document", e)
            }

    }
}