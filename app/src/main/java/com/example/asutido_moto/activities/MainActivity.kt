package com.example.asutido_moto.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.asutido_moto.R
import com.example.asutido_moto.fragments.HomeFragment
import com.example.asutido_moto.fragments.MiPerfilFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_NAME = "UserData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inicializarVistas()
        setupSharedPreferences()
        setupNavigationView()

        // Cargar el fragmento inicial (por ejemplo, el de perfil)
        if (savedInstanceState == null) {
            loadFragment(MiPerfilFragment())
            navigationView.setCheckedItem(R.id.nav_profile)
        }
    }

    private fun inicializarVistas() {
        drawerLayout = findViewById(R.id.main)
        navigationView = findViewById(R.id.navigation_view)

        val menuHamburguesa: ImageView = findViewById(R.id.menu_hamburguesa)
        menuHamburguesa.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private fun setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                loadFragment(HomeFragment())
            }
            R.id.nav_profile -> {
                loadFragment(MiPerfilFragment())
            }
            R.id.nav_logout -> {
                cerrarSesion()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun cerrarSesion() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // redirigir a login
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
