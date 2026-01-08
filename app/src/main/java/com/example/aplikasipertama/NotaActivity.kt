package com.example.aplikasipertama

import android.content.res.Configuration
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class NotaActivity : AppCompatActivity() {

    lateinit var tvNamaPel: TextView
    lateinit var tvDate: TextView
    lateinit var tbPesanan: TableLayout
    lateinit var tvGrandTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load bahasa SEBELUM setContentView
        loadLocale()

        setContentView(R.layout.activity_nota)

        // Inisialisasi views
        tvNamaPel = findViewById(R.id.tvNamaPel)
        tvDate = findViewById(R.id.tvDate)
        tbPesanan = findViewById(R.id.tbPesanan)
        tvGrandTotal = findViewById(R.id.tvGrandTotal)

        // Ambil data dari intent
        val namaPelanggan = intent.getStringExtra("NAMA_PELANGGAN") ?: "Pelanggan"

        // Data pesanan
        val seblakOri = intent.getIntExtra("SEBLAK_ORI", 0)
        val seblakCeker = intent.getIntExtra("SEBLAK_CEKER", 0)
        val seblakSeafood = intent.getIntExtra("SEBLAK_SEAFOOD", 0)
        val esTeh = intent.getIntExtra("ES_TEH", 0)
        val esJeyuk = intent.getIntExtra("ES_JEYUK", 0)
        val tehPanas = intent.getIntExtra("TEH_PANAS", 0)
        val jeyukPanas = intent.getIntExtra("JEYUK_PANAS", 0)

        // Harga per item
        val hargaSeblakOri = 8000
        val hargaSeblakCeker = 11000
        val hargaSeblakSeafood = 15000
        val hargaEsTeh = 3000
        val hargaEsJeyuk = 4000
        val hargaTehPanas = 2500
        val hargaJeyukPanas = 3500

        // Set nama pelanggan
        tvNamaPel.text = "Nama: $namaPelanggan"

        // Set tanggal
        val currentDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        tvDate.text = "Tanggal: $currentDate"

        // Format rupiah
        val rupiahFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

        var grandTotal = 0
        var nomor = 1

        // Tambahkan pesanan ke tabel
        if (seblakOri > 0) {
            val subtotal = seblakOri * hargaSeblakOri
            addTableRow(nomor++, "Seblak Original", seblakOri, subtotal, rupiahFormat)
            grandTotal += subtotal
        }

        if (seblakCeker > 0) {
            val subtotal = seblakCeker * hargaSeblakCeker
            addTableRow(nomor++, "Seblak Ceker", seblakCeker, subtotal, rupiahFormat)
            grandTotal += subtotal
        }

        if (seblakSeafood > 0) {
            val subtotal = seblakSeafood * hargaSeblakSeafood
            addTableRow(nomor++, "Seblak Seafood", seblakSeafood, subtotal, rupiahFormat)
            grandTotal += subtotal
        }

        if (esTeh > 0) {
            val subtotal = esTeh * hargaEsTeh
            addTableRow(nomor++, "Es Teh", esTeh, subtotal, rupiahFormat)
            grandTotal += subtotal
        }

        if (esJeyuk > 0) {
            val subtotal = esJeyuk * hargaEsJeyuk
            addTableRow(nomor++, "Es Jeyuk", esJeyuk, subtotal, rupiahFormat)
            grandTotal += subtotal
        }

        if (tehPanas > 0) {
            val subtotal = tehPanas * hargaTehPanas
            addTableRow(nomor++, "Teh Panas", tehPanas, subtotal, rupiahFormat)
            grandTotal += subtotal
        }

        if (jeyukPanas > 0) {
            val subtotal = jeyukPanas * hargaJeyukPanas
            addTableRow(nomor++, "Jeyuk Panas", jeyukPanas, subtotal, rupiahFormat)
            grandTotal += subtotal
        }

        // Set grand total
        tvGrandTotal.text = rupiahFormat.format(grandTotal)
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

    private fun addTableRow(no: Int, item: String, jumlah: Int, subtotal: Int, rupiahFormat: NumberFormat) {
        val tableRow = TableRow(this)

        // Nomor
        val tvNo = TextView(this)
        tvNo.text = no.toString()
        tvNo.setPadding(8, 8, 8, 8)
        tableRow.addView(tvNo)

        // Item
        val tvItem = TextView(this)
        tvItem.text = item
        tvItem.setPadding(8, 8, 8, 8)
        tableRow.addView(tvItem)

        // Jumlah
        val tvJumlah = TextView(this)
        tvJumlah.text = jumlah.toString()
        tvJumlah.setPadding(8, 8, 8, 8)
        tableRow.addView(tvJumlah)

        // Subtotal
        val tvSubtotal = TextView(this)
        tvSubtotal.text = rupiahFormat.format(subtotal)
        tvSubtotal.setPadding(8, 8, 8, 8)
        tableRow.addView(tvSubtotal)

        // Tambahkan row ke tabel
        tbPesanan.addView(tableRow)
    }
}