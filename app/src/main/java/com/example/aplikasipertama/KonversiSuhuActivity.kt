package com.example.aplikasipertama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KonversiSuhuActivity : AppCompatActivity() {

    private lateinit var etAsal: EditText
    private lateinit var etHasil: EditText
    private lateinit var spinnerAsal: Spinner
    private lateinit var spinnerHasil: Spinner
    private lateinit var btnProses: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konversi_suhu_acivity)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // inisialisasi view
        etAsal = findViewById(R.id.etasal)
        etHasil = findViewById(R.id.ethasil)
        spinnerAsal = findViewById(R.id.spinnerasal)
        spinnerHasil = findViewById(R.id.spinnerhasil)
        btnProses = findViewById(R.id.btnProses)

        // isi pilihan spinner
        val options = arrayOf("Celsius", "Fahrenheit", "Kelvin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerAsal.adapter = adapter
        spinnerHasil.adapter = adapter

        btnProses.setOnClickListener {
            val asalValue = etAsal.text.toString()

            if (asalValue.isEmpty()) {
                Toast.makeText(this, "Isi dulu suhu asalnya yaa 😭", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val input = asalValue.toDouble()
            val from = spinnerAsal.selectedItem.toString()
            val to = spinnerHasil.selectedItem.toString()

            val result = convertSuhu(input, from, to)
            etHasil.setText(result.toString())
        }
    }

    private fun convertSuhu(value: Double, from: String, to: String): Double {
        // ubah ke Celsius dulu
        val celsius = when (from) {
            "Celsius" -> value
            "Fahrenheit" -> (value - 32) * 5 / 9
            "Kelvin" -> value - 273.15
            else -> value
        }

        // dari Celsius ke target
        return when (to) {
            "Celsius" -> celsius
            "Fahrenheit" -> (celsius * 9 / 5) + 32
            "Kelvin" -> celsius + 273.15
            else -> celsius
        }
    }

}
