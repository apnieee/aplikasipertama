package com.example.aplikasipertama

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class CardActivity : AppCompatActivity() {
    // langkah 1 membuat variabel
    lateinit var cvMAINBeranda: CardView
    lateinit var cvMAINKoleksi: CardView
    lateinit var cvMAINTentangKami: CardView
    lateinit var cvMAINPromo: CardView
    lateinit var cvMAINPanduan: CardView
    lateinit var cvMAINHubungi: CardView
    lateinit var switchLanguage: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load bahasa SEBELUM setContentView
        loadLocale()

        enableEdgeToEdge()
        setContentView(R.layout.activity_card2)

        //langkah 4, memanggil fun init()
        init()

        // Set posisi switch sesuai bahasa saat ini
        val currentLang = getSharedPreferences("Settings", MODE_PRIVATE)
            .getString("My_Lang", "id")
        switchLanguage.isChecked = currentLang == "en"

        //langkah 5 menambahkan kode onclick pada cardview
        // Profil
        cvMAINBeranda.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Profil DiKlik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Formulir
        cvMAINKoleksi.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Formulir DiKlik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity, FormActivity::class.java)
            startActivity(intent)
        }

        // Kalkulator
        cvMAINTentangKami.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Kalkulator DiKlik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity, KalkulatorActivity::class.java)
            startActivity(intent)
        }

        // Transaksi
        cvMAINPromo.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Warung Gagal Diet DiKlik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity, TransaksiActivity::class.java)
            startActivity(intent)
        }

        // Konversi Suhu
        cvMAINPanduan.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Konversi Suhu DiKlik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity, KonversiSuhuActivity::class.java)
            startActivity(intent)
        }

        // Keluar (dengan dialog konfirmasi)
        cvMAINHubungi.setOnClickListener {
            showExitDialog()
        }

        // Switch Language
        switchLanguage.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Ganti ke bahasa English
                setLocale("en")
                Toast.makeText(this, "Language changed to English", Toast.LENGTH_SHORT).show()
            } else {
                // Ganti ke bahasa Indonesia
                setLocale("id")
                Toast.makeText(this, "Bahasa diganti ke Indonesia", Toast.LENGTH_SHORT).show()
            }
            // Restart activity untuk apply perubahan bahasa
            recreate()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // dialog konfirmasi keluar
    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Keluar")
            .setMessage("Yakin mau keluar?")
            .setPositiveButton("Ya") { dialog, _ ->
                finish()
                dialog.dismiss()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(true)
            .show()
    }

    // Fungsi untuk ganti bahasa
    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        // Simpan pilihan bahasa di SharedPreferences
        val editor = getSharedPreferences("Settings", MODE_PRIVATE).edit()
        editor.putString("My_Lang", languageCode)
        editor.apply()
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

    // langkah 2 membuat fun baru
    fun init(){
        //langkah 3 mengisikan variabel
        cvMAINBeranda = findViewById(R.id.berandaCard)
        cvMAINKoleksi = findViewById(R.id.koleksiCard)
        cvMAINTentangKami = findViewById(R.id.tentangKamiCard)
        cvMAINPromo = findViewById(R.id.promoCard)
        cvMAINPanduan = findViewById(R.id.panduanBelanjaCard)
        cvMAINHubungi = findViewById(R.id.hubungiCard)
        switchLanguage = findViewById(R.id.switchLanguage)
    }
}