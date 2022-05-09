package com.example.aplication

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.aplication.databinding.ActivitySecondBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior



class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    data class Block(var index: Int, var view: View)
    private var listOfBlocks: MutableList<Block> = mutableListOf()
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
        val view = View(this)
        //view.background = getDrawable(R.drawable.button)
        //var params = LinearLayout.LayoutParams(1750, 250)
        binding.container.addView(view, listOfBlocks.size)
        binding.container.setOnDragListener(choiceDragListener())
    }

    private fun addViewToScreen(view: View): Block {
//        // val lastTouchDownXY = FloatArray(2)
//        var maxY = -1f
//        var lowerBlock: View? = null
//        for (block in listOfBlocks) {
//            if (block.y > maxY) {
////                maxY = block.y
//                lowerBlock = block
//            }
//        }
 //       val params = LinearLayout.LayoutParams(200, 250)
        //var view = ForCustomView(this)
        (view as ForCustomView).SetText(listOfBlocks.size.toString())
        binding.container.addView(view)
        view.setOnTouchListener(choiceTouchListener())
        view.setOnDragListener(choiceDragListener())
        val newBlock = Block(listOfBlocks.size, view)
        return newBlock
//        private fun getTouchListener() = View.OnTouchListener { view, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    startPoint = Point(view.x, view.y)
//                    touchPoint.x = event.x
//                    touchPoint.y = event.y
//                    true
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    //moveChain(chain, event.x, event.y)
//                    val newPoint =
//                        Point(view.x + event.x - touchPoint.x, view.y + event.y - touchPoint.y)
//                    if (0 <= newPoint.x) {
//                        view.x += event.x - touchPoint.x
//                    }
//                    MotionEvent.ACTION_MOVE -> {
//                        view.x += event.x -touchPoint.x
//                        view.y += event.y -touchPoint.y
//                        false
//                        if (0 <= newPoint.y) {
//                            view.y += event.y - touchPoint.y
//                        }
//                        else -> {
//                        true
//                        false
//                    }
//                        MotionEvent.ACTION_UP -> {
//                        attachView(view, startPoint)
//                        true
//                    }
//                        else -> {
//                        true
//                    }
//                    }
//                }
//        val touchListener = OnTouchListener { View: v, event -> // save the X,Y coordinates
//            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
//                lastTouchDownXY[0] = event.x
//                lastTouchDownXY[1] = event.y
//            }
//
//            // let the touch event pass on to whoever needs it
//            false
//        }

//        val longClickListener = OnLongClickListener { // retrieve the stored coordinates
//            val x = lastTouchDownXY[0]
//            val y = lastTouchDownXY[1]
//
//            // use the coordinates for whatever
//            Log.i("TAG", "onLongClick: x = $x, y = $y")
//
//            // we have consumed the touch event
//            true
//        }
//        mview.setOnTouchListener(touchListener);
//        mview.setOnLongClickListener(longClickListener);
    }

    private lateinit var draggingView: View

    @SuppressLint("ClickableViewAccessibility")
    fun choiceTouchListener() = OnTouchListener { view, _ ->
        val data = ClipData.newPlainText("", "")
        val shadowBuilder = View.DragShadowBuilder(view)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            @Suppress("DEPRECATION")
            view.startDrag(data, shadowBuilder, view, 0)
        } else {
            view.startDragAndDrop(data, shadowBuilder, view, 0)
        }
        draggingView = view as View
        true
    }

    private fun choiceDragListener() = View.OnDragListener { view, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                //draggingView.visibility = INVISIBLE
            }
            DragEvent.ACTION_DRAG_LOCATION -> {

            }
            DragEvent.ACTION_DROP -> {
                if(view != binding.container) {
                    var count1 = 0
                    for(block in listOfBlocks) {
                        if(view == block.view){
                            break
                        }
                        count1++
                    }
                    var count2 = 0
                    for(block in listOfBlocks) {
                        if(draggingView == block.view) {
                            swapTwoBlocks(count1, count2, view, draggingView)
                            val buff1 = block.index
                            block.index = listOfBlocks[count1].index
                            listOfBlocks[count1].index = buff1
                            val buff2 = block.view
                            block.view = listOfBlocks[count1].view
                            listOfBlocks[count1].view = buff2

                            break
                        }
                        count2++
                    }
                }
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                //draggingView.visibility = VISIBLE
            }

        }
        true
    }

    data class Point(var x: Float, var y: Float)

 //   var touchPoint = Point(0f, 0f)
   // var startPoint = Point(0f, 0f)

//    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
//    private fun getTouchListener() = View.OnTouchListener { view, event ->
//        when (event.action) {
//            MotionEvent.ACTION_DOWN -> {
//                startPoint = Point(view.x, view.y)
//                touchPoint.x = event.x
//                touchPoint.y = event.y
//                true
//            }
//            MotionEvent.ACTION_MOVE -> {
//                //moveChain(chain, event.x, event.y)
//                val newPoint =
//                    Point(view.x + event.x - touchPoint.x, view.y + event.y - touchPoint.y)
//                if (0 <= newPoint.x) {
//                    view.x += event.x - touchPoint.x
//                }
//                if (0 <= newPoint.y) {
//                    view.y += event.y - touchPoint.y
//                }
//                false
//            }
//            MotionEvent.ACTION_UP -> {
//                attachView(view, startPoint)
//                //Detour()
//                true
//            }
//            else -> {
//                true
//            }
//        }
//    }

//    private fun isPointInBlock(view: View, point: Point) =
//        view.x <= point.x && point.x <= view.x + view.width &&
//                view.y <= point.y && point.y <= view.y + view.height
//
//    private fun attachView(view: View) {
//        val newPoint = Point(0f, 0f)
//        var count = 0
//        for (block in listOfBlocks) {
//            if (block != view) {
//                var point = Point(view.x, view.y)
//                if (isPointInBlock(block, point)) {
//                    count++
//                    newPoint.x = block.x
//                    newPoint.y = block.y + block.height + 2
//                    Log.i("hello", "up")
//                }
//                point = Point(view.x, view.y + block.height)
//                if (isPointInBlock(block, point)) {
//                    count++
//                    newPoint.x = block.x
//                    newPoint.y = block.y
//                    Log.i("hello", "down")
//
//                }
//            }
//        }
//        if (count > 2) {
//            Log.e("log", "pizdec")
//        }
//        count = 1
//        for (block in listOfBlocks) {
//            if (block != view) {
//                if (block.y >= newPoint.y) {
//                    block.y = block.y + block.height + 2
//                    count++
//                }
//            }
//        }
//        view.x = newPoint.x
//        view.y = newPoint.y
//    }

    private fun swapTwoBlocks(firstInd: Int, secondInd: Int, firstView: View, secondView: View) {
        if(firstInd > secondInd) { //Todo: добавить в параметры функции родительского контейнера, например, binding.container
            binding.container.removeViewAt(firstInd + 1)
            binding.container.removeViewAt(secondInd + 1)
            binding.container.addView(firstView, secondInd + 1)
            binding.container.addView(secondView, firstInd + 1)
        }
        else if(secondInd > firstInd){
            binding.container.removeViewAt(secondInd + 1)
            binding.container.removeViewAt(firstInd + 1)
            binding.container.addView(secondView, firstInd + 1)
            binding.container.addView(firstView, secondInd + 1)
        }


    }
}
