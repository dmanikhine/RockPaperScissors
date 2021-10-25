import java.io.FileReader
import java.io.InputStreamReader
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

internal class RockPaperScissorsTest {
    private val rps:RockPaperScissors=RockPaperScissors()

    private val rock: RockPaperScissors.GameItem = RockPaperScissors.GameItem.ROCK;
    private val paper: RockPaperScissors.GameItem = RockPaperScissors.GameItem.PAPER;
    private val scissors: RockPaperScissors.GameItem = RockPaperScissors.GameItem.SCISSORS;

    private val win:RockPaperScissors.GameResult=RockPaperScissors.GameResult.WIN
    private val lose:RockPaperScissors.GameResult=RockPaperScissors.GameResult.LOSE
    private val draw:RockPaperScissors.GameResult=RockPaperScissors.GameResult.DRAW


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
    fun test_evaluateResult() {
        assertEquals(draw,rps.evaluateResult(rock,rock))
        assertEquals(lose,rps.evaluateResult(rock,paper))
        assertEquals(win,rps.evaluateResult(rock,scissors))

        assertEquals(win,rps.evaluateResult(paper,rock))
        assertEquals(draw,rps.evaluateResult(paper,paper))
        assertEquals(lose,rps.evaluateResult(paper,scissors))

        assertEquals(lose,rps.evaluateResult(scissors,rock))
        assertEquals(win,rps.evaluateResult(scissors,paper))
        assertEquals(draw,rps.evaluateResult(scissors,scissors))

    }


    @Test
    fun test_playGame(){
        val path = System.getProperty("user.dir")+"/src/test/kotlin/"+"testListRockPaperScissors"
        val resultList:MutableList<RockPaperScissors.GameResult> = rps.playGame(FileReader(path))

        val expectedWIN:Int=40
        val actualWIN=resultList.filter { i->i==win}.size
        assertEquals(expectedWIN,actualWIN)

        val expectedDRAW:Int=50
        val actualDRAW=resultList.filter { i->i==draw}.size
        assertEquals(expectedDRAW,actualDRAW)

        val expectedLOSE:Int=10
        val actualLOSE=resultList.filter { i->i==lose}.size
        assertEquals(expectedLOSE,actualLOSE)
    }


}