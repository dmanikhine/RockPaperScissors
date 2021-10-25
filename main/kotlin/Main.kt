// I'm gonna go build my own theme park with blackjack and ...... in

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


fun main(args: Array<String>) {
    println("We are going to play: Rock, Paper, Scissors!")
    println("Let's have a fun!!!")
    println()
    val rockPaperScissors = RockPaperScissors()
    rockPaperScissors.execute();
}

public class RockPaperScissors {
    fun execute() {
        playGame(InputStreamReader(System.`in`))
    }

    fun playGame(inputStreamReader: InputStreamReader) {
        BufferedReader(inputStreamReader).use { br ->
            printItems()
            val userChoice: GameItem = getUserChoice(br)
            val computerChoice: GameItem = getComputerChoice()
            println("")
            println("You picked:  $userChoice")
            println("Computer picked: $computerChoice")
            evaluateResult(userChoice, computerChoice)
        }
    }

    fun printItems() {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Please input your choice of one of the following:")
        for (choice in GameItem.values()) {
            stringBuilder.append(" ")
            stringBuilder.append(choice.enumString)
        }
        println(stringBuilder.toString())
    }

    fun getUserChoice(br:BufferedReader): GameItem {
        try {
            while(true) {
                val userChoiceString = br.readLine()
                if (!GameItem.values().any { i -> i.enumString == userChoiceString }) {
                    println("Please enter one of the valid options.")
                } else {
                    return GameItem.valueOf(userChoiceString.uppercase(Locale.getDefault()))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException("There was an error while reading from input.", e)
        }
    }

    fun getComputerChoice(): GameItem {
        return GameItem.values()[Random().nextInt(GameItem.values().size)]
    }

    fun evaluateResult(userChoice: GameItem, computerChoice: GameItem) {
        if (userChoice === computerChoice) {
            println("It's a DRAW.")
            return
        }
        if (userChoice.isVictim(computerChoice)) {
            println("It's a WIN!")
        } else {
            println("It's a LOSE. :(")
        }
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