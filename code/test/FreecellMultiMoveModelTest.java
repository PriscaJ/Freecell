
import org.junit.Test;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;
import cs3500.hw04.FreecellModelCreator;
import cs3500.hw04.FreecellMultiMoveModel;

import static org.junit.Assert.*;

public class FreecellMultiMoveModelTest {
  FreecellModelCreator modelCreator;
  FreecellOperations<Card> singleModel;
  FreecellOperations<Card> multiModel;

  public void initData() {
    modelCreator = new FreecellModelCreator();
    singleModel = new FreecellModel();
    multiModel = new FreecellMultiMoveModel();
  }

  @Test
  public void invalidBuild() {

  }

  @Test
  public void correctModel() {
    this.initData();
    FreecellOperations<Card> testSingleModel =
            modelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE);
    FreecellOperations<Card> testMultiModel =
            modelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);

    assertEquals(true, testSingleModel.getClass().
            equals(new FreecellModel().getClass()));
    assertEquals(true, testMultiModel.getClass().
            equals(new FreecellMultiMoveModel().getClass()));
  }

  @Test
  public void sameOutputModel() {
    this.initData();
    multiModel.startGame(multiModel.getDeck(), 4, 3, false);
    singleModel.startGame(singleModel.getDeck(), 4, 3, false);
    assertEquals(multiModel.getGameState(), singleModel.getGameState());
  }

  @Test
  public void notEnoughSpaces() {




  }

  @Test
  public void goodMultiMove() {

  }

  @Test
  public void singleInMultiMove() {
    this.initData();
    multiModel.startGame(multiModel.getDeck(), 4, 3, false);
    multiModel.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    multiModel.move(PileType.CASCADE, 0, 11, PileType.OPEN, 1);
    multiModel.move(PileType.CASCADE, 0, 10, PileType.OPEN, 2);
    multiModel.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 6♠\n" +
            "O3: 2♠\n" +
            "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 10♠\n" +
            "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠\n" +
            "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠\n" +
            "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠",
            multiModel.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void multiInSingleMove() {

  }

  @Test
  public void controlMulti() {

  }

  @Test
  public void emptyCascadeMove() {
    //multiModel.startGame(multiModel.getDeck(), );
  }

  @Test
  public void emptyOpenMove() {

  }


}