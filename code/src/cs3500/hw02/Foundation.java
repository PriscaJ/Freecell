package cs3500.hw02;

import java.util.ArrayList;

/**
 * Represents the 4 foundation Piles in a FreeCell game.
 */
public class Foundation extends Pile {
  //int numPile;
  public Foundation(ArrayList<ArrayList<Card>> pile, int numPile) {
    super("F", pile, 4);
    //this.numPile = 4;
  }
}
