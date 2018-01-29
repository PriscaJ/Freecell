package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

public abstract class Pile {
  public final ArrayList<ArrayList<Card>> pile;
  public final int numPile;

  public Pile(ArrayList<ArrayList<Card>> pile, int numPile) {
    this.pile = pile;
    this.numPile = numPile;
  }
}
