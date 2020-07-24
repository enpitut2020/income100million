package com.example.soundhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.soundhub.R.id.search_button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(search_button)

        button.setOnClickListener {
            // ここに任意の処理を実装する
            // 妥当かどうか
            /*
            var isValid = true

            val priceEditText = findViewById<EditText>(R.id.price)
            val priceText = priceEditText.text.toString()

            if (priceText.isEmpty()) {
                // 定価が未入力
                priceEditText.error = getString(R.string.price_error)
                isValid = false
            }

            val discountEditText = findViewById<EditText>(R.id.discount)
            val discountText = discountEditText.text.toString()

            if (discountText.isEmpty()) {
                // 割引率が未入力
                discountEditText.error = getString(R.string.discount_error)
                isValid = false
            }
            */

                // ここで画面遷移を行う
                val intent = Intent(this, SearchActivity::class.java)
                //intent.putExtra("price", price)
                //intent.putExtra("discount", discount) //値渡し
                startActivity(intent)
        }
    }
}