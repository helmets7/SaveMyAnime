package com.example.savemyanime.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.savemyanime.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db = Firebase.firestore


        this.supportActionBar?.title = "Registro"

        binding.btnregister.setOnClickListener {
            if (binding.campoRegCorreo.text.toString() != "" && binding.campoRegPass.text.toString() != "")
                registrar(binding.campoRegCorreo.text.toString(), binding.campoRegPass.text.toString())
            else
                Toast.makeText(this, "Error debes rellenar todos los campos", Toast.LENGTH_LONG).show()
        }

        binding.btnIrlogin.setOnClickListener { irLogin() }

    }

    private fun registrar(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    registrarUsuario()
                    irLogin()
                } else {
                    Toast.makeText(this, "Error usuario/contraseña incorrectos", Toast.LENGTH_LONG).show()

                }
            }
    }

    private fun irLogin() {
        startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
        finish()
    }

    private fun registrarUsuario(){
        db.collection("usuarios")
            .document(auth.currentUser?.uid.toString())
            .set(mapOf(
                "apodo" to "",
                "bio" to "",
                "email" to binding.campoRegCorreo.text.toString(),
                "id" to auth.currentUser?.uid.toString(),
                "nombre" to "",
                "rolRef" to "kVUJgCV6Z1LWwj5eZFcp"


            ))
    }
}