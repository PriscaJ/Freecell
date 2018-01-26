package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

public abstract class Pile {
  ArrayList<ArrayList<Card>> pile;

  public Pile(ArrayList<ArrayList<Card>> pile) {
    this.pile = pile;
  }
}
