import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FreecellModelTest {

  FreecellOperations<Card> model;
  List<Card> testDeck;

  /**
   * Initializes the data.
   */
  public void initData() {
    model = new FreecellModel();
    testDeck = model.getDeck();

  }

  @Test
  public void testGetDeck() {

    //assertEquals(52, model.getDeck().size());

    this.initData();


    System.out.print(model.getGameState());
    List<Card> testList = model.getDeck();
    assertEquals(52, testList.size());

    ArrayList<Card> seen = new ArrayList<Card>();
    boolean answer = false;
    for (Card c : testList) {
      if (seen.contains(c)) {
        answer = false;
      } else {
        seen.add(c);
      }
    }

    assertEquals(52, seen.size());
  }

  @Test
  public void testStartGame() {
    model.startGame(model.getDeck(), 5, 2, false);
    assertEquals("", model.getGameState());

  }

  @Test
  public void testMoveToEmptyOpen() {
    this.initData();
    // List<Card> deck = model.getDeck();
    model.startGame(model.getDeck(), 4, 3, false);
    System.out.print(model.getGameState());
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals(
            "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: 10♠\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠\n" +
                    "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠\n" +
                    "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠\n" +
                    "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠",
            model.getGameState());


    // ignore vvvvvv

    /** model.startGame(model.getDeck(), 4, 2, false);
    // model.move(PileType.CASCADE, 1 , )

    model.startGame(model.getDeck(), 4, 1, false);
    String stage1 = model.getGameState();
    System.out.print(stage1);

    model.move(PileType.CASCADE, 1, 12, PileType.OPEN, 1);
    String stage2 = model.getGameState();
    System.out.print(stage2); **/

  }

  @Test
  public void testGameState() {
    this.initData();

    model.startGame(model.getDeck(), 4, 3, false);
    assertEquals(
            "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠\n" +
                    "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠\n" +
                    "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠\n" +
                    "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠",
            model.getGameState());
  }

  @Test
  public void testGameOver() {
    this.initData();
    model.startGame(model.getDeck(),5,3,false);
    assertEquals(false, model.isGameOver());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testFullFoundation() {
    this.initData();
    model.startGame(model.getDeck(), 4, 3, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
  }



  @Test
  public void testShuffle() {
    this.initData();
    String stage1 = model.getGameState();
    model.startGame(model.getDeck(), 4, 2, true);
    String stage2 = model.getGameState();

    assertNotEquals(stage1, stage2);
  }

  @Test
  public void testEmptyGameState() {
    this.initData();
    model.startGame(model.getDeck(), 2, 3, false);
    assertEquals("", model.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
    public void testLittleCascade() {
    this.initData();
    model.startGame(model.getDeck(), 2, 3, false);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testLittleOpen() {
    this.initData();
    model.startGame(model.getDeck(), 4, 0, false);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testFoundationInvalid() {
    this.initData();
    model.startGame(model.getDeck(), 4, 2, false);
    // todo: wrong suit
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCascadeInvalid() {
    this.initData();
    model.startGame(model.getDeck(), 4, 2, false);
    // todo: wrong suit
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOpenInvalid() {
    this.initData();
    model.startGame(model.getDeck(), 4, 2, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 1, 12, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 2, 12, PileType.OPEN, 1);
  }
}