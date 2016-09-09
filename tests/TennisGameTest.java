import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.Test;

public class TennisGameTest{ 
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
		@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}		
}

public class GameTest {

    Player victor;
    Player sarah;
    Game game;

    //Before
    public void beforeGameTest() {
        victor = new Player("Victor");
        sarah = new Player("Sarah");
        game = new Game(victor, sarah);
    }

    //Test
    public void loveShouldBeDescriptionForScore0() {
        Game game = new Game(victor, sarah);
        assertThat(game, hasProperty("score", is("love, love")));
    }

    //Test
    public void fifteenShouldBeDescriptionForScore1() {
        sarah.winBall();
        assertThat(game, hasProperty("score", is("love, fifteen")));
    }

    //Test
    public void thirtyShouldBeDescriptionForScore2() {
        victor.winBall();
        victor.winBall();
        sarah.winBall();
        assertThat(game, hasProperty("score", is("thirty, fifteen")));
    }

    private Matcher<? super Game> hasProperty(String string, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	//Test
    public void fortyShouldBeDescriptionForScore3() {
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
                victor.winBall();
        });
        assertThat(game, hasProperty("score", is("forty, love")));
    }

    //Test
    public void advantageShouldBeDescriptionWhenLeastThreePointsHaveNeenScoredByEachSideAndPlayerHasOnePointMoreThanHisOpponent() {
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
            victor.winBall();
        });
        IntStream.rangeClosed(1, 4).forEach((Integer) -> {
            sarah.winBall();
        });
        assertThat(game, hasProperty("score", is("advantage Sarah")));
    }

    @Test
    public void deuceShouldBeDescriptionWhenAtLeastThreePointsHaveBeenScoredByEachPlayerAndTheScoresAreEqual() {
        for(int index = 1; index <= 3; index++) {
            victor.winBall();
        }
        for(int index = 1; index <= 3; index++) {
            sarah.winBall();
        }
        assertThat(game, hasProperty("score", is("deuce")));
        victor.winBall();
        assertThat(game, hasProperty("score", is(not("deuce"))));
        sarah.winBall();
        assertThat(game, hasProperty("score", is("deuce")));
    }

    @Test
    public void gameShouldBeWonByTheFirstPlayerToHaveWonAtLeastFourPointsInTotalAndWithAtLeastTwoPointsMoreThanTheOpponent() {
        for(int index = 1; index <= 4; index++) {
            victor.winBall();
        }
        for(int index = 1; index <= 3; index++) {
            sarah.winBall();
        }
        assertThat(game, hasProperty("score", is(not("Victor won"))));
        assertThat(game, hasProperty("score", is(not("Sarah won"))));
        victor.winBall();
        assertThat(game, hasProperty("score", is("Victor won")));
    }

	private String not(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object is(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}

