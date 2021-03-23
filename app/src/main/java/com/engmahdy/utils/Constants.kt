package com.engmahdy.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    //Settings Constants
    val OPTION_DARK_MODE_KEY = booleanPreferencesKey("DARK_MODE")
    val OPTION_LANGUAGE_KEY = stringPreferencesKey("LANG")

}