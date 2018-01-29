package cs3500.hw02;

import java.util.ArrayList;

/**
 * Represents the 4 foundation Piles in a FreeCell game.
 */
public class Foundation extends Pile {
  //int numPile;
  public Foundation(ArrayList<ArrayList<Card>> pile, int numPile) {
    super("F", pile, 4);
    //this.numPile = 4;
  }

  /**
   * Checks if the foundation piles contains all the cards.
   * @return Boolean if the all cards are in the foundations.
   */
  public boolean allCards() {
    ArrayList<Card> seen = new ArrayList<Card>();
    for (ArrayList<Card> suitPile: pile) {
      for (Card c: suitPile) {
        if (seen.contains(c)) {
          throw new RuntimeException("duplicate card");
        }
        else {
          seen.add(c);
        }
      }
    }
    return seen.size() == 52;
  }
}
