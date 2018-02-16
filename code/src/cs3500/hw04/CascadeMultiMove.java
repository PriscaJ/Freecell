package cs3500.hw04;

import java.util.ArrayList;

import cs3500.hw02.Card;
import cs3500.hw02.Cascade;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;

public class CascadeMultiMove extends Cascade {



  @Override
  public boolean canPlace(Card c, int destPile) {
    return false;
  }

  // check if it is a build or a single move.
  // i.e. moving the last card in the pile or further down in the pile
  private boolean isBuild() {
    return true;
  }

  // check if it is a well formed-build
  // meaning The cards in the "build" are alternating colors
  // and The cards in the "build" descending by 1
  private boolean validBuild() {
    return true;
  }

}
