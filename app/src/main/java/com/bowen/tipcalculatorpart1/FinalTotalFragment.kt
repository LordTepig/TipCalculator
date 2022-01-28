package com.bowen.tipcalculatorpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bowen.tipcalculatorpart1.databinding.FragmentFinalTotalBinding
import com.bowen.tipcalculatorpart1.databinding.FragmentSubtotalBinding


class FinalTotalFragment : Fragment() {

    private var _binding: FragmentFinalTotalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFinalTotalBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val args = FinalTotalFragmentArgs.fromBundle(requireArguments())
        val total = args.totalArg
        val numGuests = args.guestArg
        binding.subtotalWithTipTextView.text = "${binding.subtotalWithTipTextView.text} $${total}0"
        binding.amountForEachPersonTextView.text = "${binding.amountForEachPersonTextView.text} $${total/numGuests}0"
        return rootView
    }

}