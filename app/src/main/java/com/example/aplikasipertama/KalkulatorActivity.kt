package com.example.aplikasipertama

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KalkulatorActivity : AppCompatActivity() {

    private lateinit var etangka: EditText
    private lateinit var tvHasil: TextView
    private var angkaPertama: Double = 0.0
    private var operator: String = ""
    private var isOperatorPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        etangka = findViewById(R.id.etangka)
        tvHasil = findViewById(R.id.tvHasil)

        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val btn00: Button = findViewById(R.id.btn00)
        val btnKoma: Button = findViewById(R.id.btnKoma)

        // Tombol operator
        val btnPlus: Button = findViewById(R.id.btnPlus)
        val btnKurang: Button = findViewById(R.id.btnKurang)
        val btnKali: Button = findViewById(R.id.btnKali)
        val btnBagi: Button = findViewById(R.id.btnBagi)
        val btnSaDeng: Button = findViewById(R.id.btnSaDeng)

        // Tombol fungsi
        val btnC: Button = findViewById(R.id.btnC)
        val btnDel: Button = findViewById(R.id.btnDel)

        // Set listener untuk tombol angka
        val numberClickListener = { angka: String ->
            if (isOperatorPressed) {
                etangka.setText("")
                isOperatorPressed = false
            }
            val currentText = etangka.text.toString()
            val newText = currentText + angka
            etangka.setText(newText)
            updateDisplay(newText)
        }

        btn0.setOnClickListener { numberClickListener("0") }
        btn1.setOnClickListener { numberClickListener("1") }
        btn2.setOnClickListener { numberClickListener("2") }
        btn3.setOnClickListener { numberClickListener("3") }
        btn4.setOnClickListener { numberClickListener("4") }
        btn5.setOnClickListener { numberClickListener("5") }
        btn6.setOnClickListener { numberClickListener("6") }
        btn7.setOnClickListener { numberClickListener("7") }
        btn8.setOnClickListener { numberClickListener("8") }
        btn9.setOnClickListener { numberClickListener("9") }
        btn00.setOnClickListener { numberClickListener("00") }

        // Tombol koma (desimal)
        btnKoma.setOnClickListener {
            val currentText = etangka.text.toString()
            if (!currentText.contains(".") && currentText.isNotEmpty()) {
                val newText = currentText + "."
                etangka.setText(newText)
                updateDisplay(newText)
            }
        }

        // Set listener untuk operator
        val operatorClickListener = { op: String ->
            val text = etangka.text.toString()
            if (text.isNotEmpty()) {
                angkaPertama = text.toDouble()
                operator = op
                isOperatorPressed = true
            }
        }

        btnPlus.setOnClickListener { operatorClickListener("+") }
        btnKurang.setOnClickListener { operatorClickListener("-") }
        btnKali.setOnClickListener { operatorClickListener("*") }
        btnBagi.setOnClickListener { operatorClickListener("/") }

        // Tombol sama dengan (=)
        btnSaDeng.setOnClickListener {
            val text = etangka.text.toString()
            if (text.isNotEmpty() && operator.isNotEmpty()) {
                val angkaKedua = text.toDouble()
                val hasil = when (operator) {
                    "+" -> angkaPertama + angkaKedua
                    "-" -> angkaPertama - angkaKedua
                    "*" -> angkaPertama * angkaKedua
                    "/" -> {
                        if (angkaKedua != 0.0) {
                            angkaPertama / angkaKedua
                        } else {
                            etangka.setText("Error")
                            tvHasil.text = "Error"
                            return@setOnClickListener
                        }
                    }
                    else -> 0.0
                }

                // Tampilkan hasil tanpa desimal jika bilangan bulat
                val hasilText = if (hasil % 1.0 == 0.0) {
                    hasil.toInt().toString()
                } else {
                    hasil.toString()
                }

                etangka.setText(hasilText)
                tvHasil.text = hasilText

                operator = ""
                isOperatorPressed = true
            }
        }

        // Tombol C (Clear)
        btnC.setOnClickListener {
            etangka.setText("")
            tvHasil.text = "0"
            angkaPertama = 0.0
            operator = ""
            isOperatorPressed = false
        }

        // Tombol Delete
        btnDel.setOnClickListener {
            val text = etangka.text.toString()
            if (text.isNotEmpty()) {
                val newText = text.substring(0, text.length - 1)
                etangka.setText(newText)
                updateDisplay(newText)
            }
        }
    }

    // Fungsi untuk update display TextView
    private fun updateDisplay(text: String) {
        if (text.isEmpty()) {
            tvHasil.text = "0"
        } else {
            tvHasil.text = text
        }
    }
}