package com.engmahdy.news.destinations.fragments.settingsfragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {
    private val darkMode by lazy {
        MutableLiveData<Boolean>().apply {
            viewModelScope.launch {
                settingsRepository.getDarkModeEnabled().collect {
                    value = it
                }
            }
        }
    }

    fun setDarkModeEnabled(enabled: Boolean) {
        settingsRepository.setDarkModeEnabled(enabled)
        darkMode.value = enabled
    }

    fun getDarkModeEnabled() = darkMode


}