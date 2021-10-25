// I'm gonna go build my own theme park with blackjack and ...... in

fun main(args: Array<String>) {
    println("We are going to play: Rock, Paper, Scissors!")
    println("Let's have a fun!!!")
    println()
    val rockPaperScissors = RockPaperScissors()
    rockPaperScissors.printItems()
}

public class RockPaperScissors {
    
    fun printItems() {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Please input your choice of one of the following:")
        for (choice in GameItem.values()) {
            stringBuilder.append(" ")
            stringBuilder.append(choice.enumString)
        }
        println(stringBuilder.toString())
    }

    enum class GameItem(val enumString: String) {
        ROCK("rock"),
        PAPER("paper"),
        SCISSORS("scissors");

        companion object {
            init {
                ROCK.listOfVictims.add(SCISSORS)
                PAPER.listOfVictims.add(ROCK)
                SCISSORS.listOfVictims.add(PAPER)
            }
        }
        private val listOfVictims: MutableList<GameItem> = ArrayList()
        fun isVictim(otherChoice: GameItem): Boolean {
            return listOfVictims.contains(otherChoice)
        }
    }
}