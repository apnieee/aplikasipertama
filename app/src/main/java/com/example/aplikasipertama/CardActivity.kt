package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardActivity : AppCompatActivity() {
    // langkah 1 membuat variabel
    lateinit var cvMAINBeranda: CardView
    lateinit var cvMAINKoleksi: CardView
    lateinit var cvMAINTentangKami: CardView
    lateinit var cvMAINPromo: CardView
    lateinit var cvMAINPanduan: CardView
    lateinit var cvMAINHubungi: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card2)

        //langkah 4, memanggil fun init()
        init()

        //langkah 5 menambahkan kode onclick pada cardview
        // Profil
        cvMAINBeranda.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Profil DiKlik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Formulir
        cvMAINKoleksi.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Profil DiKlik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity, FormActivity::class.java)
            startActivity(intent)
        }

        // Kalkulator
        cvMAINTentangKami.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Profil DiKlik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity, KalkulatorActivity::class.java)
            startActivity(intent)
        }

        // Warung Gagal Diet (Transaksi)
        cvMAINPromo.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Profil DiKlik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity, TransaksiActivity::class.java)
            startActivity(intent)
        }

        // Konversi Suhu
        cvMAINPanduan.setOnClickListener {
            val intent = Intent(this@CardActivity, KonversiSuhuActivity::class.java)
            startActivity(intent)
        }

        // Keluar (dengan dialog konfirmasi)
        cvMAINHubungi.setOnClickListener {
            Toast.makeText(this@CardActivity, "CardView Profil DiKlik", Toast.LENGTH_SHORT).show()
            showExitDialog()
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

    // langkah 2 membuat fun baru
    fun init(){
        //langkah 3 mengisikan variabel
        cvMAINBeranda = findViewById(R.id.berandaCard)
        cvMAINKoleksi = findViewById(R.id.koleksiCard)
        cvMAINTentangKami = findViewById(R.id.tentangKamiCard)
        cvMAINPromo = findViewById(R.id.promoCard)
        cvMAINPanduan = findViewById(R.id.panduanBelanjaCard)
        cvMAINHubungi = findViewById(R.id.hubungiCard)
    }
}