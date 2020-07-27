package com.example.soundhub

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.AccessControlContext

private const val DB_NAME = "SampleDataBase"
private const val DB_VERSION = 1

class ListDataBase(context: Context) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE texts (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " text TEXT NOT NULL, " +
                " tag1 TEXT DEFAULT 'hoge', " +
                " tag2 TEXT DEFAULT 'a', " +
                " tag3 TEXT DEFAULT 'a', " +
                " tag4 TEXT DEFAULT 'a', " +
                " tag5 TEXT DEFAULT 'a', " +
                " created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}

//レコード内を全探索
fun queryTexts(context: Context) : List<String> {
    val database = ListDataBase(context).readableDatabase
    val cursor = database.query(
        "texts", null, null, null, null, null, "created_at DESC"
    )
    val texts = mutableListOf<String>()

    cursor.use {
        while(cursor.moveToNext()) {
            val text = cursor.getString(cursor.getColumnIndex("text"))
            texts.add(text)
        }
    }

    database.close()
    return texts
}

//レコードにプレイリスト名を挿入する
fun insertText(context: Context, text: String) {
    val database = ListDataBase(context).writableDatabase

    database.use { db->
        val record = ContentValues().apply {
            put("text", text)
        }

        db.insert("texts", null, record)
    }
}

//レコード内を検索する
fun searchTexts(context: Context, sText: String) : List<String> {
    val database = ListDataBase(context).readableDatabase
    val cursor = database.query(
        "texts", null, "text like '%${sText}%'", null, null, null, "created_at DESC"
    )
    val texts = mutableListOf<String>()

    cursor.use {
        while(cursor.moveToNext()) {
            val text = cursor.getString(cursor.getColumnIndex("text"))
            texts.add(text)
        }
    }

    database.close()
    return texts
}

//レコードにタグを挿入、保存する
fun editPlayListTags(context: Context, tag1: String, tag2: String, tag3: String, tag4: String, tag5: String, titleName: String) {
    val database = ListDataBase(context).writableDatabase

    database.use { db->
        val update = ContentValues().apply {
            put("tag1", tag1)
            put("tag2", tag2)
            put("tag3", tag3)
            put("tag4", tag4)
            put("tag5", tag5)
        }

        db.update("texts", update, "text like ?", arrayOf(titleName))
    }
}

