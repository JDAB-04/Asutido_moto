package com.example.asutido_moto.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asutido_moto.R
import com.google.android.material.textfield.TextInputEditText

class LoginActivity: AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Redireccionamiento a registro
        val startRegister = findViewById<TextView>(R.id.link_registro);
        startRegister.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java);
            startActivity(intent)
        }

        //Proceso de login
        val inpNombre = findViewById<TextInputEditText>(R.id.input_nombreLogin);
        val inpContrasena = findViewById<TextInputEditText>(R.id.input_contraseñaLogin);
        val btnLogin = findViewById<Button>(R.id.boton_login);
        btnLogin.setOnClickListener {
            comparacionLogin(inpNombre, inpContrasena);
        }
    }
    private fun comparacionLogin(inpNombre: TextInputEditText, inpContrasena: TextInputEditText){
        val nombre = inpNombre.text.toString().trim()
        val contrasena = inpContrasena.text.toString().trim()
        val sharedPreferences = getSharedPreferences("RegistroUsuario", MODE_PRIVATE);
        val nombreGuardado = sharedPreferences.getString("nombres", "")
        val contrasenaGuardada= sharedPreferences.getString("contraseña", "")
        if (nombre == nombreGuardado && contrasena == contrasenaGuardada){
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent);
            finish()
        }else{
            Toast.makeText(this, "Nombre de usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}