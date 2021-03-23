package com.engmahdy.news.destinations.fragments.settingsfragment

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.engmahdy.utils.DataStoreManger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsRepository(
    private val dataStoreManger: DataStoreManger,
    private val context: Context
) {

    fun setDarkModeEnabled(enabled: Boolean) {
        val coroutine =
            CoroutineScope(Dispatchers.Main).launch {
                dataStoreManger.setDarkModeEnabled(enabled)
                setNightMode(enabled)
            }
    }

    fun getDarkModeEnabled() = dataStoreManger.getDarkModeEnabled()

    private fun setNightMode(enabled: Boolean) {
        val uiManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        if (Build.VERSION.SDK_INT <= 22) {
            uiManager.enableCarMode(0)
            return
        }
        val mode =
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)

    }
}