package cs3500.hw02;

import java.util.ArrayList;

/**
 * Represents the open pile(s) in a game of FreeCell.
 */
public class Open extends Pile {

  // constructor for Open Piles
  public Open() {
    super(PileType.OPEN, "O", new ArrayList<ArrayList<Card>>(), 0);
  }

  @Override
  public boolean canPlace(Card c, int destPile) {
    boolean canPlaceFlag = false;
    if (getPiles().isEmpty()) {
      throw new RuntimeException("Must start game");
    }
    ArrayList<Card> thisPile = getPiles().get(destPile);
    if (thisPile.isEmpty()) {
      canPlaceFlag = true;
      //thisPile.add(c);
    } else {
      throw new IllegalArgumentException("Card already in this Pile");
    }
    return canPlaceFlag;
  }
}
