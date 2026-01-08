package com.example.aplikasipertama

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class HasilFormActivity : AppCompatActivity() {
    lateinit var tvNama: TextView
    lateinit var tvNomorHP: TextView
    lateinit var tvAlamat: TextView
    lateinit var tvJenisKelamin: TextView
    lateinit var tvAgama: TextView
    lateinit var tvHobi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load bahasa SEBELUM setContentView
        loadLocale()

        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_form)
        init()
        getData()

        val btnKembali: Button = findViewById(R.id.btnKembali)
        btnKembali.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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

    fun init() {
        tvNama = findViewById(R.id.tvNaleng)
        tvNomorHP = findViewById(R.id.tvNohap)
        tvAlamat = findViewById(R.id.tvAleng)
        tvJenisKelamin = findViewById(R.id.tvJekel)
        tvAgama = findViewById(R.id.tvAgama)
        tvHobi = findViewById(R.id.tvHobi)
    }

    fun getData() {
        val nama = intent.getStringExtra("nama").toString()
        val nohp = intent.getStringExtra("nohp").toString()
        val alamat = intent.getStringExtra("alamat").toString()
        val jeniskelamin = intent.getStringExtra("jeniskelamin").toString()
        val agama = intent.getStringExtra("agama").toString()
        val hobi = intent.getStringExtra("hobi").toString()

        // Gunakan string resource untuk label supaya support multi-bahasa
        tvNama.text = "${getString(R.string.nama_lengkap)}: $nama"
        tvNomorHP.text = "${getString(R.string.nomor_hp)}: $nohp"
        tvAlamat.text = "${getString(R.string.alamat)}: $alamat"
        tvJenisKelamin.text = "${getString(R.string.jenis_kelamin)}: $jeniskelamin"
        tvAgama.text = "${getString(R.string.agama)}: $agama"
        tvHobi.text = "${getString(R.string.hobi)}: $hobi"
    }
}