package com.example.savemyanime.view

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ShareActionProvider
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.savemyanime.R
import com.example.savemyanime.databinding.ActivityHomeBinding
import com.example.savemyanime.databinding.ActivityLoginBinding
import com.example.savemyanime.model.modelUser
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {


    private lateinit var binding : ActivityHomeBinding
    private lateinit var infoUser : modelUser
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore
        auth = Firebase.auth

        this.supportActionBar?.title = "Home"

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }


        binding.btnSalir.setOnClickListener { logout() }
        db.collection("usuarios").document(auth.currentUser?.uid.toString())
            .get().addOnSuccessListener {
                infoUser = modelUser(
                    it.get("apodo").toString(),
                    it.get("bio").toString(),
                    it.get("email").toString(),
                    it.get("id").toString(),
                    it.get("nombre").toString(),
                    it.get("rolRef").toString()
                )
            } .addOnSuccessListener {
                binding.campoApodo.text = infoUser.apodo
                binding.campoBio.text = infoUser.bio
                binding.campoNombre.text = infoUser.nombre
            }

    }




    private fun logout() {
        Firebase.auth.signOut()
        startActivity(Intent(this@HomeActivity,LoginActivity::class.java))
        finish()
    }

}