package com.example.aplication

import android.annotation.SuppressLint
import android.content.ClipData
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.core.view.marginStart
import com.example.aplication.Logic.*
import com.example.aplication.databinding.ActivitySecondBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.example.aplication.Logic.MainBlock.Companion.consoleOutput


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var countOfCycli = 0

    data class Block(
        var view: View,
        var type: String,
        var canHaveElse: Boolean,
        var name: String,
        var startInd: Int,
        var finishInd: Int
    )

    private var listOfBlocks: MutableList<Block> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var buttonGo = binding.go
        buttonGo.setVisibility(View.INVISIBLE);
        var buttonStop = binding.floating2
        buttonStop.setVisibility(View.INVISIBLE);

        //консоль и bottom sheet
        val frame = findViewById<FrameLayout>(R.id.sheet)
        val consol = findViewById<FrameLayout>(R.id.sheet2)
        consol.setVisibility(View.INVISIBLE)
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
        var buttonPlay = binding.floating

        buttonPlay.setOnClickListener {//кomпиляция
            frame.setVisibility(View.INVISIBLE)
            consol.setVisibility(View.VISIBLE)
            bottomSheetBehavior.peekHeight = 0
            bottomSheetConsol.peekHeight = 135
            buttonPlay.setVisibility(View.INVISIBLE)
            buttonStop.setVisibility(View.VISIBLE);
            var i = 0
            for (block in listOfBlocks) {
                println(block.name)
                println(block.startInd)

                if (block.name == "ForCustomView") {
                    var string = (block.view as ForCustomView).GetText1()
                    var string2 = (block.view as ForCustomView).GetText2()
                    pushDataForArithmetic(string2, string, i)
                }
                if (block.name == "For_inizalitation") {
                    var string = (block.view as For_inizalitation).GetText2()
                    pushDataForInitialization(string, i)
                }
                if (block.name == "IF") {
                    println(block.finishInd)
                    var string = (block.view as If_block).GetText2()
                    pushDataForIf(string, i, block.finishInd)
                }
                if (block.name == "WHILE") {
                    println(block.finishInd)
                    var string = (block.view as If_block).GetText2()
                    pushDataForWhile(string, i, block.finishInd)
                }
                if (block.name == "PRINT") {
                    var string = (block.view as Print_block).GetText2()
                    pushDataForOutput(string, i)
                    for (i in consoleOutput) {
                        println("jdhbfvjkd $i")
                        var text = TextView(this)
                        binding.containerForTextView.addView(text)
                        text.background = Drawable.createFromPath("drawable/block_button.xml")
                        var string2 = i
                        text.text = string2
                    }

                }
                if (block.name == "end") {
                    println(block.finishInd)
                    pushDataForEnd(block.finishInd)
                }
                i++
            }
            main()
        }

        buttonStop.setOnClickListener {
            frame.setVisibility(View.VISIBLE)
            consol.setVisibility(View.INVISIBLE)
            bottomSheetConsol.peekHeight = 135
            buttonPlay.setVisibility(View.VISIBLE)
            buttonStop.setVisibility(View.INVISIBLE)
        }

        //обработка нажатий на кнопки создания блоков
        binding.forArifmetic.setOnClickListener {
            createBlock(ForCustomView(this), "ForCustomView", false)
            createArithmetic()
        }
        binding.forCycleWhile.setOnClickListener {
            createBlock(While_block(this), "WHILE", true)
            createWhile()
            createNull()
        }
        binding.forOperatorIf.setOnClickListener {
            createBlock(If_block(this), "IF", true)
            createIf()
            createNull()
        }
        binding.forOperatorIfElse.setOnClickListener {
            var variable = countBlock("IF")
            var variable2 = countBlock("ELSE")
            if (variable > variable2) {
                createBlock(Else_block(this), "ELSE", true)
            }
        }
        binding.forInitialization.setOnClickListener {
            createBlock(For_inizalitation(this), "For_inizalitation", false)
            createInitialization()
        }
        binding.forPrint.setOnClickListener {
            createOutput()
            createBlock(Print_block(this), "PRINT", false)
        }
//        binding.forInput.setOnClickListener {
//            frame.setVisibility(View.INVISIBLE)
//            consol.setVisibility(View.VISIBLE)
//            bottomSheetConsol.peekHeight = 135
//            buttonPlay.setVisibility(View.INVISIBLE)
//            buttonStop.setVisibility(View.VISIBLE);
//            var edit = EditText(this)
//            binding.containerForTextView.addView(edit)
//            buttonGo.setVisibility(View.VISIBLE);
//            edit.width = 150
//            edit.setTextColor(Color.parseColor("#e3e3e3"))
//        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createBlock(view: View, name: String, isHaveChild: Boolean) {
        var flag = false
        val start = listOfBlocks.size
        if (name == "IF") {
            flag = true
        }
        if (!(view is Else_block)) {
            binding.container.addView(view)
            listOfBlocks.add(Block(view, "", false, name, start, start))
            view.setOnLongClickListener(choiceTouchListener())
            view.setOnDragListener(choiceDragListener())
            if (isHaveChild) {
                val blockEnd = Block_end(this)
                listOfBlocks[start].finishInd++
                listOfBlocks.add(Block(blockEnd, "", flag, "end", start, start + 1))
                blockEnd.setOnLongClickListener(choiceTouchListener())
                blockEnd.setOnDragListener(choiceDragListener())
                binding.container.addView(blockEnd)
            }
        } else {
            for (index in 1..listOfBlocks.size) {
                if (listOfBlocks[index].canHaveElse) {
                    binding.container.addView(view, index + 1)
                    listOfBlocks.add(index + 1, Block(view, "", false, name, index + 1, index + 1))
                    view.setOnLongClickListener(choiceTouchListener())
                    view.setOnDragListener(choiceDragListener())



                    val blockEnd = Block_end(this)
                    listOfBlocks.add(
                        index + 2,
                        Block(blockEnd, "", false, "end", index + 1, index + 2)
                    )
                    blockEnd.setOnLongClickListener(choiceTouchListener())
                    blockEnd.setOnDragListener(choiceDragListener())
                    binding.container.addView(blockEnd, index + 2)
                    calculateNewIndexes()
                    break
                }
            }
        }
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
                        if (listOfBlocks[second.startInd].view is Else_block && !canAttachElse(
                                ind - 1,
                                Point(event.x, event.y)
                            )
                        ) {
                            return@OnDragListener true
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
        val buffList = mutableListOf<Block>()
        for (i in listOfBlocks) {
            buffList.add(i)
        }
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
                    buffList[i].startInd = i
                    buffList[j].startInd = i
                    buffList[i].finishInd = j
                    buffList[j].finishInd = j
                    checkList[i] = true
                    checkList[j] = true
                    break
                }
            }
            if (!checkList[i]) {
                buffList[i].startInd = i
                buffList[i].finishInd = i
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

    private fun countBlock(string: String): Int {
        var count = 0
        for (block in listOfBlocks) {
            if (block.name == string) {
                count++
            }
        }
        return count
    }

    private fun canAttachElse(toBlockInd: Int, dropPoint: Point): Boolean {
        if (toBlockInd == -1) {
            return false
        }
        return if (dropPoint.y > listOfBlocks[toBlockInd].view.height / 2)
            (listOfBlocks[toBlockInd].canHaveElse)
        else (listOfBlocks[toBlockInd - 1].canHaveElse)
    }

}
