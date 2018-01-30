package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the cascade piles in a game of FreeCell
 */
public class Cascade extends Pile {
  //int numPiles;

  public Cascade() {
    super(PileType.CASCADE, new ArrayList<ArrayList<Card>>(), 0);
    //this.numPiles = numPiles;
  }


  public void roundRobin(List<Card> deck) {
    for (int i = 0; i < numPile; i++) {
      pile.add(new ArrayList<Card>());
    }
      int countdown = 0;
      for (Card c : deck) {
        pile.get(countdown).add(c);
        countdown = (countdown + 1) % numPile;
    }
  }

  @Override
  public void canPlace(Card c, PileType pt, int destPile) {
    Card currLastCard = this.pile.get(destPile).get(this.pile.get(destPile).size() - 1);

    switch (currLastCard.suit.sc) {
      case red:
        if (c.suit.sc.equals(Suit.SuitColor.black)
                && currLastCard.value.value == (c.value.value - 1)) {
          this.pile.get(destPile).add(c);
        }
      case black:
        if (c.suit.sc.equals(Suit.SuitColor.red)
                && currLastCard.value.value == (c.value.value - 1)) {
          this.pile.get(destPile).add(c);
        }
        default:
          throw new IllegalArgumentException("Cannot place card");
    }
  }
}
