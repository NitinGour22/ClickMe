package com.piexxi.clickme

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.piexxi.clickme.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        observeData()
        listeners()

        return binding.root
    }

    private fun observeData() {
        viewModel.currentTime.observe(
            viewLifecycleOwner,
            Observer { time -> binding.tvTimer.text = DateUtils.formatElapsedTime(time) })

        viewModel.currentButton.observe(
            viewLifecycleOwner,
            Observer { button -> moveButton(button) })

        viewModel.score.observe(
            viewLifecycleOwner,
            Observer { score -> binding.tvScore.text = score.toString() })

        viewModel.scoreColor.observe(
            viewLifecycleOwner,
            Observer { color -> changeScoreColor(color) })

        viewModel.gameFinish.observe(
            viewLifecycleOwner,
            Observer { isFinished -> if (isFinished) gameOver() })
    }

    private fun listeners() {
        binding.btOne.setOnClickListener { viewModel.gainPoint() }
        binding.btTwo.setOnClickListener { viewModel.gainPoint() }
        binding.btThree.setOnClickListener { viewModel.gainPoint() }
        binding.btFour.setOnClickListener { viewModel.gainPoint() }

        binding.loGame.setOnClickListener { viewModel.loosPoint() }
    }

    // on;y show given button
    private fun moveButton(button: Int) {
        binding.btOne.visibility = View.INVISIBLE
        binding.btTwo.visibility = View.INVISIBLE
        binding.btThree.visibility = View.INVISIBLE
        binding.btFour.visibility = View.INVISIBLE

        when (button) {
            1 -> binding.btOne.visibility = View.VISIBLE
            2 -> binding.btTwo.visibility = View.VISIBLE
            3 -> binding.btThree.visibility = View.VISIBLE
            else -> binding.btFour.visibility = View.VISIBLE
        }
    }

    private fun changeScoreColor(color: String) {
        if (color == "purple") {
            binding.tvScore.setTextColor(
                ContextCompat.getColor(
                    requireContext().applicationContext,
                    R.color.purple_700
                )
            )
        } else {
            binding.tvScore.setTextColor(
                ContextCompat.getColor(
                    requireContext().applicationContext,
                    R.color.my_red
                )
            )
        }

    }

    private fun gameOver() {

        findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score.value!!))

    }
}