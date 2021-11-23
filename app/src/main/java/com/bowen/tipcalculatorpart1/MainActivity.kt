package com.bowen.tipcalculatorpart1

import android.media.SubtitleData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import org.w3c.dom.Text

lateinit var tipSeekBar: SeekBar
lateinit var tenRadioButton: RadioButton
lateinit var fifteenRadioButton: RadioButton
lateinit var eighteenRadioButton: RadioButton
lateinit var twentyFiveRadioButton: RadioButton
lateinit var tipAmount : TextView
    var subTotal = 0
    var numOfGuests = 0
    var tipPercent = 0
    var finalTotal = 0
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subTotal = 100
        tipSeekBar= findViewById(R.id.tip_seekBar)
        tenRadioButton  = findViewById(R.id.ten_radioButton)
        fifteenRadioButton = findViewById(R.id.fifteen_radioButton)
        eighteenRadioButton = findViewById(R.id.eighteen_radioButton)
        twentyFiveRadioButton = findViewById(R.id.twentyFive_radioButton)
        tipAmount = findViewById(R.id.tip_amount_textView)
        setUpRadioButtons()
        setUpTipSeekBar()
        setUpNumOfGuestsSpinner()
    }
        fun setUpRadioButtons() {
            val radioButtons = listOf(tenRadioButton, fifteenRadioButton, eighteenRadioButton, twentyFiveRadioButton)
            for(eachRadioButton in radioButtons) {
                eachRadioButton.setOnClickListener{ view->
                    when(view.id){
                        R.id.ten_radioButton -> tipPercent = 10
                        R.id.fifteen_radioButton -> tipPercent = 15
                        R.id.eighteen_radioButton -> tipPercent = 18
                        R.id.twentyFive_radioButton -> tipPercent = 25
                    }
                    tipSeekBar.progress = tipPercent
                    setTipAndTotalTextViews()
                }
            }
        }

        fun setUpTipSeekBar() {
            tipSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                var progressValue = 0
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    progressValue = progress
                    if(progressValue != 10 && progressValue != 15 && progressValue != 18 && progressValue !=25){
                        tenRadioButton.isChecked = false
                        fifteenRadioButton.isChecked = false
                        eighteenRadioButton.isChecked = false
                        twentyFiveRadioButton.isChecked = false
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
                            setRadioButtonAsChecked(tenRadioButton)
                            setTipAndTotalTextViews()
                        }
                        15 -> {
                            tipPercent = 15
                            setRadioButtonAsChecked(fifteenRadioButton)
                            setTipAndTotalTextViews()
                        }
                        18 -> {
                            tipPercent = 18
                            setRadioButtonAsChecked(eighteenRadioButton)
                            setTipAndTotalTextViews()
                        }
                        25 -> {
                            tipPercent = 25
                            setRadioButtonAsChecked(twentyFiveRadioButton)
                            setTipAndTotalTextViews()
                        }
                    }
                    tipPercent = progressValue
                    setTipAndTotalTextViews()
                }
            })
        }

        fun setUpNumOfGuestsSpinner(){
            val numGuestsSpinner: Spinner = findViewById(R.id.guests_spinner)
            val numGuestsArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.numGuestsArray,
                android.R.layout.simple_spinner_item)
            numGuestsArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item)
            numGuestsSpinner.adapter = numGuestsArrayAdapter
            numGuestsSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                var selectedNumGuests = ""
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long)
                {
                    selectedNumGuests = parent.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        fun setRadioButtonAsChecked(selectedRadioButton : RadioButton) {
            val tipRadioGroup : RadioGroup = findViewById(R.id.tip_radio_group)
            tipRadioGroup.clearCheck()
            selectedRadioButton.isChecked = true
        }

        fun setTipAndTotalTextViews() {
            val totalWithTip : TextView = findViewById(R.id.total_with_tip_textView)
            val tipAmount : TextView = findViewById(R.id.tip_amount_textView)
            tipAmount.text = "$tipPercent%"
            val tipDollarAmount:Int = ((tipPercent.toDouble()).div(100).times(subTotal)).toInt()
            val finalTotal = subTotal.plus(tipDollarAmount)
            totalWithTip.text = "$$finalTotal"
        }
}