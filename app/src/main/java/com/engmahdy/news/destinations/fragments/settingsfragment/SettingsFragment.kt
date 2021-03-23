package com.engmahdy.news.destinations.fragments.settingsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.engmahdy.news.databinding.FragmentSettingsBinding
import com.engmahdy.utils.DataStoreManger


class SettingsFragment : Fragment() {

    private lateinit var viewModel: SettingsViewModel
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
        initViewModel()
        initViews()
        setupViews()

    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(
                this@SettingsFragment,
                SettingsViewModelFactory(
                    SettingsRepository(DataStoreManger(requireContext()), requireContext())
                )
            ).get(
                SettingsViewModel::class.java
            )
    }


    private fun initViews() {
        darkModeSwitch = binding.darkModeSwitch
    }

    private fun setupViews() {

        viewModel.getDarkModeEnabled()
            .observe(viewLifecycleOwner) { darkModeSwitch.isChecked = it ?: false }

        darkModeSwitch.setOnClickListener { viewModel.setDarkModeEnabled(darkModeSwitch.isChecked) }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}