package Logic

interface MainBlock {
    fun start()
    companion object {
        val variables = mutableMapOf<String,Int>()
        val listOfBlocks = mutableListOf<MainBlock>()

    }

}