package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the open pile(s) in a game of FreeCell.
 */
public class Open extends Pile {

  // constructor for Open Piles
  public Open() {
    super(PileType.OPEN, "O", new ArrayList<ArrayList<Card>>(), 0);
  }

  /**
   * Initializes the open piles when startGame is called.
   * Prevents duplication when startGame is called multiple times
   */
  void initOpen() {
    for (int i = 0; i < getPileNum(); i++) {
      this.getPiles().add(new ArrayList<Card>());
    }
  }

  @Override
  public boolean canPlace(Card c, int destPile) {
    boolean canPlaceFlag = false;
    if (getPiles().isEmpty()) {
      System.out.print("Must start game");
    }
    ArrayList<Card> thisPile = getPiles().get(destPile);
    if (thisPile.isEmpty()) {
      canPlaceFlag = true;
      //thisPile.add(c);
    }
    else {
      // simply remain false.
    }
    return canPlaceFlag;
  }


}
