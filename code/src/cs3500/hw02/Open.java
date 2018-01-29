package cs3500.hw02;

import java.util.ArrayList;

/**
 * Represents the open pile(s) in a game of FreeCell.
 */
public class Open extends Pile {


  public Open() {
    super("O", new ArrayList<ArrayList<Card>>(), 0);
  }
}
