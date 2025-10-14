package com.example.asutido_moto.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.asutido_moto.R
import com.google.android.material.textfield.TextInputEditText

class MiPerfilFragment : Fragment() {

    private lateinit var txNombres: TextInputEditText
    private lateinit var txApellidos: TextInputEditText
    private lateinit var txEmail: TextInputEditText
    private lateinit var txTelefono: TextInputEditText
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREF_NAMES = "MisDatos"
        private const val KEY_NOMBRES = "nombres"
        private const val KEY_APELLIDOS = "apellidos"
        private const val KEY_EMAIL = "email"
        private const val KEY_TELEFONO = "telefono"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragment
        val view = inflater.inflate(R.layout.fragment_mi_perfil, container, false)

        // Inicializar vistas y preferencias
        initViews(view)
        setupSharedPreferences()
        loadUserData()

        return view
    }

    private fun initViews(view: View) {
        txNombres = view.findViewById(R.id.input_nombrePerfil)
        txApellidos = view.findViewById(R.id.input_apellidosPerfil)
        txEmail = view.findViewById(R.id.input_emailPerfil)
        txTelefono = view.findViewById(R.id.input_phonePerfil)
    }

    private fun setupSharedPreferences() {
        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAMES, Context.MODE_PRIVATE)
    }

    private fun loadUserData() {
        val nombres = sharedPreferences.getString(KEY_NOMBRES, "")
        val apellidos = sharedPreferences.getString(KEY_APELLIDOS, "")
        val email = sharedPreferences.getString(KEY_EMAIL, "")
        val telefono = sharedPreferences.getString(KEY_TELEFONO, "")

        txNombres.setText(nombres)
        txApellidos.setText(apellidos)
        txEmail.setText(email)
        txTelefono.setText(telefono)
    }
}
