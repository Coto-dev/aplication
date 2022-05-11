package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.MapArray

class Massive : MainBlock {
    val map = MapArray
    override var ErrorString = ""
    override var status = true
    var textBar: String=""
    override fun start() = indetify()
    fun indetify(){
        if (!textBar?.contains(Regex("""([^\w|,|\s|\[\]]|((^|,)\s*([0-9]+[a-zA-Z]|\d+))|(\w+\s+\w+)|,{2,})"""))) {
            val matches = Regex("""\w+\d*\[\d+\]""").findAll(textBar)
            for(name in matches){
                val matches = Regex("""\w+\d*\[\d+\]""").find(name.value)
                val size = Regex("""\d+""").find(matches.toString())
                val nameMas = Regex("""\w+\d*""").find(matches.toString())
                map += nameMas!!.value.toString() to  arrayOf(size?.value!!.toInt())
            }
    println(map.values)
            println(map.keys)
            println(map)
        }
        else{
            //исключение(тут надо в UX выдать пользователю ошибку типо ввел невозможную переменную e.g "12awd","@#!aue" и тд)
            val matches = Regex("""([^\w|,|\s|\[\]]|((^|,)\s*([0-9]+[a-zA-Z]|\d+))|(\w+\s+\w+)|,{2,})""").find(textBar)
            ErrorString = "the value of the variable was entered incorrectly ${matches?.value}"
            status = false
        }
    println(map.get("mas"))
    }
}