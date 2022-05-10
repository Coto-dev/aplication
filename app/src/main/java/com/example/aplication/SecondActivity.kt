package com.example.aplication

import android.annotation.SuppressLint
import android.content.ClipData
import android.graphics.drawable.Drawable
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplication.databinding.ActivitySecondBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    data class Block(var view: View, var startInd: Int, var finishInd: Int)
    private var listOfBlocks: MutableList<Block> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //консоль и bottom sheet
        val frame = findViewById<FrameLayout>(R.id.sheet)
        val consol = findViewById<FrameLayout>(R.id.sheet2)

        val bottomSheetBehavior: BottomSheetBehavior<*> =
            BottomSheetBehavior.from<View>(frame)

        val bottomSheetConsol: BottomSheetBehavior<*> =
            BottomSheetBehavior.from<View>(consol)

        bottomSheetConsol.peekHeight = 0

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetBehavior.peekHeight = 135
        bottomSheetBehavior.isHideable = false


        // if (bottomSheetBehavior.peekHeight == 135){
        binding.floating.setOnClickListener{//компиляция
            bottomSheetBehavior.peekHeight = 0
            bottomSheetConsol.peekHeight = 135

            for(block in listOfBlocks){

                Log.d("gfdhj",(block.view as ForCustomView).GetText())
            }

        }
        //}

        //обработка нажатий на кнопки создания блоков
        binding.forArifmetic.setOnClickListener {
            listOfBlocks.add(addViewToScreen(ForCustomView(this)))
            createArithmetic
        }
        binding.forCycleFor.setOnClickListener {
           // addViewToScreen(ForCustomView(this), listOfBlocks.size, listOfBlocks.size + 1)
        }
        binding.forCycleWhile.setOnClickListener {
            addViewToScreen(ForCustomView(this), listOfBlocks.size, listOfBlocks.size + 1)
        }
        binding.forOperatorIf.setOnClickListener {
            addViewToScreen(ForCustomView(this), listOfBlocks.size, listOfBlocks.size + 1)
        }
        binding.forOperatorIfElse.setOnClickListener {
           // addViewToScreen(ForCustomView(this), listOfBlocks.size, listOfBlocks.size + 1)
        }
        binding.forInitialization.setOnClickListener {
            createInitialization()
            listOfBlocks.add(addViewToScreen2(For_inizalitation(this)))
        }

    }

//появление обычного блока
    private fun addViewToScreen(view: View): Block {
        (view as ForCustomView).SetText(listOfBlocks.size.toString())
        binding.container.addView(view)
        view.setOnTouchListener(choiceTouchListener())
        view.setOnDragListener(choiceDragListener())
        val newBlock = Block(view, listOfBlocks.size, listOfBlocks.size)
        return newBlock
    }

    private fun addViewToScreen2(view: View): Block {
        (view as For_inizalitation).SetText(listOfBlocks.size.toString())
        binding.container.addView(view)
        view.setOnTouchListener(choiceTouchListener())
        view.setOnDragListener(choiceDragListener())
        val newBlock = Block(view, listOfBlocks.size, listOfBlocks.size)
        return newBlock
    }


    //  пояление вложенного блока
    private fun addViewToScreen(buff: View, startInd: Int, finishInd: Int){
        for(i in startInd..finishInd) {
            Log.i("hello", "$i")
            var view = ForCustomView(this)
            (view as ForCustomView).SetText(listOfBlocks.size.toString())
            (view as ForCustomView).SetText("vlojen")
            binding.container.addView(view)
            view.setOnTouchListener(choiceTouchListener())
            view.setOnDragListener(choiceDragListener())
            val newBlock = Block(view, startInd, finishInd)
            listOfBlocks.add(newBlock)
        }
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
//                var second = listOfBlocks[0]
//                for(i in listOfBlocks) {
//                    if(i.view == draggingView) {
//                        second = i
//                    }
//                }
//                for(i in second.finishInd downTo second.startInd) {
//                    listOfBlocks[i].view.visibility = INVISIBLE
//                }
            }
            DragEvent.ACTION_DROP -> {
                if(view != binding.container) {
                    if (view != draggingView) {
                        var first = listOfBlocks[0]
                        var ind = 0
                        var second = listOfBlocks[0]
                        for (i in listOfBlocks) {
                            if (view != first.view) {
                                ind++
                            }
                            if (i.view == view) {
                                first = i
                            } else if (i.view == draggingView) {
                                second = i
                            }
                        }
                        if(first.finishInd != second.finishInd) {
                            attach(ind - 1, second, Point(event.x, event.y))
                            calculateNewIndexes()
                        }
                    }
                }
            }
            DragEvent.ACTION_DRAG_ENDED -> {
//                for(i in listOfBlocks) {
//                    i.view.visibility = VISIBLE
//                }
            }
        }
        true
    }

    data class Point(var x: Float, var y: Float)

