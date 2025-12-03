package com.example.aplikasipertama

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HasilFormActivity : AppCompatActivity() {
    lateinit var tvNama: TextView
    lateinit var tvNomorHP: TextView
    lateinit var tvAlamat: TextView
    lateinit var tvJenisKelamin: TextView
    lateinit var tvAgama: TextView
    lateinit var tvHobi: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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






        tvNama.text = "Nama Lengkap: $nama"
        tvNomorHP.text = "Nomor HP: $nohp"
        tvAlamat.text = "Alamat Lengkap: $alamat"
        tvJenisKelamin.text = "Jenis Kelamin: $jeniskelamin"
        tvAgama.text = "Agama: $agama"
        tvHobi.text = "Hobi: $hobi"


    }
}