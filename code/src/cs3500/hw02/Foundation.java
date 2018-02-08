package cs3500.hw02;

import java.util.ArrayList;

/**
 * Represents the 4 foundation Piles in a FreeCell game.
 */
public class Foundation extends Pile {
  //int numPile;
  public Foundation() {
    super(PileType.FOUNDATION, "F", new ArrayList<ArrayList<Card>>(), 4);
    for (int i= 0; i < this.getPileNum(); i++) {
      this.getPiles().add(new ArrayList<Card>());
    }
  }

  /**
   * Checks if the foundation piles contains all the cards.
   *
   * @return Boolean if the all cards are in the foundations.
   */
  public boolean allCards() {
    ArrayList<Card> seen = new ArrayList<Card>();
    for (ArrayList<Card> suitPile : this.getPiles()) {
      for (Card c : suitPile) {
        if (seen.contains(c)) {
          throw new RuntimeException("duplicate card");
        } else {
          seen.add(c);
        }
      }
    }
    return seen.size() == 52;
  }

  @Override
  public boolean canPlace(Card c, PileType pt, int destPile) {
    boolean canPlaceFlag = false;
    Card currLastCard = this.getPiles().get(destPile).get(
            this.getPiles().get(destPile).size() - 1);

    if (c.suit.equals(currLastCard.suit)
            && currLastCard.value.getNumVal() == (c.value.getNumVal() - 1)) {
      this.getPiles().get(destPile).add(c);
      canPlaceFlag = true;
    }
    else {
      throw new IllegalArgumentException("Cannot place card");
    }
    return canPlaceFlag;
  }
}
