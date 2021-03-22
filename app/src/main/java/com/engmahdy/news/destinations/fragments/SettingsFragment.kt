package com.engmahdy.news.destinations.fragments

import android.app.UiModeManager
import android.content.Context
import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.engmahdy.news.databinding.FragmentSettingsBinding
import com.engmahdy.utils.getDarkModeEnabled
import com.engmahdy.utils.setDarkModeEnabled
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var darkModeSwitch: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        setupViews()

    }


    private fun initViews() {
        darkModeSwitch = binding.darkModeSwitch

    }

    private fun setupViews() {
        darkModeSwitch.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val nightModeEnabled = darkModeSwitch.isChecked
                setDarkModeEnabled(nightModeEnabled)
                withContext(Dispatchers.Main) { setNightMode(nightModeEnabled) }

            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            requireContext().getDarkModeEnabled().collect {
                darkModeSwitch.isChecked = it
            }
        }

    }

    private fun setNightMode(enabled: Boolean) {
        val uiManager = requireContext().getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        if (VERSION.SDK_INT <= 22) {
            uiManager.enableCarMode(0)
            return
        }
        val mode =
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}