package com.example.chatup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var buttonRegister: Button
    lateinit var buttonLogin: Button
    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
//        updateUI(currentUser)
        Log.i(" >>***** sono sul metodo   :","onstart = ${currentUser.toString()}")
        Toast.makeText(this,"utente già connesso",Toast.LENGTH_SHORT)
    }
      override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// ...
// Initialize Firebase Auth
        auth = Firebase.auth
        Log.i(" >>***** sono sul metodo   :","oncreate = ${auth.toString()}")
      //  auth = FirebaseAuth.getInstance()
        buttonRegister =  findViewById(R.id.buttonRegister)
        buttonLogin =  findViewById(R.id.buttonLogin)
        editTextEmail =  findViewById(R.id.editTextEmail)
        editTextPassword =  findViewById(R.id.editTextPassword)

        buttonRegister.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Inserisci email e password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show()
                        // Puoi fare altre operazioni come accedere all'utente, ecc.
                    } else {
                        Toast.makeText(applicationContext, "Registrazione fallita: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
          buttonLogin.setOnClickListener {
              val email = editTextEmail.text.toString().trim()
              val password = editTextPassword.text.toString().trim()

              if (email.isEmpty() || password.isEmpty()) {
                  Toast.makeText(applicationContext, "Inserisci email e password", Toast.LENGTH_SHORT).show()
                  return@setOnClickListener
              }

              auth.signInWithEmailAndPassword(email, password)
                  .addOnCompleteListener(this) { task ->
                      if (task.isSuccessful) {
                          Toast.makeText(applicationContext, "Accesso effettuato con successo", Toast.LENGTH_SHORT).show()
                          // Puoi fare altre operazioni come accedere all'utente, ecc.
                          openWelcomeActivity()
                      } else {
                          Toast.makeText(applicationContext, "Accesso fallito: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                      }
                  }
          }

    }
    private fun openWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}