package com.example.asutido_moto.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asutido_moto.R
import com.google.android.material.textfield.TextInputEditText

class RegistroActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        sharedPreferences = getSharedPreferences("RegistroUsuario", MODE_PRIVATE)
        setupOnClickListeners()
    }

    //Guardado de botón
    private fun setupOnClickListeners(){
        val txNombres = findViewById<TextInputEditText>(R.id.input_nombre) //ID DE LOS INPUT
        val txApellidos = findViewById<TextInputEditText>(R.id.input_apellidos)
        val txEmail = findViewById<TextInputEditText>(R.id.input_email)
        val txTelefono = findViewById<TextInputEditText>(R.id.input_phone)
        val txContrasena = findViewById<TextInputEditText>(R.id.input_contraseña)
        val txConfirmContrasena = findViewById<TextInputEditText>(R.id.input_confirm_contraseña)
        val chBox = findViewById<CheckBox>(R.id.checkBox)
        val bRegistro= findViewById<Button>(R.id.btnRegistro)

        bRegistro.setOnClickListener {
            val nombres = txNombres.text.toString().trim()
            val apellidos = txApellidos.text.toString().trim()
            val email = txEmail.text.toString().trim()
            val telefono = txTelefono.text.toString().trim()
            val contrasena = txContrasena.text.toString().trim()
            val cfcontrasena = txConfirmContrasena.text.toString().trim()
            val checkBox = chBox.isChecked

            if (validarCampos(nombres, apellidos, email, telefono, contrasena, cfcontrasena, checkBox)){
                guardarDatos(nombres, apellidos, email, telefono, contrasena, cfcontrasena, checkBox)

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    //Funcion validación de los campos
    private fun validarCampos(nombres: String, apellidos: String, email: String , telefono: String, contrasena: String, cfcontrasena: String, checkBox: Boolean): Boolean{
        if (nombres.isEmpty()){
            Toast.makeText(this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if (apellidos.isEmpty()){
            Toast.makeText(this,"Por favor ingresa tu apellido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (email.isEmpty()){
            Toast.makeText(this,"Por favor ingresa tu email", Toast.LENGTH_SHORT).show()
            return false
        }
        if (telefono.isEmpty()){
            Toast.makeText(this,"Por favor ingresa tu telefono", Toast.LENGTH_SHORT).show()
            return false
        }
        if (contrasena.isEmpty()){
            Toast.makeText(this,"Debes crear una contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if (contrasena != cfcontrasena) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        // Nueva validación para el CheckBox
        if (!checkBox) {
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    //Función guardado datos inputs
    private fun guardarDatos(
        nombres: String,
        apellidos: String,
        email: String,
        telefono: String,
        contrasena: String,
        cfcontrasena: String,
        checkBox: Boolean
    ){
        val editor = sharedPreferences.edit()
        editor.putString("nombres", nombres)
        editor.putString("apellidos", apellidos)
        editor.putString("email", email)
        editor.putString("telefono", telefono)
        editor.putString("contraseña", contrasena)
        editor.putString("confirmar contraseña", cfcontrasena )
        editor.apply()
    }
}