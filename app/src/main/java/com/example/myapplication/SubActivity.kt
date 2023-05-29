package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val TextRange : TextView = findViewById<TextView>(R.id.TextRange)
        val RandomView : TextView = findViewById<TextView>(R.id.RandomView)
        val number = intent.getIntExtra("num",0)
        val ran= (0..number)
        val random_num=ran.random()


        TextRange.text = getString(R.string.textrange,number)//"Here is a random number between 0 and " + number
        RandomView.text=random_num.toString()

        //Here is a random number between 0 and
    }
}