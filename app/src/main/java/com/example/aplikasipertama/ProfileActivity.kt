package com.example.aplikasipertama

import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var civAku: CircleImageView
    private lateinit var ivInsto: ImageView
    private lateinit var ivTiktok: ImageView
    private lateinit var ivGithub: ImageView
    private lateinit var linearInsto: LinearLayout
    private lateinit var linearTiktok: LinearLayout
    private lateinit var linearGithub: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load bahasa SEBELUM setContentView
        loadLocale()

        setContentView(R.layout.activity_profile)

        initViews()
        setupClickListeners()
        enforceTextColors()
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

    private fun initViews() {
        civAku = findViewById(R.id.civaku)

        ivInsto = findViewById(R.id.ivinsto)
        ivTiktok = findViewById(R.id.ivtiktok)
        ivGithub = findViewById(R.id.ivgithub)

        linearInsto = findViewById(R.id.linear2)
        linearTiktok = findViewById(R.id.linear3)
        linearGithub = findViewById(R.id.linear4)
    }

    private fun setupClickListeners() {
        linearInsto.setOnClickListener {
            openSocialMedia("instagram", "syfagratitude")
        }

        linearTiktok.setOnClickListener {
            openSocialMedia("tiktok", "bukan.opet")
        }

        linearGithub.setOnClickListener {
            openSocialMedia("github", "sipaakk")
        }
    }

    private fun openSocialMedia(platform: String, username: String) {
        val intent = when (platform) {
            "instagram" -> {
                android.content.Intent(
                    android.content.Intent.ACTION_VIEW,
                    android.net.Uri.parse("https://instagram.com/$username")
                )
            }
            "tiktok" -> {
                android.content.Intent(
                    android.content.Intent.ACTION_VIEW,
                    android.net.Uri.parse("https://www.tiktok.com/@$username")
                )
            }
            "github" -> {
                android.content.Intent(
                    android.content.Intent.ACTION_VIEW,
                    android.net.Uri.parse("https://github.com/$username")
                )
            }
            else -> null
        }

        intent?.let { startActivity(it) }
    }

    private fun enforceTextColors() {
        findViewById<android.widget.TextView>(R.id.tvjudul)?.apply {
            setTextColor(ContextCompat.getColor(this@ProfileActivity, android.R.color.black))
        }

        findViewById<android.widget.TextView>(R.id.tvsubjudul)?.apply {
            setTextColor(ContextCompat.getColor(this@ProfileActivity, android.R.color.black))
        }
    }
}