package com.example.mezenceva_pr31_var2

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class first_activity : AppCompatActivity() {

    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var pref: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        login = findViewById<EditText>(R.id.log)
        password = findViewById<EditText>(R.id.pass)
    }

    fun onClickStart(view: View) {
        pref = getPreferences(MODE_PRIVATE)
        val ed = pref.edit()
        val savedLog = pref.getString("login", "")
        val savedPas = pref.getString("password", "")

        if (pref.contains("login"))
        {
            if(login.text.toString() == savedLog)
            {
                if(password.text.toString() == savedPas)
                {
                    Toast.makeText(this,"Пользователь авторизован.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, second_activity::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this,"Введен неверный пароль!", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this,"Введен неверный логин!", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Toast.makeText(this,"Регистрация пользователя...", Toast.LENGTH_SHORT).show()

            ed.putString("login", login.text.toString())
            ed.putString("password", password.text.toString())
            ed.apply()
        }
    }
}