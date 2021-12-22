package com.bowen.tipcalculatorpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bowen.tipcalculatorpart1.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    var subTotal = 0
    var tipPercent = 0

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root
        subTotal = 100
        setUpRadioButtons()
        setUpTipSeekBar()
        setUpNumOfGuestsSpinner()
        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setUpRadioButtons() {
        val radioButtons = listOf(binding.tenRadioButton, binding.fifteenRadioButton, binding.eighteenRadioButton, binding.twentyFiveRadioButton)
        for(eachRadioButton in radioButtons) {
            eachRadioButton.setOnClickListener{ view->
                when(view.id){
                    R.id.ten_radioButton -> tipPercent = 10
                    R.id.fifteen_radioButton -> tipPercent = 15
                    R.id.eighteen_radioButton -> tipPercent = 18
                    R.id.twentyFive_radioButton -> tipPercent = 25
                }
                binding.tipSeekBar.progress = tipPercent
                setTipAndTotalTextViews()
            }
        }
    }

    fun setUpTipSeekBar() {
        binding.tipSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            var progressValue = 0
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                progressValue = progress
                if(progressValue != 10 && progressValue != 15 && progressValue != 18 && progressValue !=25){
                    binding.tenRadioButton.isChecked = false
                    binding.fifteenRadioButton.isChecked = false
                    binding.eighteenRadioButton.isChecked = false
                    binding.twentyFiveRadioButton.isChecked = false
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    progressValue = seekBar.progress
                }
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tipPercent = progressValue
                when(progressValue){
                    10 -> {
                        tipPercent = 10
                        setRadioButtonAsChecked(binding.tenRadioButton)
                        setTipAndTotalTextViews()
                    }
                    15 -> {
                        tipPercent = 15
                        setRadioButtonAsChecked(binding.fifteenRadioButton)
                        setTipAndTotalTextViews()
                    }
                    18 -> {
                        tipPercent = 18
                        setRadioButtonAsChecked(binding.eighteenRadioButton)
                        setTipAndTotalTextViews()
                    }
                    25 -> {
                        tipPercent = 25
                        setRadioButtonAsChecked(binding.twentyFiveRadioButton)
                        setTipAndTotalTextViews()
                    }
                }
                tipPercent = progressValue
                setTipAndTotalTextViews()
            }
        })
    }

    fun setUpNumOfGuestsSpinner(){
        val numGuestsArrayAdapter = ArrayAdapter.createFromResource(requireActivity(),
            R.array.numGuestsArray,
            android.R.layout.simple_spinner_item)
        numGuestsArrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        binding.guestsSpinner.adapter = numGuestsArrayAdapter
        binding.guestsSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            var selectedNumGuests = ""
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long)
            {
                selectedNumGuests = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun setRadioButtonAsChecked(selectedRadioButton : RadioButton) {
        binding.tipRadioGroup.clearCheck()
        selectedRadioButton.isChecked = true
    }

    fun setTipAndTotalTextViews() {
        binding.tipAmountTextView.text = " $tipPercent%"
        val tipDollarAmount:Int = ((tipPercent.toDouble()).div(100).times(subTotal)).toInt()
        val finalTotal = subTotal.plus(tipDollarAmount)
        binding.totalWithTipTextView.text = " $$finalTotal.0"
    }
}