package com.example.mezenceva_pr31_var2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class fierd_activity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fierd)

        val value = intent.getStringExtra("value")
        val from = intent.getStringExtra("from")
        val v_in = intent.getStringExtra("in")
        val result = intent.getStringExtra("result")

        findViewById<TextView>(R.id.outputValues).text = value
        findViewById<TextView>(R.id.outputFrom).text = from
        findViewById<TextView>(R.id.outputIn).text = v_in
        findViewById<TextView>(R.id.outputResult).text = result

    }

    fun comeback(view: View) {
        val intent = Intent(this, first_activity::class.java)
        startActivity(intent)
    }
}