package cs3500.hw02;

import java.util.ArrayList;


/**
 * Class to abstract the behavior of piles in Freecell.
 */
public abstract class Pile {
  // public to be accessed by the Model
  private final PileType id;
  private final String idName;
  // not final because they should have the ability to be mutable
  private ArrayList<ArrayList<Card>> pile;
  private int numPile;

  /**
   * Base constructor for piles in freecell.
   *
   * @param id      the Piletype identifier.
   * @param pile    The piles in use for the game.
   * @param numPile the number of piles for the type of pile in the game.
   */
  public Pile(PileType id, String idName, ArrayList<ArrayList<Card>> pile, int numPile) {
    this.id = id;
    this.idName = idName;
    this.pile = pile;
    this.numPile = numPile;

  }

  // unsure about making this static, todo: take out static maybe?

  /**
   * The state of the cards in the game.
   *
   * @return String of the cards and their position in the game.
   */
  public String gameStateHelp() {
    String workString = "";
    //String idName = id.pt;
    for (int i = 0; i < pile.size(); i++) {
      workString = workString + idName + (i + 1) + ":";
      if (pile.get(i).size() == 0) {
        // enter and do the next pile
        workString = workString + "\n";
      }
      else {
        for (Card c : pile.get(i)) {
          // is this the last card in the pile?
          if (c.equals(pile.get(i).get(pile.get(i).size() - 1))) {
            workString = workString + " " + c.toString() + "\n";
          }
          else {
            workString = workString + " " + c.toString() + ",";
          }
        }
      }
    }
    // workString = workString.substring(0, workString.length() - 1);
    return workString;
  }

  /**
   * A helper to determine if a card can be moved.
   */
  public void moveHelp(ArrayList<ArrayList<Card>> allPiles, int fromPile, int pickCard,
                       PileType goingTo, int goingPile) {

    Card movingCard = allPiles.get(fromPile).get(pickCard);
    if (!canTake(movingCard, allPiles, fromPile ) || !canPlace(movingCard, goingTo, goingPile)) {
      throw new IllegalArgumentException("Can't place card");
    }
    else {
      // take the cards with the neccessary
      // goingTo.pile.canPlace(movingCard, goingTo, goingPile);
    }
  }

  /**
   * Is it possible to take a card from this pile. i.e. last card in pile
   *
   * @return boolean if it is the last card in the pile.
   */
  private boolean canTake(Card c, ArrayList<ArrayList<Card>> allPiles, int fromPile) {
    return c.equals(allPiles.get(fromPile).get(allPiles.get(fromPile).size() - 1));
  }

  /**
   * Can a card be placed in this pile.
   *
   * @param c        The card that will be placed.
   * @param pt       The type of pile it is going to.
   * @param destPile The exact pile it's going to.
   */
  public abstract boolean canPlace(Card c, PileType pt, int destPile);

  // getters for the private fields
  public ArrayList<ArrayList<Card>> getPiles()  {
    return pile;
  }

  public int getPileNum() {
    return numPile;
  }

  // setter for the private fields of a pile
  public void setPiles(ArrayList<ArrayList<Card>> piles) {
    pile = piles;
  }

  public void setPileNum(int pileIndex) {
    numPile = pileIndex;
  }


}
