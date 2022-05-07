package Logic
import Logic.MainBlock.Companion.variables
import java.util.*
class Initialization : MainBlock {
    val vars = variables
    val name :String? = null
    val previousBlock : MainBlock? = null
    val nextBlock : MainBlock? = null
    var textBar: String?=null
   override fun start() = indetify()
    fun indetify(){
        if (!textBar?.contains(Regex("""([^\w|,|\s]|((^|,)\s*([0-9]+[a-zA-Z]|\d+))|(\w+\s+\w+)|,{2,})"""))!!) {
            val matches = Regex("""[a-zA-Z]+[0-9]*""").findAll(textBar!!)
            for(name in matches){
                vars+= name.value to 0
            }

        }
        else{
            //исключение(тут надо в UX выдать пользователю ошибку типо ввел невозможную переменную e.g "12awd","@#!aue" и тд)
            println("input error")
        }
        println(vars)
    }

}