//    private fun swapTwoBlocks(firstInd: Int, secondInd: Int, firstView: View, secondView: View) {
//        if(firstInd > secondInd) { //Todo: добавить в параметры функции родительского контейнера, например, binding.container
//            binding.container.removeViewAt(firstInd + 1)
//            binding.container.removeViewAt(secondInd + 1)
//            binding.container.addView(firstView, secondInd + 1)
//            binding.container.addView(secondView, firstInd + 1)
//        }
//        else if(secondInd > firstInd){
//            binding.container.removeViewAt(secondInd + 1)
//            binding.container.removeViewAt(firstInd + 1)
//            binding.container.addView(secondView, firstInd + 1)
//            binding.container.addView(firstView, secondInd + 1)
//        }
//
//
//    }


    private fun attach(toBlockInd: Int, fromBlock: Block, dropPoint: Point) {

        val buff = mutableListOf<Block>()
        var ind = toBlockInd
        if(toBlockInd == -1) {
            ind = 0
        }
        val toBlock = listOfBlocks[ind]
        val size = listOfBlocks.size - 1
        for (i in fromBlock.finishInd downTo fromBlock.startInd) {
            buff.add(listOfBlocks[i])

            //binding.container.removeView(listOfBlocks[i].view)
            //listOfBlocks.removeAt(i)
        }
//        for(i in (fromBlock.finishInd-fromBlock.startInd) downTo 0) {
//            var c = 0
//            if(toBlockInd>fromBlock.startInd) c-= buff.size
//            if(toBlockInd == listOfBlocks[toBlockInd].startInd) {
//                Log.i("hello", "start, $toBlockInd, ${listOfBlocks[toBlockInd].startInd}")
//                if (dropPoint.y < toBlock.view.height / 2) {
//                    binding.container.addView(buff[buff.size - i - 1].view, toBlock.startInd + c)
//                    listOfBlocks.add(toBlock.startInd + c, buff[buff.size - i - 1])
//                } else {
//                    binding.container.addView(buff[buff.size - i - 1].view, toBlock.startInd + 1 + c)
//                    listOfBlocks.add(toBlock.startInd + 1 + c, buff[buff.size - i - 1])
//                }
//            }
//            else {
//                Log.i("hello", "finish $toBlockInd ${listOfBlocks[toBlockInd].startInd}")
//                if (dropPoint.y < toBlock.view.height / 2) {
//                    binding.container.addView(buff[buff.size - i - 1].view, toBlock.finishInd + c)
//                    listOfBlocks.add(toBlock.finishInd + c, buff[buff.size - i - 1])
//                } else {
//                    binding.container.addView(buff[buff.size - i - 1].view, toBlock.finishInd + 1 + c)
//                    listOfBlocks.add(toBlock.finishInd + 1 + c, buff[buff.size - i - 1])
//                }
//            }
        val newList = mutableListOf<Block>()
        for (i in 0..size) {
            if ((fromBlock.startInd >  i|| i > fromBlock.finishInd) && i != ind) {
                newList.add(listOfBlocks[i])
            } else if(i == ind) {
                if(dropPoint.y > toBlock.view.height/2) {
                    newList.add(listOfBlocks[ind])
                    for(j in buff.size - 1 downTo  0) {
                        newList.add(buff[j])
                    }
                }
                else {
                    for(j in buff.size - 1 downTo  0) {
                        newList.add(buff[j])
                    }
                    newList.add(listOfBlocks[ind])
                }
            }
        }
        for(i in newList.size - 1 downTo 0) {
            binding.container.removeView(listOfBlocks[i].view)
        }
        for(i in newList) {
            binding.container.addView(i.view)
        }
        Log.i("zalupa", "ne, nu pravda je zalupa")
    }

    private fun calculateNewIndexes() {
        val buffList =  listOfBlocks
        val checkList = mutableListOf<Boolean>()
        for(i in 0 until listOfBlocks.size) {
            checkList.add(false)
        }
        for(i in 0 until listOfBlocks.size - 1) {
            if(checkList[i]) continue
            for(j in i + 1 until listOfBlocks.size) {
                if(checkList[j]) continue
                if(listOfBlocks[i].startInd == listOfBlocks[j].startInd) {
                    buffList[i].startInd = i
                    buffList[j].startInd = i
                    buffList[i].finishInd = j
                    buffList[j].finishInd = j
                    checkList[i] = true
                    checkList[j] = true
                }
            }
            if(!checkList[i]) {
                buffList[i].finishInd = i
                buffList[i].startInd = i
            }
        }
        if(!checkList[listOfBlocks.size-1]) {
            buffList[listOfBlocks.size - 1].finishInd = listOfBlocks.size - 1
            buffList[listOfBlocks.size - 1].startInd = listOfBlocks.size - 1
        }
        listOfBlocks = buffList
    }

    /*
    listOfBlocks.add(toBlock.startInd + i, fromBlock)
    listOfBlocks.removeAt(fromBlock.startInd)
    */
}
