package Logic
import java.util.*
import kotlin.reflect.typeOf

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
        fun indetify(textBar:String){
        if (!textBar.contains(Regex("""([^\w|,|\s]|((^|,)[0-9]+[a-zA-Z]))"""))) {
            val matches = Regex("""(\w+([0-9])*)""").findAll(textBar)
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
class Arithmetic : MainBlock() {
    val vars = variables
    val name :String? = null
    val previousBlock : MainBlock? = null
    val nextBlock : MainBlock? = null
  fun cacluate(textBar:String){
      val stack: Stack<Char> = Stack<Char>()
      val text = textBar.replace("""\s""".toRegex(), "")
      var RPN:String = ""
      for (i in text) {
          if(i.isDigit()){
            RPN+=i

          }
        else{
              RPN+=" "
            if (i == '-' || i == '+'){
                if(!stack.isEmpty()) {
                    if (stack.peek() == '-' || stack.peek() == '+' || stack.peek() == '/' || stack.peek() == '*') {
                        RPN += stack.pop()
                    }
                    stack.push(i)
                }
                else stack.push(i)
            }
              if (i == '*' || i == '/') {
                  if(!stack.isEmpty()) {
                      if (stack.peek() == '/' || stack.peek() == '*') {
                          RPN += stack.pop()
                      }
                      stack.push(i)
                  }
                      else stack.push(i)
                }

              if (i == '(' || i ==')') {
                 if (i == '('){
                     stack.push(i)
                 }
                  else {
                     while(stack.peek() != '('){
                         RPN+= stack.pop()
                     }
                     stack.pop()
                 }
                 }
              }
        }

      while(!stack.isEmpty()) RPN+= stack.pop()
        println(RPN)
      val stackInt: Stack<String> = Stack<String>()
      var value = ""
      for (i in 0..RPN.length){
          if (RPN[i] != '-' && RPN[i] != '+' && RPN[i] != '/' && RPN[i] != '*') {
              if (RPN[i+1].isDigit())
              value+=RPN[i]
          }
          else {
              stackInt.push(value)
              println(stackInt)
              value = ""
              if (RPN[i] == '+'){
                  val x = stackInt.pop()
                  val y = stackInt.pop()
                  val count = x.toInt() + y.toInt()
                  //print(count)
                  stackInt.push(count.toString())
              }
              else
              if (RPN[i] == '-'){
                  val x = stackInt.pop()
                  val y = stackInt.pop()
                  val count = y.toInt() - x.toInt()
                 // println(value)
//                  val count = stack.pop().code + stack.pop().code
                  stackInt.push(count.toString())
              }
              else
              if (RPN[i] == '*'){
                  val x = stackInt.pop()
                  val y = stackInt.pop()
                  val count = x.toInt() * y.toInt()
                  //println(value)
//                  val count = stack.pop().code + stack.pop().code
                  stackInt.push(count.toString())
              }
              else
              if (RPN[i] == '/'){
                  val x = stackInt.pop()
                  val y = stackInt.pop()
                  val count = x.toInt() / y.toInt()
                  //println(value)
//                  val count = stack.pop().code + stack.pop().code
                  stackInt.push(count.toString())
              }

          }
          //println(stackInt)
      }
      println(stackInt.pop())
  }
}

fun main(){

        val a = ContainerVariables()
        a.indetify("aaa,b,a2,aaw233,    awd        ,a23,   adwadwadawdadwa,wdadsawd")
        val b = Arithmetic()

             b.cacluate("3+4+2")
       //b.cacluate("(3+2)")
    }
