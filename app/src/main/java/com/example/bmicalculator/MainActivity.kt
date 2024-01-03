package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightInput = findViewById<EditText>(R.id.weightInput)
        val heightInput = findViewById<EditText>(R.id.heightInput)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)


        btnCalculate.setOnClickListener {
            val weight = weightInput.text.toString()
            val height = heightInput.text.toString()

            if (weight.isNotEmpty() && height.isNotEmpty()) {

                val bmi = weight.toFloat() / (height.toFloat() / 100).pow(2)
                val bmiRoundOff2 = String.format("%.2f", bmi).toFloat()
                displayResult(bmiRoundOff2)
            } else {
                Toast.makeText(this, "Weight or Height not entered!", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun displayResult(bmi: Float) {
        val tvBmiNo = findViewById<TextView>(R.id.tvBmiNo)
        val tvResultLine = findViewById<TextView>(R.id.tvResultLine)
        val tvRange = findViewById<TextView>(R.id.tvRange)

        tvBmiNo.text = bmi.toString()
        tvResultLine.text = ""
        tvRange.text = getString(R.string.normalVal)

        var color = 0
        var result = ""

        when {
            bmi < 18.50 -> {
                result = "Underweight"
                color = R.color.under_weight
            }

            bmi in 18.50..24.99 -> {
                result = "Healthy"
                color = R.color.normal
            }

            bmi in 25.00..29.99 -> {
                result = "Overweight"
                color = R.color.over_weight
            }

            bmi > 29.99 -> {
                result = "Obese"
                color = R.color.obese
            }

        }
        tvResultLine.setTextColor(ContextCompat.getColor(this, color))
        tvResultLine.text = "You are $result"
    }
}