package com.example.mycurrencyconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var inputEditText: EditText
    private lateinit var convertButton: Button
    private lateinit var resultEditText: EditText
    private lateinit var clearButton: Button

    // Fixed exchange rate (USD to IDR) - update this value as needed
    private val exchangeRate = 16795.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize UI components
        inputEditText = findViewById(R.id.input)
        convertButton = findViewById(R.id.button)
        resultEditText = findViewById(R.id.result)
        clearButton = findViewById(R.id.clear_button)

        // Set up window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up button click listener for conversion
        convertButton.setOnClickListener {
            convertCurrency()
        }

        clearButton.setOnClickListener {
            clearFields()
        }
    }

    private fun convertCurrency() {
        val inputText = inputEditText.text.toString()

        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter a USD amount", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            // Convert input to double and calculate result
            val usdAmount = inputText.toDouble()
            if (usdAmount < 0) {
                Toast.makeText(this, "Amount cannot be negative", Toast.LENGTH_SHORT).show()
                return
            }

            val idrAmount = usdAmount * exchangeRate

            // Format result with 2 decimal places and display
            resultEditText.setText(String.format("%,.2f", idrAmount))
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearFields(){
        inputEditText.text.clear()
        resultEditText.text.clear()
    }
}