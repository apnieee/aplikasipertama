package com.example.aplikasipertama

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class KalkulatorActivity : AppCompatActivity() {

    private lateinit var etangka: EditText
    private lateinit var tvHasil: TextView
    private var ekspresi: String = "" // Simpan seluruh ekspresi
    private var angkaSekarang: String = "" // Angka yang sedang diketik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load bahasa SEBELUM setContentView
        loadLocale()

        setContentView(R.layout.activity_kalkulator)

        etangka = findViewById(R.id.etangka)
        tvHasil = findViewById(R.id.tvHasil)

        // Nonaktifkan keyboard sistem
        etangka.showSoftInputOnFocus = false

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

        val btnPlus: Button = findViewById(R.id.btnPlus)
        val btnKurang: Button = findViewById(R.id.btnKurang)
        val btnKali: Button = findViewById(R.id.btnKali)
        val btnBagi: Button = findViewById(R.id.btnBagi)
        val btnSaDeng: Button = findViewById(R.id.btnSaDeng)

        val btnC: Button = findViewById(R.id.btnC)
        val btnDel: Button = findViewById(R.id.btnDel)

        // Tombol angka
        val numberClickListener = { angka: String ->
            angkaSekarang += angka
            ekspresi += angka
            updateDisplay()
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

        // Tombol koma
        btnKoma.setOnClickListener {
            if (!angkaSekarang.contains(".")) {
                if (angkaSekarang.isEmpty()) {
                    angkaSekarang = "0."
                    ekspresi += "0."
                } else {
                    angkaSekarang += "."
                    ekspresi += "."
                }
                updateDisplay()
            }
        }

        // Tombol operator
        val operatorClickListener = { op: String ->
            if (angkaSekarang.isNotEmpty()) {
                ekspresi += op
                angkaSekarang = ""
                updateDisplay()
            } else if (ekspresi.isNotEmpty() && ekspresi.last() in "+-*/") {
                // Ganti operator terakhir jika double-press operator
                ekspresi = ekspresi.dropLast(1) + op
                updateDisplay()
            }
        }

        btnPlus.setOnClickListener { operatorClickListener("+") }
        btnKurang.setOnClickListener { operatorClickListener("-") }
        btnKali.setOnClickListener { operatorClickListener("*") }
        btnBagi.setOnClickListener { operatorClickListener("/") }

        // Tombol sama dengan
        btnSaDeng.setOnClickListener {
            if (ekspresi.isNotEmpty() && angkaSekarang.isNotEmpty()) {
                try {
                    val hasil = evaluateExpression(ekspresi)

                    val hasilText = if (hasil % 1.0 == 0.0) {
                        hasil.toInt().toString()
                    } else {
                        hasil.toString()
                    }

                    // Tampilkan hasil di bawah (input baru)
                    etangka.setText(hasilText)
                    // Tampilkan ekspresi di atas
                    tvHasil.text = ekspresi

                    // Reset untuk operasi berikutnya
                    ekspresi = hasilText
                    angkaSekarang = hasilText

                } catch (e: Exception) {
                    tvHasil.text = "Error"
                    etangka.setText("")
                    ekspresi = ""
                    angkaSekarang = ""
                }
            }
        }

        // Tombol Clear
        btnC.setOnClickListener {
            ekspresi = ""
            angkaSekarang = ""
            tvHasil.text = "0"
            etangka.setText("")
        }

        // Tombol Delete
        btnDel.setOnClickListener {
            if (ekspresi.isNotEmpty()) {
                val lastChar = ekspresi.last()
                ekspresi = ekspresi.dropLast(1)

                if (lastChar.isDigit() || lastChar == '.') {
                    if (angkaSekarang.isNotEmpty()) {
                        angkaSekarang = angkaSekarang.dropLast(1)
                    }
                } else {
                    // Jika operator dihapus
                    angkaSekarang = ekspresi.takeLastWhile { it.isDigit() || it == '.' }
                }

                updateDisplay()
            }
        }
    }

    // Fungsi untuk load bahasa yang tersimpan
    private fun loadLocale() {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val language = prefs.getString("My_Lang", "id") ?: "id"

        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }

    private fun updateDisplay() {
        // Tampilkan angka yang sedang diketik di bawah (etangka)
        etangka.setText(if (angkaSekarang.isEmpty()) "" else angkaSekarang)
        etangka.setSelection(etangka.text.length)

        // Tampilkan ekspresi lengkap di atas (tvHasil)
        tvHasil.text = if (ekspresi.isEmpty()) "0" else ekspresi
    }

    private fun evaluateExpression(expr: String): Double {
        return object {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < expr.length) expr[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < expr.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.code) -> x += parseTerm() // Penjumlahan
                        eat('-'.code) -> x -= parseTerm() // Pengurangan
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('*'.code) -> x *= parseFactor() // Perkalian (prioritas lebih tinggi)
                        eat('/'.code) -> {
                            val divisor = parseFactor()
                            if (divisor == 0.0) throw RuntimeException("Division by zero")
                            x /= divisor
                        }
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor() // Unary +
                if (eat('-'.code)) return -parseFactor() // Unary -

                val startPos = pos
                if (eat('('.code)) { // Tanda kurung
                    val x = parseExpression()
                    eat(')'.code)
                    return x
                }

                // Parse angka
                while ((ch >= '0'.code && ch <= '9'.code) || ch == '.'.code) nextChar()

                if (startPos == pos) throw RuntimeException("Unexpected character")

                return expr.substring(startPos, pos).toDouble()
            }
        }.parse()
    }
}