// I'm gonna go build my own theme park with blackjack and ...... in

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


fun main(args: Array<String>) {
    val rockPaperScissors = RockPaperScissors()
    rockPaperScissors.execute();
}

public class RockPaperScissors {
    fun execute() {
        println("We are going to play: Rock, Paper, Scissors!")
        println("Let's have a fun!!!")
        println()
        val resultList:MutableList<String> = playGame(InputStreamReader(System.`in`))
        println()
        println("This is the End of our Game my friend.")
        for( i in resultList){
            println(i)
        }
        println()

        val win:Int=resultList.filter { i->i=="WIN"}.size;
        val lose:Int=resultList.filter { i->i=="LOSE"}.size;
        val draw:Int=resultList.filter { i->i=="DRAW"}.size;
        println("Number of your 'WIN':$win")
        println("Number of your 'LOSE':$lose")
        println("Number of your 'DRAW':$draw")
    }

    fun playGame(inputStreamReader: InputStreamReader): MutableList<String> {
        val resultList: MutableList<String> = mutableListOf<String>()
        BufferedReader(inputStreamReader).use { br ->
           val intNumberOfRound = getNumberOfRound()
            println("Number of Rounds to play is $intNumberOfRound!")
            for (round in 1..intNumberOfRound) {
                printItems()
                val userChoice: GameItem = getUserChoice(br)
                val computerChoice: GameItem = getComputerChoice()
                println("")
                println("You picked:  $userChoice")
                println("Computer picked: $computerChoice")
                val roundResult:String=evaluateResult(userChoice, computerChoice)
                println("It's a $roundResult.")
                println()
                resultList.add(roundResult)
            }
        }
        return resultList
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

    fun evaluateResult(userChoice: GameItem, computerChoice: GameItem) :String{
        if (userChoice === computerChoice) {
            return "DRAW"
        }
        if (userChoice.isVictim(computerChoice)) {
            return "WIN"
        } else {
            return "LOSE"
        }
    }

    fun getNumberOfRound():Int{
        return 2
        try {
            while(true) {
                println("How much Rounds would you like to play?(Please input Number from 1 to 10)")
                val stringNumberOfRound= readLine()
                if (!Round100.values().any { i -> i.enumString == stringNumberOfRound }) {
                    println("Please enter one of the valid options.")
                } else {
                    return Round100.values().first{ i->i.enumString==stringNumberOfRound }.ordinal+1
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException("There was an error while reading from input.", e)
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