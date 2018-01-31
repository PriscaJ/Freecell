package cs3500.hw02;

import java.util.ArrayList;

/**
 * Represents the 4 foundation Piles in a FreeCell game.
 */
public class Foundation extends Pile {
  //int numPile;
  public Foundation() {
    super(PileType.FOUNDATION, new ArrayList<ArrayList<Card>>(), 4);
    //this.numPile = 4;
  }

  /**
   * Checks if the foundation piles contains all the cards.
   *
   * @return Boolean if the all cards are in the foundations.
   */
  public boolean allCards() {
    ArrayList<Card> seen = new ArrayList<Card>();
    for (ArrayList<Card> suitPile : pile) {
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
  public void canPlace(Card c, PileType pt, int destPile) {
    Card currLastCard = this.pile.get(destPile).get(this.pile.get(destPile).size() - 1);

    if (c.suit.equals(currLastCard.suit)
            && currLastCard.value.value == (c.value.value - 1)) {
      this.pile.get(destPile).add(c);
    } else {
      throw new IllegalArgumentException("Cannot place card");
    }
  }
}
