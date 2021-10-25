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


}