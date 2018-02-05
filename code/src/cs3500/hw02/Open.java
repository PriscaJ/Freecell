package cs3500.hw02;

import java.util.ArrayList;

/**
 * Represents the open pile(s) in a game of FreeCell.
 */
public class Open extends Pile {

  // constructor for Open Piles
  public Open() {
    super(PileType.OPEN, new ArrayList<ArrayList<Card>>(), 0);
  }

  @Override
  public void canPlace(Card c, PileType pt, int destPile) {
    if (pile.isEmpty()) {
      System.out.print("Must start game");
    }
    ArrayList<Card> thisPile = pile.get(destPile);
    System.out.print("Keep going");
    if (thisPile.isEmpty()) {
      System.out.print("canReach?");
      thisPile.add(c);
    } else {
      throw new IllegalArgumentException("Card already in this Pile");
    }
  }
}
