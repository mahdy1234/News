package com.engmahdy.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.engmahdy.news.destinations.fragments.settingsfragment.SettingsFragment
import com.engmahdy.utils.Constants.OPTION_DARK_MODE_KEY
import com.engmahdy.utils.Constants.OPTION_LANGUAGE_KEY
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManger(private val context: Context) {


    //Storing Settings Data

    //Dark Mode
    fun getDarkModeEnabled() =
        context.dataStore.data.map { it[OPTION_DARK_MODE_KEY] ?: true }

    suspend fun setDarkModeEnabled(enabled: Boolean) =
        context.dataStore.edit {
            it[OPTION_DARK_MODE_KEY] = enabled
        }


    //Language
    fun getLanguage() =
        context.dataStore.data.map {
            it[OPTION_LANGUAGE_KEY] ?: "EN"
        }

    suspend fun setLanguage(language: String) =
        context.dataStore.edit {
            it[OPTION_LANGUAGE_KEY] = language
        }

}

