package cs3500.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.Cascade;


public class CascadeMultiMove extends Cascade {



  @Override
  public boolean canPlace(Card c, int destPile) {
    return super.canPlace(c, destPile);
  }

  @Override
  public boolean canTake(Card c, ArrayList<ArrayList<Card>> allPiles, int fromPile, int index) {
    List<Card> build = new ArrayList<Card>();
    boolean answer;

    // if the index is valid
    if (index > getPiles().get(fromPile).size() - 1) {
      throw new IllegalArgumentException("Index out of bounds");
    }
    //check if it is the last card in the pile, single card build
    if (c.equals(getPiles().get(fromPile).get(getPiles().get(fromPile).size() - 1))) {
      // single card build
      build.add(c);

      //super.canTake(c, allPiles, fromPile);
    }
    // make a build with a sublist starting from the card to the end of the list
    else if (getPiles().get(fromPile).contains(c)){
      // make a build with the sublist of cards from that card
      build.addAll(getPiles().get(fromPile).subList(index, getPiles().get(fromPile).size() - 1));
    }

    // check the size of the build to ensure it is possible to do

    try {
      answer = validBuild(build);
    }
    catch (IllegalArgumentException e) {
      answer = false;
    }
    return answer;
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
    if (build.size() == 1) {
      answer = true;
    }
    else {
      for (int i = 1; i < build.size() - 1; i++) {
        Card temp = build.get(i);
        if (!temp.getSuit().sc.equals(holdCard.getSuit().sc)) {
          if (temp.getValue().getNumVal() == (holdCard.getValue().getNumVal() - 1)) {
            holdCard = build.get(i);
            answer = true;
          }
        }
        else {
          throw new IllegalArgumentException("Not a valid Build");
        }
      }
    }
    return answer;
  }
}
