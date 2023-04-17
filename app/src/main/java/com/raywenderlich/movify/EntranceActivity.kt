package com.raywenderlich.movify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EntranceActivity : AppCompatActivity() {
    private  lateinit var enterButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        enterButton = findViewById(R.id.enter)

        enterButton.setOnClickListener {
            val intent = Intent(this@EntranceActivity, IntentActivity::class.java)
            startActivity(intent)
        }
    }

}