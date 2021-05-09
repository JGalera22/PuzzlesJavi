package com.naturesecurityvanguard.puzzles_Javi.ui.Admin.Main_Admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.naturesecurityvanguard.puzzles_Javi.MainActivity
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.ui.Admin.PantallaInicioAdmin.FilterActivityAdmin
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.FilterActivity
import com.naturesecurityvanguard.puzzles_Javi.ui.login.LoginActivity

class AdminActivity : AppCompatActivity() {
    lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

    val navView: BottomNavigationView = findViewById(R.id.nav_view_admin)

    val navController = findNavController(R.id.nav_host_fragment_admin)
    val appBarConfiguration = AppBarConfiguration(
        setOf(
            R.id.navigation_puzzle_admin, R.id.navigation_usuarios_admin
        )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

    val sharedPref = getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)
    token = sharedPref.getString("token", "")!!
}

override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.main_option_menu_admin, menu)

    val actionLogout = menu.findItem(R.id.action_logout)

    if (token.isEmpty()) {
        actionLogout.icon = getDrawable(R.drawable.ic_logout_24px)
    }

    return true
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle item selection
    return when (item.itemId) {
        R.id.action_admin -> {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
            true
        }
        R.id.action_filter_admin -> {
            val intent = Intent(this, FilterActivityAdmin::class.java)
            this.startActivity(intent)
            true
        }

        R.id.action_logout -> {
            logout()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}



private fun logout(){
    if (token.isEmpty()) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    } else {
        val sharedPref = getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            remove("token")
            commit()
        }
        finish()
    }
}



}