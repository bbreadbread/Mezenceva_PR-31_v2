package com.example.mezenceva_pr31_var2

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class second_activity : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var inputUnit: Spinner
    private lateinit var outputUnit: Spinner
    private lateinit var outputResult: EditText
    private lateinit var calculateButton: Button
    private val units = arrayOf("Байты", "Килобайты", "Мегабайты", "Гигабайты")

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        inputNumber = findViewById(R.id.inputValues)
        inputUnit = findViewById(R.id.spinnerFrom)
        outputUnit = findViewById(R.id.spinnerIn)
        outputResult = findViewById(R.id.result)
        calculateButton = findViewById(R.id.result_button)

        inputUnit.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        outputUnit.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)


        calculateButton.setOnClickListener {
            val number = inputNumber.text.toString().toDoubleOrNull()
            if (number != null) {
                val inputUnitValue = inputUnit.selectedItemPosition
                val outputUnitValue = outputUnit.selectedItemPosition

                val inputValue = inputUnit.selectedItem
                val outputValue = outputUnit.selectedItem

                val result = convert(number, inputUnitValue, outputUnitValue)

                outputResult.setText(result.toString())

                val intent = Intent(this, fierd_activity::class.java)
                intent.putExtra("value", number.toString())
                intent.putExtra("from", inputValue.toString())
                intent.putExtra("in",  outputValue.toString())
                intent.putExtra("result", result.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Введите корректное число", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun convert(value: Double, fromUnit: Int, toUnit: Int): Double {
        val byteValue = when (fromUnit) {
            0 -> value
            1 -> value * 1024
            2 -> value * 1024 * 1024
            3 -> value * 1024 * 1024 * 1024
            else -> value
        }

        return when (toUnit) {
            0 -> byteValue
            1 -> byteValue / 1024
            2 -> byteValue / (1024 * 1024)
            3 -> byteValue / (1024 * 1024 * 1024)
            else -> byteValue
        }
    }
}