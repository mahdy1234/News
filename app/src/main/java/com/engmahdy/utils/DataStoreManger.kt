package com.engmahdy.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.engmahdy.news.destinations.fragments.SettingsFragment
import com.engmahdy.utils.Constants.Companion.OPTION_DARK_MODE_KEY
import com.engmahdy.utils.Constants.Companion.OPTION_LANGUAGE_KEY
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


//Storing Settings Data

//Dark Mode
fun Context.getDarkModeEnabled() =
    dataStore.data.map { it[OPTION_DARK_MODE_KEY] ?: true }

suspend fun SettingsFragment.setDarkModeEnabled(enabled: Boolean) =
    requireContext().dataStore.edit {
        it[OPTION_DARK_MODE_KEY] = enabled
    }


//Language
fun Context.getLanguage() =
    dataStore.data.map {
        it[OPTION_LANGUAGE_KEY] ?: "EN"
    }

suspend fun SettingsFragment.setLanguage(language: String) =
    requireContext().dataStore.edit {
        it[OPTION_LANGUAGE_KEY] = language
    }

