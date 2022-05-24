package me.tankery.app.circularseekbar

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import me.tankery.app.circularseekbar.R.id
import me.tankery.app.circularseekbar.databinding.ActivityMainBinding
import me.tankery.lib.circularseekbar.CircularSeekBar

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val seekBar = findViewById<CircularSeekBar>(id.seek_bar) //Or binding.seekBar
        val textEvent = binding.textEvent
        val textProgress = binding.textProgress

        seekBar.setOnSeekBarChangeListener(object :
            CircularSeekBar.OnCircularSeekBarChangeListener {
            override fun onProgressChanged(
                circularSeekBar: CircularSeekBar?,
                progress: Float,
                fromUser: Boolean
            ) {
                val message =
                    String.format("Progress changed to %.2f, fromUser %s", progress, fromUser)
                Log.d("Main", message)
                textProgress.text = message
            }

            override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {
                Log.d("Main", "onStopTrackingTouch")
                textEvent.text = ""
            }

            @SuppressLint("SetTextI18n")
            override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {
                Log.d("Main", "onStartTrackingTouch")
                textEvent.text = "touched | "
            }
        })

        composeDataBindingUi()
    }

    private fun composeDataBindingUi() {
        @Suppress("DEPRECATION") //Can do version check or use ResourceCompat
        val pointerFillColor = resources.getColor(android.R.color.white)

        val pointerInnerBorderWidthDp = 10f
        val circleStrokeWidthDp = 8f
        val pointerStrokeWidthDp = 24f
        val pointerHaloWidthPixels =
            resources.getDimensionPixelSize(R.dimen.seekbar_holo_width).toFloat()

        binding.uiBinding = UiBinding(
            progress = 80,
            pointerFillColor = pointerFillColor,
            pointerInnerBorderWidthPixels = dpToPixels(pointerInnerBorderWidthDp),
            isNegativeEnabled = true,
            circleStyle = Paint.Cap.SQUARE,
            circleStrokeWidthPixels = dpToPixels(circleStrokeWidthDp),
            pointerStrokeWidthPixels = dpToPixels(pointerStrokeWidthDp),
            pointerHaloWidthPixels = pointerHaloWidthPixels
        )

        binding.executePendingBindings()
    }

    private fun dpToPixels(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
    }
}