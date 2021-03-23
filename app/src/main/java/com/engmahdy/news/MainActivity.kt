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
import com.engmahdy.news.destinations.fragments.settingsfragment.SettingsRepository
import com.engmahdy.utils.DataStoreManger
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var settingsRepository: SettingsRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRepository()
        initViews()
        setDarkMode()
        bottomNav.setupWithNavController(findNavController(R.id.nav_host_fragment))

    }

    private fun initRepository() {
        settingsRepository = SettingsRepository(DataStoreManger(this), this)
    }

    private fun setDarkMode() {
        CoroutineScope(Dispatchers.Main).launch {
            settingsRepository.getDarkModeEnabled().collect {
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