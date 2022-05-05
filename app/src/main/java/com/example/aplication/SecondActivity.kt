package com.example.aplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.aplication.databinding.ActivitySecondBinding

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val frame = findViewById<FrameLayout>(R.id.sheet)
        val bottomSheetBehavior: BottomSheetBehavior<*> =
           BottomSheetBehavior.from<View>(frame)

      bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetBehavior.peekHeight = 150
        bottomSheetBehavior.isHideable = false

        binding.govno.setOnClickListener {
            val Params = CoordinatorLayout.LayoutParams(1750, 300)
            var SHIT = ForCustomView(this)
            binding.root.addView(SHIT, Params)
            SHIT.setOnTouchListener(getDragNDrop())
        }
    }

    data class Point(var x: Float, var y: Float)
    var touchPoint = Point(0f, 0f)
    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    private fun getDragNDrop() = View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    touchPoint.x = event.x
                    touchPoint.y = event.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                            view.x += event.x - touchPoint.x
                            view.y += event.y - touchPoint.y
                    false
                }
                else -> {
                    true
                }
            }
        }
    }




