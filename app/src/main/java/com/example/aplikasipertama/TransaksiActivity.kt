package com.example.aplikasipertama

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class TransaksiActivity : AppCompatActivity() {

    lateinit var etOri: EditText
    lateinit var etCeker: EditText
    lateinit var etSipud: EditText
    lateinit var etEsteh: EditText
    lateinit var etEsjeyuk: EditText
    lateinit var etTeh: EditText
    lateinit var etJeyuk: EditText
    lateinit var etNama: EditText
    lateinit var btnHitung: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load bahasa SEBELUM setContentView
        loadLocale()

        setContentView(R.layout.activity_transaksi)

        etOri = findViewById(R.id.etOri)
        etCeker = findViewById(R.id.etCeker)
        etSipud = findViewById(R.id.etSipud)
        etEsteh = findViewById(R.id.etEsteh)
        etEsjeyuk = findViewById(R.id.etEsjeyuk)
        etTeh = findViewById(R.id.etTeh)
        etJeyuk = findViewById(R.id.etJeyuk)
        etNama = findViewById(R.id.etNama)
        btnHitung = findViewById(R.id.btnHitung)

        btnHitung.setOnClickListener {
            hitungTransaksi()
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

    private fun hitungTransaksi() {

        val namaPelanggan = etNama.text.toString().trim()
        if (namaPelanggan.isEmpty()) {
            Toast.makeText(this, "Nama pelanggan harus diisi!", Toast.LENGTH_SHORT).show()
            etNama.requestFocus()
            return
        }

        val seblakOri = etOri.text.toString().toIntOrNull() ?: 0
        val seblakCeker = etCeker.text.toString().toIntOrNull() ?: 0
        val seblakSeafood = etSipud.text.toString().toIntOrNull() ?: 0
        val esTeh = etEsteh.text.toString().toIntOrNull() ?: 0
        val esJeyuk = etEsjeyuk.text.toString().toIntOrNull() ?: 0
        val tehPanas = etTeh.text.toString().toIntOrNull() ?: 0
        val jeyukPanas = etJeyuk.text.toString().toIntOrNull() ?: 0

        val totalItem = seblakOri + seblakCeker + seblakSeafood + esTeh + esJeyuk + tehPanas + jeyukPanas
        if (totalItem == 0) {
            Toast.makeText(this, "Minimal pesan 1 item!", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, NotaActivity::class.java).apply {
            putExtra("NAMA_PELANGGAN", namaPelanggan)
            putExtra("SEBLAK_ORI", seblakOri)
            putExtra("SEBLAK_CEKER", seblakCeker)
            putExtra("SEBLAK_SEAFOOD", seblakSeafood)
            putExtra("ES_TEH", esTeh)
            putExtra("ES_JEYUK", esJeyuk)
            putExtra("TEH_PANAS", tehPanas)
            putExtra("JEYUK_PANAS", jeyukPanas)
        }
        startActivity(intent)
    }
}