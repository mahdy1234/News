package com.engmahdy.news.destinations.fragments.settingsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SettingsViewModelFactory(val settingsRepository: SettingsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java))
            return SettingsViewModel(settingsRepository) as T
        throw IllegalArgumentException("UnknownViewModel")
    }

}