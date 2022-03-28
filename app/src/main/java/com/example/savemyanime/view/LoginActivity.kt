package com.example.savemyanime.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.savemyanime.R
import com.example.savemyanime.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        this.supportActionBar?.title = "Login"




        binding.btnlogin.setOnClickListener{
            if (binding.campoCorreo.text.toString() != "" && binding.campoPass.text.toString() != "")
                iniciarSesion(binding.campoCorreo.text.toString(), binding.campoPass.text.toString())
            else
                Toast.makeText(this, "Error debes rellenar todos los campos", Toast.LENGTH_LONG).show()

        }

        binding.btnIrRegister.setOnClickListener { irRegister() }
    }

    private fun iniciarSesion(email:String, password:String) {

        var emailr = ""
        if (!email.contains("@gmail.com")) emailr = "$email@gmail.com" else emailr = email

        auth.signInWithEmailAndPassword(emailr, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    irHome()
                } else {
                    Toast.makeText(this, "El usuario y la contraseña no son correctos y vuelva a intentarl", Toast.LENGTH_LONG).show()
                }
            }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            irHome();
        }
    }

    private fun irHome() {
        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
        finish()
    }

    private fun irRegister() {
        startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        finish()
    }
}