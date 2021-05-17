package com.piexxi.clickme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.piexxi.clickme.databinding.FragmentTitleeBinding

class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_titlee, container, false)
        listeners()
        return binding.root
    }

    private fun listeners() {
       binding.btPlay.setOnClickListener{findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())}
    }
}