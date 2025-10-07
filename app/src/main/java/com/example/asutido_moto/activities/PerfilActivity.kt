package com.example.asutido_moto.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asutido_moto.R
import com.google.android.material.textfield.TextInputEditText

class PerfilActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        sharedPreferences = getSharedPreferences("RegistroUsuario", MODE_PRIVATE)

        // Referencias a los campos
        val inputNombre = findViewById<TextInputEditText>(R.id.input_nombrePerfil)
        val inputApellidos = findViewById<TextInputEditText>(R.id.input_apellidosPerfil)
        val inputEmail = findViewById<TextInputEditText>(R.id.input_emailPerfil)
        val inputTelefono = findViewById<TextInputEditText>(R.id.input_phonePerfil)
        val btnActualizar = findViewById<Button>(R.id.boton_actualizar)
        val btnVolver = findViewById<Button>(R.id.boton_volver)

        // Mostrar los datos guardados
        inputNombre.setText(sharedPreferences.getString("nombres", ""))
        inputApellidos.setText(sharedPreferences.getString("apellidos", ""))
        inputEmail.setText(sharedPreferences.getString("email", ""))
        inputTelefono.setText(sharedPreferences.getString("telefono", ""))

        // Botón actualizar datos
        btnActualizar.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putString("nombres", inputNombre.text.toString())
            editor.putString("apellidos", inputApellidos.text.toString())
            editor.putString("email", inputEmail.text.toString())
            editor.putString("telefono", inputTelefono.text.toString())
            editor.apply()

            Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
        }

        // Botón volver al login
        btnVolver.setOnClickListener {
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnActualizarContrasena = findViewById<Button>(R.id.boton_actualizarcontrasena)
        btnActualizarContrasena.setOnClickListener {
            mostrarDialogoActualizarContrasena()
        }
    }

    private fun mostrarDialogoActualizarContrasena() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Actualiza tu contraseña en ConektaFriends")

        // Layout para el campo de texto
        val inputLayout = com.google.android.material.textfield.TextInputLayout(this)
        inputLayout.setPadding(50, 20, 50, 0)

        val input = com.google.android.material.textfield.TextInputEditText(this)
        input.hint = "Nueva contraseña"
        input.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        inputLayout.addView(input)

        builder.setView(inputLayout)

        builder.setPositiveButton("Guardar") { dialog, _ ->
            val nuevaContrasena = input.text.toString().trim()

            if (nuevaContrasena.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("RegistroUsuario", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("contraseña", nuevaContrasena)
                editor.apply()

                Toast.makeText(this, "Contraseña actualizada correctamente", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.cancel()
        }

        val dialog = builder.create()
        dialog.show()
    }
}
