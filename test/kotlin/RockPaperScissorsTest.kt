import java.io.FileReader
import java.io.InputStreamReader
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

internal class RockPaperScissorsTest {
    private val rock: RockPaperScissors.GameItem = RockPaperScissors.GameItem.ROCK;
    private val paper: RockPaperScissors.GameItem = RockPaperScissors.GameItem.PAPER;
    private val scissors: RockPaperScissors.GameItem = RockPaperScissors.GameItem.SCISSORS;



    @Test
    fun testRock() {
        assertFalse(rock.isVictim(rock))
        assertFalse(rock.isVictim(paper))
        assertTrue(rock.isVictim(scissors))
    }

    @Test
    fun testPaper() {
        assertTrue(paper.isVictim(rock))
        assertFalse(paper.isVictim(paper))
        assertTrue(rock.isVictim(scissors))
    }

    @Test
    fun testScissors() {
        assertFalse(scissors.isVictim(rock))
        assertTrue(scissors.isVictim(paper))
        assertFalse(scissors.isVictim(scissors))
    }

    @Test
    fun test_playGame(){
        val rps=RockPaperScissors()
        val path = System.getProperty("user.dir")+"/src/test/kotlin/"+"testListRockPaperScissors"
        val resultList:MutableList<RockPaperScissors.GameResult> = rps.playGame(FileReader(path))

        val expectedWIN:Int=40
        val actualWIN=resultList.filter { i->i==RockPaperScissors.GameResult.WIN }.size
        assertEquals(expectedWIN,actualWIN)

        val expectedDRAW:Int=50
        val actualDRAW=resultList.filter { i->i==RockPaperScissors.GameResult.DRAW }.size
        assertEquals(expectedDRAW,actualDRAW)

        val expectedLOSE:Int=10
        val actualLOSE=resultList.filter { i->i==RockPaperScissors.GameResult.LOSE }.size
        assertEquals(expectedLOSE,actualLOSE)
    }


}