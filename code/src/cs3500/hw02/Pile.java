package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

public abstract class Pile {
  // public to be accessed by the Model
  public final String id;
  // not final because they should have the ability to be mutable
  public ArrayList<ArrayList<Card>> pile;
  public int numPile;

  public Pile(String id, ArrayList<ArrayList<Card>> pile, int numPile) {
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
    for (ArrayList<Card> cardPile: pile) {
      workString = workString + id + pile.indexOf(cardPile) + ":";
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
}
