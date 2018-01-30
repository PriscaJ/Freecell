import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;

import static org.junit.Assert.assertEquals;

public class FreecellModelTest {

  FreecellOperations<Card> model;
  List<Card> testDeck;

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
  public void testMove() {
    this.initData();
    List<Card> deck = model.getDeck();

    model.startGame(model.getDeck(), 4, 2, false);
    // model.move(PileType.CASCADE, 1 , )

    model.startGame(model.getDeck(), 4, 1, false);
    String stage1 = model.getGameState();
    System.out.print(stage1);

    model.move(PileType.CASCADE, 1, 12, PileType.OPEN, 1);
    String stage2 = model.getGameState();
    System.out.print(stage2);
  }

  @Test
  public void testGameState() {
    this.initData();

    List<Card> d = model.getDeck();

    assertEquals("", model.getGameState());

    model.startGame(d, 4, 2, false);
    System.out.print(model.getGameState());
  }

  @Test
  public void testGameOver() {

  }


  @Test(expected = IllegalArgumentException.class)
    public void testException() {

    }
}