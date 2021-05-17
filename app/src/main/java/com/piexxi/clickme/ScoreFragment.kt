package com.piexxi.clickme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.piexxi.clickme.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private  lateinit var binding : FragmentScoreBinding
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_score,container,false)
        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ScoreViewModel::class.java)

        bindDataToXml()
        listeners()
        return binding.root
    }

    private fun bindDataToXml() {
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun listeners() {
        binding.btGamble.setOnClickListener{viewModel.gamble()}
        binding.btPlay.setOnClickListener{findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())}
        binding.btTitle.setOnClickListener{findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToTitleFragment())}
    }
}