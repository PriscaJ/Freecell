package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

public abstract class Pile {
  // public to be accessed by the Model
  public final PileType id;
  // not final because they should have the ability to be mutable
  public ArrayList<ArrayList<Card>> pile;
  public int numPile;

  public Pile(PileType id, ArrayList<ArrayList<Card>> pile, int numPile) {
    this.id = id;
    this.pile = pile;
    this.numPile = numPile;
  }

  // unsure about making this static, todo: take out static maybe?

  /**
   * The state of the cards in the game.
   * @return String of the cards and their position in the game.
   */
  public String gameStateHelp() {
    String workString = "";
    String idName = id.name().substring(0, 0);
    for (ArrayList<Card> cardPile: pile) {
      workString = workString + idName + pile.indexOf(cardPile) + ":";
      if (cardPile.size() == 0) {
        // enter and do the next pile
        workString = workString + "\n";
      }
      else {
        for (Card c: cardPile) {
          // is this the last card in the pile?
          if (c.equals(cardPile.get(cardPile.size() - 1))) {
            workString = workString + " " + c.toString() + "\n";
          }
          else {
            workString = workString + " " + c.toString() + ",";
          }
        }
      }
    }
    return workString;
  }

  /**
   * A helper to determine if a card can be moved.
   */
  public void moveHelp(ArrayList<ArrayList<Card>> allPiles, int fromPile, int pickCard,
                       PileType goingTo, int goingPile) {

    Card movingCard = allPiles.get(fromPile).get(pickCard);
    if (!canTake(movingCard, allPiles, fromPile)) {
      throw new IllegalArgumentException("Must choose last card in Pile");
    }
    else {
      canPlace(movingCard, goingTo, goingPile);
    }
  }

  /**
   * Is it possible to take a card from this pile.
   * i.e. last card in pile
   * @return boolean if it is the last card in the pile.
   */
  private boolean canTake(Card c, ArrayList<ArrayList<Card>> allPiles, int fromPile) {
    return c.equals(allPiles.get(fromPile).get(allPiles.get(fromPile).size() - 1));
  }

  /**
   * Can a card be placed in this pile?
   * @return boolean can the card be placed in the pile
   */
  public abstract void canPlace(Card c, PileType pt, int destPile);

}
