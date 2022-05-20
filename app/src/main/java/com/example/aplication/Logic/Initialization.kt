package com.example.aplication.Logic
import com.example.aplication.Logic.MainBlock.Companion.variables

class Initialization : MainBlock {
    override var ErrorString = ""
    override var status = true
    val vars = variables
    val name :String? = null
    val previousBlock : MainBlock? = null
    val nextBlock : MainBlock? = null
    var textBar: String=""
   override fun start() = indetify()
    fun indetify(){
        if (!textBar?.contains(Regex("""([^\w|,|\s]|((^|,)\s*([0-9]+[a-zA-Z]+|\d+))|(\w+\s+\w+)|,{2,})"""))!!) {
            val matches = Regex("""[a-zA-Z]+[0-9]*""").findAll(textBar)
            for(name in matches){
                vars+= name.value to 0
            }

        }
        else{
            //исключение(тут надо в UX выдать пользователю ошибку типо ввел невозможную переменную e.g "12awd","@#!aue" и тд)
                val text = textBar
                val matches = Regex("""([^\w|,|\s]|((^|,)\s*([0-9]+[a-zA-Z]+|\d+))|(\w+\s+\w+)|,{2,})""").find(text)
            ErrorString = "the value of the variable was entered incorrectly ${matches?.value}"
            status = false
        }
        println(vars)
    }

}