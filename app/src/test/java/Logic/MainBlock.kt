package Logic

interface MainBlock {
    fun start()
    var status:Boolean
    var ErrorString:String
    companion object {
        val variables = mutableMapOf<String,Int>()
        val listOfBlocks = mutableListOf<MainBlock>()
        val MapArray = mutableMapOf<String,Array<Int>>()
    }

}