package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.MapArray

class Massive : MainBlock {
    val map = MapArray
    override var ErrorString = ""
    override var status = true
    var textBar: String = ""
    override fun start() = indetify()
    fun indetify() {
        if (!textBar?.contains(Regex("""([^\w,\s={}\[\]\-]|((^)\s*([0-9]+[a-zA-Z]|\d+))|(\w+\s+\w+)|,{2,})"""))) {
            val matches =
                Regex("""(\w+\d*\[\s*\-?\d+\s*\]\s*(=\s*\{\s*((\-?\d+\s*(,|\})\s*)+\s*)\s*)?)""").findAll(
                    textBar
                )

            for (name in matches) {
//                name.value =  name.value.replace("""\s+""".toRegex(), " ")
//                name.value =  name.value.replace("""^\s+""".toRegex(), "")

                val matches = Regex("""(\w+\d*\[\s*\d+\s*\]\s*)""").find(name.value)
                val size = Regex("""\d+""").find(matches?.value.toString())
                val nameMas = Regex("""\w+\d*""").find(matches?.value.toString())
//                println(matches?.value)
//                println(size?.value)
//                println(nameMas?.value)
                val massValue =
                    Regex("""(\s*\{\s*((\-?\d+\s*(,|\})\s*)+\s*)\s*)""").find(name?.value.toString())
                if (massValue?.value?.isEmpty() == false) {
                    val value = Regex("""\-?\d+""").findAll(massValue?.value.toString())
                    val mas = Array(size?.value!!.toInt(), { 0 })
                    var ind = 0
                    for (name in value) {

                        if (name.value.contains(Regex("""\-"""))) {
                            mas[ind] = name.value.toInt()

                        } else
                            mas[ind] = name.value.toInt()
                        // println("name value ${ mas[ind]}")
                        ind++

                    }
                    map.put(nameMas!!.value.toString(), mas)
                } else
                    map.put(nameMas!!.value.toString(), Array(size?.value!!.toInt(), { 0 }))
            }

        } else {

            //исключение(тут надо в UX выдать пользователю ошибку типо ввел невозможную переменную e.g "12awd","@#!aue" и тд)
            val matches =
                Regex("""([^\w|,|\s|\[\]]|((^|,)\s*([0-9]+[a-zA-Z]|\d+))|(\w+\s+\w+)|,{2,})""").find(
                    textBar
                )
            ErrorString = "the value of the variable was entered incorrectly ${matches?.value}"
            status = false
        }
        //(map["a"]?.set(1,3))
        println("-99999999999999999999999")
        println(map["mas"]?.get(0))
//        println(map["a"]?.get(1))
    }
}