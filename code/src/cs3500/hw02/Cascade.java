package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the cascade piles in a game of FreeCell
 */
public class Cascade extends Pile {
  //int numPiles;

  public Cascade() {
    super("C", new ArrayList<ArrayList<Card>>(), 0);
    //this.numPiles = numPiles;
  }


  public void roundRobin(List<Card> deck) {
    for (int i = 0; i < numPile; i++) {
      pile.add(new ArrayList<Card>());
    }
    //if (true) {
      int countdown = 0;
      for (Card c : deck) {
        pile.get(countdown).add(c);
        countdown = (countdown + 1) % numPile;
      //}
    }
  }
}
