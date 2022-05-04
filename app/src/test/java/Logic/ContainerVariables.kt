package Logic
import java.util.*
class ContainerVariables : MainBlock() {
    val variablesForContainer = variables
    val name :String? = null
    val previousBlock : MainBlock? = null
    val nextBlock : MainBlock? = null
    fun indetify(textBar:String){
        if (!textBar.contains(Regex("""([^\w|,|\s]|((^|,)\s*([0-9]+[a-zA-Z]|\d+))|(\w+\s+\w+)|,{2,})"""))) {
            val matches = Regex("""[a-zA-Z]+[0-9]*""").findAll(textBar)
            for(name in matches){
                variablesForContainer+= name.value to 0
            }

        }
        else{
            //исключение(тут надо в UX выдать пользователю ошибку типо ввел невозможную переменную e.g "12awd","@#!aue" и тд)
            println("false")
        }
        println(variablesForContainer)
    }

}