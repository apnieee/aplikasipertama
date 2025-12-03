package com.example.aplikasipertama

import android.content.Intent
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
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)

        // inisialisasi spinner agama
        val spinnerAgama: Spinner = findViewById(R.id.spinnerAgama)
        val agama = arrayOf("Islam", "Kristen", "Katolik", "Hindu", "Buddha", "Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agama)
        spinnerAgama.adapter = adapter

        init()

        btSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val alamat = etAlamat.text.toString()
            val nohp = etNomorHP.text.toString()
            val jekel = if (rbLakiLaki.isChecked) "Laki-Laki" else "Perempuan"
            val agama = spinnerAgama.selectedItem.toString()

            val hobiList = mutableListOf<String>()
            if (cbMembaca.isChecked) hobiList.add("Membaca")
            if (cbMenanam.isChecked) hobiList.add("Menanam")
            if (cbBerandai.isChecked) hobiList.add("Berandai")
            if (cbTidur.isChecked) hobiList.add("Tidur")

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
