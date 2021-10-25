// I'm gonna go build my own theme park with blackjack and ...... in

fun main(args: Array<String>) {
    println("We are going to play: Rock, Paper, Scissors!")
    println("Let's have a fun!!!")
    println()
}

public class RockPaperScissors {

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