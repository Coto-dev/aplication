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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginStart
import com.example.aplication.databinding.ActivitySecondBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.example.aplication.Logic.createInitialization
import com.example.aplication.Logic.createArithmetic
import com.example.aplication.Logic.pushDataForArithmetic
import com.example.aplication.Logic.pushDataForInitialization
import com.example.aplication.Logic.main
import com.example.aplication.Logic.MainBlock.Companion.consoleOutput


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    data class Block(
        var view: View,
        var type: String,
        var name: String,
        var startInd: Int,
        var finishInd: Int
    )

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

        if (bottomSheetBehavior.peekHeight == 135) {
            binding.floating.setOnClickListener {//компиляция
                bottomSheetBehavior.peekHeight = 0
                bottomSheetConsol.peekHeight = 135

                var i = 0

                for (block in listOfBlocks) {
                    if (block.name == "ForCustomView") {
                        var string = (block.view as ForCustomView).GetText1()
                        var string2 = (block.view as ForCustomView).GetText2()
                        pushDataForArithmetic(string2, string, i)
                    }
                    if (block.name == "For_inizalitation") {
                        var string = (block.view as For_inizalitation).GetText2()
                        pushDataForInitialization(string, i)
                    }

                    i++
                }
                main()
            }
        }

//        val params=ConstraintLayout.LayoutParams(10000,1)
//        binding.container.addView(ForCustomView(this),params)

        //обработка нажатий на кнопки создания блоков
        binding.forArifmetic.setOnClickListener {
            createBlock(ForCustomView(this), "MATH", false)
            //listOfBlocks.add(addViewToScreen(ForCustomView(this)))
            createArithmetic()
        }
        binding.forCycleFor.setOnClickListener {
            createBlock(ForCustomView(this), "FOR", true)
           // addViewToScreen(ForCustomView(this), listOfBlocks.size, listOfBlocks.size + 1)
        }
        binding.forCycleWhile.setOnClickListener {
            createBlock(ForCustomView(this), "WHILE", true)
           // addViewToScreen(ForCustomView(this), listOfBlocks.size, listOfBlocks.size + 1)
        }
        binding.forOperatorIf.setOnClickListener {
             createBlock(ForCustomView(this), "IF", true)
           // addViewToScreen(ForCustomView(this), listOfBlocks.size, listOfBlocks.size + 1)
        }
        binding.forOperatorIfElse.setOnClickListener {
            createBlock(ForCustomView(this), "IF", true)
            //addViewToScreen(ForCustomView(this), listOfBlocks.size, listOfBlocks.size + 1)
        }
        binding.forInitialization.setOnClickListener {
            createBlock(ForCustomView(this), "INIT", false)
            createInitialization()
           // listOfBlocks.add(addViewToScreen2(For_inizalitation(this)))
        }
        binding.forPrint.setOnClickListener {
            createBlock(ForCustomView(this), "PRINT", false)
           // listOfBlocks.add(addViewToScreen3(Print_block(this)))
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createBlock(view: ForCustomView, name: String, isHaveChild: Boolean) {
        view.setName(name)
        binding.container.addView(view)
        val start = listOfBlocks.size
        listOfBlocks.add(Block(view, "", "", start, start))
        view.setOnLongClickListener(choiceTouchListener())
        view.setOnDragListener(choiceDragListener())
        if (isHaveChild) {
            val blockEnd = Block_end(this) //TODO: change forCustomView on blockEnd
            binding.container.addView(blockEnd)
            listOfBlocks[start].finishInd++
            listOfBlocks.add(Block(blockEnd, "", "", start, start + 1))
            blockEnd.setOnLongClickListener(choiceTouchListener())
            blockEnd.setOnDragListener(choiceDragListener())
        }
    }

    //появление обычного блока
    private fun addViewToScreen(view: View): Block {
        // (view as ForCustomView).SetText(listOfBlocks.size.toString())
        binding.container.addView(view)
        view.setOnLongClickListener(choiceTouchListener())
        view.setOnDragListener(choiceDragListener())
        val newBlock = Block(view, "no", "ForCustomView", listOfBlocks.size, listOfBlocks.size)
        return newBlock
    }

    private fun addViewToScreen2(view: View): Block {
        (view as For_inizalitation).SetText(listOfBlocks.size.toString())
        binding.container.addView(view)
        view.setOnLongClickListener(choiceTouchListener())
        view.setOnDragListener(choiceDragListener())
        val newBlock = Block(view, "no", "For_inizalitation", listOfBlocks.size, listOfBlocks.size)
        return newBlock
    }

    private fun addViewToScreen3(view: View): Block {
        //(view as Print_block).setName(listOfBlocks.size.toString())
        binding.container.addView(view)
        view.setOnLongClickListener(choiceTouchListener())
        view.setOnDragListener(choiceDragListener())
        val newBlock = Block(view, "no", "Print_block", listOfBlocks.size, listOfBlocks.size)
        return newBlock
    }


    //  пояление вложенного блока
    private fun addViewToScreen(buff: View, startInd: Int, finishInd: Int) {
        //for (i in startInd..finishInd) {
        var view = ForCustomView(this)
        (view as ForCustomView).setName(listOfBlocks.size.toString())
        (view as ForCustomView).setName("я вложен")
        binding.container.addView(view)
        view.setOnLongClickListener(choiceTouchListener())
        view.setOnDragListener(choiceDragListener())
        val newBlock = Block(view, "nested", "ForCustomView", startInd, finishInd)
        listOfBlocks.add(newBlock)
        var view2 = ForCustomView(this)
        (view2 as ForCustomView).setName(listOfBlocks.size.toString())
        (view2 as ForCustomView).setName("я вложен")
        binding.container.addView(view2)
        view2.setOnLongClickListener(choiceTouchListener())
        view2.setOnDragListener(choiceDragListener())
        val newBlock2 = Block(view2, "nested", "end", startInd, finishInd)
        listOfBlocks.add(newBlock2)
        // }
    }

    private lateinit var draggingView: View

    @SuppressLint("ClickableViewAccessibility")
    fun choiceTouchListener() = OnLongClickListener { view ->
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
                var second = listOfBlocks[0]
                for (i in listOfBlocks) {
                    if (i.view == draggingView) {
                        second = i
                    }
                }
                for (i in second.finishInd downTo second.startInd) {
                    listOfBlocks[i].view.visibility = INVISIBLE
//                    if(i != second.finishInd) {
//                        binding.container.removeView(listOfBlocks[i].view)
//                    }
                }
            }
            DragEvent.ACTION_DROP -> {
                Log.i("hello", "take cumbag")
                if (view != binding.container) {
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
                        if (first.finishInd != second.finishInd) {
                            attach(ind - 1, second, Point(event.x, event.y))
                            calculateNewIndexes()
                            createMargin()
                        }
                    }
                }
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                for (i in listOfBlocks) {
                    i.view.post {
                        i.view.visibility = VISIBLE
                    }
                }


            }
        }
        true
    }

    data class Point(var x: Float, var y: Float)

    private fun attach(toBlockInd: Int, fromBlock: Block, dropPoint: Point) {

        val buff = mutableListOf<Block>()
        var ind = toBlockInd
        if (toBlockInd == -1) {
            ind = 0
        }
        val toBlock = listOfBlocks[ind]
        val size = listOfBlocks.size - 1
        for (i in fromBlock.finishInd downTo fromBlock.startInd) {
            buff.add(listOfBlocks[i])
        }

        val newList = mutableListOf<Block>()
        for (i in 0..size) {
            if ((fromBlock.startInd > i || i > fromBlock.finishInd) && i != ind) {
                newList.add(listOfBlocks[i])
            } else if (i == ind) {
                if (dropPoint.y > toBlock.view.height / 2) {
                    newList.add(listOfBlocks[ind])
                    for (j in buff.size - 1 downTo 0) {
                        newList.add(buff[j])
                    }
                } else {
                    for (j in buff.size - 1 downTo 0) {
                        newList.add(buff[j])
                    }
                    newList.add(listOfBlocks[ind])
                }
            }
        }
        for (i in newList.size - 1 /*- (fromBlock.finishInd - fromBlock.startInd)*/ downTo 0) {
            binding.container.removeView(listOfBlocks[i].view)
        }
        for (i in newList) {
            binding.container.addView(i.view)
        }
        listOfBlocks = newList

    }

    private fun calculateNewIndexes() {
        val buffList = listOfBlocks
        val checkList = mutableListOf<Boolean>()
        for (i in 0 until listOfBlocks.size) {
            checkList.add(false)
        }
        for (i in 0 until listOfBlocks.size) {
            if (checkList[i]) continue
            for (j in i until listOfBlocks.size) {
                if (checkList[j]) continue
                if (listOfBlocks[i].startInd == listOfBlocks[j].startInd &&
                    listOfBlocks[i].finishInd == listOfBlocks[j].finishInd &&
                    i != j
                ) {
                    listOfBlocks[i].startInd = i
                    listOfBlocks[j].startInd = i
                    listOfBlocks[i].finishInd = j
                    listOfBlocks[j].finishInd = j
                    checkList[i] = true
                    checkList[j] = true
                }
            }
            if (!checkList[i]) {
                listOfBlocks[i].startInd = i
                listOfBlocks[i].finishInd = i
                checkList[i] = true
            }
        }
        listOfBlocks = buffList
    }


    private fun createMargin() {
        for (block in listOfBlocks) {
            block.view.x = 0f
        }

        for (block in listOfBlocks) {
            if (block.finishInd - block.startInd >= 2) {
                for (i in block.startInd + 1..block.finishInd - 1) {
                    listOfBlocks[i].view.x += 30f
                }
            }
        }

    }

}
