package com.example.aplikasipertama

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class FormActivity : AppCompatActivity() {

    // variabel
    lateinit var etNama: EditText
    lateinit var etAlamat: EditText
    lateinit var etNomorHP: EditText
    lateinit var rbPerempuan: RadioButton
    lateinit var rbLakiLaki: RadioButton
    lateinit var spinnerAgama: Spinner
    lateinit var cbMembaca: CheckBox
    lateinit var cbMenanam: CheckBox
    lateinit var cbBerandai: CheckBox
    lateinit var cbTidur: CheckBox
    lateinit var btSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load bahasa SEBELUM setContentView
        loadLocale()

        enableEdgeToEdge()
        setContentView(R.layout.activity_form)

        init()

        // inisialisasi spinner agama dengan string resource (TIDAK DIUBAH)
        val agama = resources.getStringArray(R.array.daftar_agama)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agama)
        spinnerAgama.adapter = adapter

        btSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val alamat = etAlamat.text.toString()
            val nohp = etNomorHP.text.toString()

            // Ambil text dari RadioButton yang support multi-bahasa
            val jekel = if (rbLakiLaki.isChecked) rbLakiLaki.text.toString() else rbPerempuan.text.toString()
            val agama = spinnerAgama.selectedItem.toString()

            val hobiList = mutableListOf<String>()
            if (cbMembaca.isChecked) hobiList.add(cbMembaca.text.toString())
            if (cbMenanam.isChecked) hobiList.add(cbMenanam.text.toString())
            if (cbBerandai.isChecked) hobiList.add(cbBerandai.text.toString())
            if (cbTidur.isChecked) hobiList.add(cbTidur.text.toString())

            val hobi = hobiList.joinToString(", ")

            val keHasil = Intent(this, HasilFormActivity::class.java).apply {
                putExtra("nama", nama)
                putExtra("alamat", alamat)
                putExtra("nohp", nohp)
                putExtra("jeniskelamin", jekel)
                putExtra("agama", agama)
                putExtra("hobi", hobi)
            }

            startActivity(keHasil)
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

    private fun init() {
        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        etNomorHP = findViewById(R.id.etNomorHP)
        rbPerempuan = findViewById(R.id.rbPerempuan)
        rbLakiLaki = findViewById(R.id.rbLakiLaki)
        spinnerAgama = findViewById(R.id.spinnerAgama)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbMenanam = findViewById(R.id.cbMenanam)
        cbBerandai = findViewById(R.id.cbBerandai)
        cbTidur = findViewById(R.id.cbTidur)
        btSimpan = findViewById(R.id.btnSimpan)
    }
}