package com.android.deepdweller.maprouteapp_nojetpack.presentation.map

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.android.deepdweller.maprouteapp_nojetpack.R
import com.android.deepdweller.maprouteapp_nojetpack.databinding.FragmentApiRegistrationBinding
import com.android.deepdweller.maprouteapp_nojetpack.databinding.FragmentMapBinding
import com.android.deepdweller.maprouteapp_nojetpack.presentation.base.BaseFragment
import com.android.deepdweller.maprouteapp_nojetpack.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

class ApiRegistrationFragment : BaseFragment(R.layout.fragment_api_registration) {
    private val binding by viewBinding(FragmentApiRegistrationBinding::bind)
    private val viewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAPIInputBehaviour()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initAPIInputBehaviour() {
        binding.GO.setOnClickListener{
            viewModel.setApiKey(binding.APITokenInput.text.toString())
            if (viewModel.getApiKey() == null)
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.root_host, MapFragment())
                    addToBackStack(null)
                    commit()
                }



        }
        binding.APITokenInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                val userInput: String = binding.APITokenInput.text.toString()
                viewModel.setApiKey(userInput)
                true
            } else {
                false
            }
        }
    }
}