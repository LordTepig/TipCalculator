package com.bowen.tipcalculatorpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.bowen.tipcalculatorpart1.databinding.FragmentSubtotalBinding
import com.bowen.tipcalculatorpart1.databinding.FragmentTipBinding


class SubtotalFragment : Fragment() {

    private var _binding: FragmentSubtotalBinding? = null
    private val binding get() = _binding!!

    private var subtotal: Int = 0
    private lateinit var numButtonList : List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSubtotalBinding.inflate(inflater, container, false)
        val rootView = binding.root
        numButtonList = listOf(binding.oneButton, binding.twoButton, binding.threeButton, binding.fourButton, binding.fiveButton, binding.sixButton, binding.sevenButton, binding.eightButton, binding.nineButton, binding.zeroButton)
        for(numButton in numButtonList){
            numButton.setOnClickListener { view ->
            when(view){
                binding.oneButton -> subtotal= (subtotal*10) +1
                binding.twoButton -> subtotal= (subtotal*10) + 2
                binding.threeButton -> subtotal = (subtotal*10) +3
                binding.fourButton -> subtotal= (subtotal*10) + 4
                binding.fiveButton -> subtotal = (subtotal*10) + 5
                binding.sixButton -> subtotal = (subtotal*10) + 6
                binding.sevenButton -> subtotal = (subtotal*10) + 7
                binding.eightButton -> subtotal = (subtotal*10) + 8
                binding.nineButton -> subtotal = (subtotal*10) + 9
                binding.zeroButton -> subtotal = (subtotal*10) +0
                }
                binding.moneyAmountTextView.text="$${subtotal.toString()}.00"
            }
        }
        binding.backspaceButton.setOnClickListener {
            subtotal = subtotal/10
            binding.moneyAmountTextView.text = "$${subtotal.toString()}.00"
        }
        binding.doneButton.setOnClickListener{
            val action =SubtotalFragmentDirections.actionSubtotalFragmentToTipFragment(subtotal)
            if(subtotal !=0) {
                rootView.findNavController().navigate(action)
            }
        }
        return rootView
    }


}