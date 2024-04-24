package com.example.chatup

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textViewWelcome: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Puoi personalizzare l'immagine e il testo di benvenuto come desideri
        imageView =  findViewById(R.id.imageView)
        textViewWelcome =  findViewById(R.id.textViewWelcome)
        imageView.setImageResource(R.drawable.welcome)
        textViewWelcome.text = "Benvenuto!"
    }
}