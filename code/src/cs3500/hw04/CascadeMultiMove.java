package cs3500.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.Cascade;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;

public class CascadeMultiMove extends Cascade {



  @Override
  public boolean canPlace(Card c, int destPile) {
    return false;
  }

  @Override
  public boolean canTake(Card c, ArrayList<ArrayList<Card>> allPiles, int fromPile, int index) {
    List<Card> build = null;

    if (index > getPiles().get(fromPile).size() - 1) {
      throw new IllegalArgumentException("Index out of bounds");
    }
    //check if it is the last card in the pile to make a build
    if (c.equals(getPiles().get(fromPile).get(getPiles().get(fromPile).size() - 1))) {
      // single card build
      build.add(c);

      //super.canTake(c, allPiles, fromPile);
    }
    else if (getPiles().get(fromPile).contains(c)){
      // make a build with the sublist of cards from that card
      build.addAll(getPiles().get(fromPile).subList(index, getPiles().get(fromPile).size() - 1));
    }

    validBuild(build);
    build.size() < intermediateHelp()


    return false;
  }

  private boolean checkCard(int pile, int cardIndex) {
    // where does the build start?
    Card c = getPiles().get(pile).get(cardIndex);
    isBuild(c, pile, cardIndex);

    // is there

    // is the size of the build <= the # of intermediate slots
    return true;

  }

  // check if it is a build or a single move.
  // i.e. moving the last card in the pile or further down in the pile
  private boolean isBuild(Card c, int fromPile, int index) {
    if (index > getPiles().get(fromPile).size() - 1) {
      throw new IllegalArgumentException("Index out of bounds");
    }
    else if (c.equals(getPiles().get(fromPile).size() - 1)) {
      return false;
    }
    return true;
  }

  // check if it is a well formed-build
  // meaning The cards in the "build" are alternating colors
  // and The cards in the "build" descending by 1
  private boolean validBuild(List<Card> build) {
    // is the size of the build less than the amount of intermediate steps
    Card holdCard = build.get(0);
    boolean answer = false;
    for (int i = 1; i < build.size() - 1; i++) {
      if (super.canPlace(holdCard, build.get(i))) {
        holdCard = build.get(i);
        answer = true;
      }
    }
    if (build.size() <)
    return answer;
  }

}
