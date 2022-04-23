package Logic

    open class MainBlock {
        companion object {
            val variables = mutableMapOf<String,Int>()

        }

    }
    class ContainerVariables : MainBlock() {
        val variablesForContainer = variables
        val name :String? = null
        val previousBlock : MainBlock? = null
        val nextBlock : MainBlock? = null
        fun assign(){
            variablesForContainer+= "f" to 4
        }
    }
class Arithmetic : MainBlock() {
    val vars = variables
    val name :String? = null
    val previousBlock : MainBlock? = null
    val nextBlock : MainBlock? = null
   fun writeln(){
       println(vars)
   }
}

fun main(){
        val a = ContainerVariables()
        a.assign()
        val b = Arithmetic()
        b.writeln()
    }
