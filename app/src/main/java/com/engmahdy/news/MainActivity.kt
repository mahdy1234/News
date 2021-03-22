package com.engmahdy.news

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.engmahdy.news.databinding.ActivityMainBinding
import com.engmahdy.utils.getDarkModeEnabled
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setDarkMode()
        bottomNav.setupWithNavController(findNavController(R.id.nav_host_fragment))

    }

    private fun setDarkMode() {
        CoroutineScope(Dispatchers.Main).launch {
            getDarkModeEnabled().collect {
                setNightMode(it)
            }
        }

    }

    private fun setNightMode(enabled: Boolean) {
        val uiManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        if (Build.VERSION.SDK_INT <= 22) {
            uiManager.enableCarMode(0)
            return
        }
        val mode =
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)

    }

    private fun initViews() {
        bottomNav = binding.bottomNav
    }
}