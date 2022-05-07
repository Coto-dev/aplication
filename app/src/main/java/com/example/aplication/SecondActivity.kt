package com.example.aplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.aplication.databinding.ActivitySecondBinding
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    private var listOfBlocks: MutableList<View> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val linearLayout = findViewById<LinearLayout>(R.id.container)

        val frame = findViewById<FrameLayout>(R.id.sheet)
        val bottomSheetBehavior: BottomSheetBehavior<*> =
            BottomSheetBehavior.from<View>(frame)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetBehavior.peekHeight = 135
        bottomSheetBehavior.isHideable = false

        binding.forInitialization.setOnClickListener {
            listOfBlocks.add(addViewToScreen(ForCustomView(this)))
        }
        binding.forCycleFor.setOnClickListener {
            listOfBlocks.add(addViewToScreen(ForCustomView(this)))
        }
        binding.forCycleWhile.setOnClickListener {
            listOfBlocks.add(addViewToScreen(ForCustomView(this)))
        }
        binding.forOperatorIf.setOnClickListener {
            listOfBlocks.add(addViewToScreen(ForCustomView(this)))
        }
        binding.forOperatorIfElse.setOnClickListener {
            listOfBlocks.add(addViewToScreen(ForCustomView(this)))
        }
    }

    private fun addViewToScreen(view: View): View {

        var maxY = -1f
        var lowerBlock: View? = null
        for(block in listOfBlocks) {
            if(block.y > maxY) {
                maxY = block.y
                lowerBlock = block
            }
        }
        var params = LinearLayout.LayoutParams(1750, 200)
        binding.container.addView(view, params)
        view.setOnTouchListener(getTouchListener())
        if(lowerBlock == null) {
            view.y = 0f
            Log.i("hello", view.y.toString())
        }
        else {
            view.y = lowerBlock.y + lowerBlock.height  + 1
            view.x = lowerBlock.x
            Log.i("hello","view.y = ${view.y}, lowerBlock.y = ${lowerBlock.y}, lowerBlock.height = ${lowerBlock.height}")
        }

        return view
    }


    data class Point(var x: Float, var y: Float)
    var touchPoint = Point(0f, 0f)
    var startPoint = Point(0f, 0f)
    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    private fun getTouchListener() = View.OnTouchListener { view, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startPoint = Point(view.x, view.y)
                touchPoint.x = event.x
                touchPoint.y = event.y
                true
            }
            MotionEvent.ACTION_MOVE -> {
                //moveChain(chain, event.x, event.y)
                val newPoint =
                    Point(view.x + event.x - touchPoint.x, view.y + event.y - touchPoint.y)
                if (0 <= newPoint.x) {
                    view.x += event.x - touchPoint.x
                }
                if (0 <= newPoint.y) {
                    view.y += event.y - touchPoint.y
                }
                false
            }
            MotionEvent.ACTION_UP -> {
                attachView(view, startPoint)
                true
            }
            else -> {
                true
            }
        }
    }
    private fun isPointInBlock(view: View, point: Point) =
        view.x <= point.x && point.x <= view.x + view.width &&
        view.y <= point.y && point.y <= view.y + view.height

    private fun attachView(view: View, startPoint: Point) {
        val newPoint = Point(startPoint.x, startPoint.y)
        var count = 0
        for(block in listOfBlocks) {
            if(block != view) {
                var point = Point(view.x, view.y)
                if (isPointInBlock(block, point)) {
                    count++
                    newPoint.x = block.x
                    newPoint.y = block.y + block.height + 1
                    Log.i("hello", "up")
                }
                point = Point(view.x , view.y + block.height)
                if (isPointInBlock(block, point)) {
                    count++
                    newPoint.x = block.x
                    newPoint.y = block.y
                    Log.i("hello", "down")

                }
            }
        }
        if(count > 2) {
            Log.e("log", "TI JIDKO OBOSRALSYA!!!")
        }
        count = 1
        for(block in listOfBlocks) {
            if(block != view) {
               if(block.y >= newPoint.y) {
                   block.y = block.y + block.height + 1
                   count++
               }
            }
        }
        view.x = newPoint.x
        view.y = newPoint.y
    }
}


class CustomRecyclerAdapter(private val names: List<String>) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}

