package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the cascade piles in a game of FreeCell.
 */
public class Cascade extends Pile {
  //int numPiles;

  public Cascade() {
    super(PileType.CASCADE,"C", new ArrayList<ArrayList<Card>>(), 0);
    //this.numPiles = numPiles;
  }


  /**
   * Distributes the cards in a round robin.
   *
   * @param deck the deck of cards.
   */
  void roundRobin(List<Card> deck) {

    for (int i = 0; i < this.getPileNum(); i++) {
      this.getPiles().add(new ArrayList<Card>());
    }
    int countdown = 0;
    for (Card c : deck) {
      this.getPiles().get(countdown).add(c);
      countdown = (countdown + 1) % this.getPileNum();
    }
  }

  @Override
  public boolean canPlace(Card c, PileType pt, int destPile) {
    boolean canPlaceFlag = false;
    Card currLastCard =
            this.getPiles().get(destPile).get(this.getPiles().get(destPile).size() - 1);


    if (currLastCard.suit.sc.equals(Suit.SuitColor.red)) {
      if (c.suit.sc.equals(Suit.SuitColor.black)
              && currLastCard.value.getNumVal() == (c.value.getNumVal() - 1)) {
        this.getPiles().get(destPile).add(c);
        canPlaceFlag = true;

      }
      else {
        throw new IllegalArgumentException("Cannot place card");
      }
    }
    else if (currLastCard.suit.sc.equals(Suit.SuitColor.black)) {
      if (c.suit.sc.equals(Suit.SuitColor.red)
              && currLastCard.value.getNumVal() == (c.value.getNumVal() - 1)) {
        this.getPiles().get(destPile).add(c);
        canPlaceFlag = true;
      }
      else {
        throw new IllegalArgumentException("Cannot place card");
      }
    }
    else {
      throw new IllegalArgumentException("Cannot place card");
    }
    return canPlaceFlag;
  }
}
