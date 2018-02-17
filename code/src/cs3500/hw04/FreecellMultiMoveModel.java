package cs3500.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;


/**
 * Class representing the model to handle multiple moves at once, moving a "stack" of cards
 */
public class FreecellMultiMoveModel extends FreecellModel implements FreecellOperations<Card> {

  public FreecellMultiMoveModel() {
    super();
    this.cascadePiles = new CascadeMultiMove();
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) throws IllegalArgumentException {

    /**
     * 1. Moving the bottom card in the "build" is a valid move
     * 2. The cards in the "build" are alternating colors
     * 3. The cards in the "build" descending by 1
     * 4. The move can be completed as a series of individual card moves using empty cascade/open piles.
     */
    if ((source == null) || (destination == null)) {
      throw new IllegalArgumentException("source or destination can not be null");
    }

    // the variable from where the card is moving based on what piletype it is
    Card choosenCard = null;
    // capabilities of  one card builds
    List<Card> build = new ArrayList<Card>();
    Pile srcPile = null;
    Pile destPile = null;

    if (source.equals(PileType.OPEN)) {
      choosenCard = openPiles.getPiles().get(pileNumber).get(cardIndex);
      build.add(choosenCard);
      srcPile = openPiles;

      if (!srcPile.canTake(choosenCard, srcPile.getPiles(), pileNumber)) {
        throw new IllegalArgumentException("Can't place card");
      }
    }
    // builds can only be made in cascade piles (sublist of a casacade pile)
    else if (source.equals(PileType.CASCADE)) {
      choosenCard = cascadePiles.getPiles().get(pileNumber).get(cardIndex);

      //  todo: call canTake which will override its parent so that it can check the build


      if (cascadePiles.checkCard(pileNumber, cardIndex)) {
        build = cascadePiles.getBuild();
        if (build.size() > intermediateSlots()) {
          throw new IllegalArgumentException("Build is too large");
        }
      }
      srcPile = cascadePiles;
    }

    // check if it is valid to take the card
    if (!srcPile.canTake(choosenCard, srcPile.getPiles(), pileNumber, cardIndex)) {
      throw new IllegalArgumentException("Can't place card");
    }

    // see where the card is going to
    if (destination.equals(PileType.CASCADE)) {
      // todo: check if the choosen card (last card in pile) is able to be placed
      // todo: then move the entire build
      cascadePiles.canPlace(choosenCard, destPileNumber);

    }
    else if (destination.equals(PileType.OPEN)) {
      // todo: check if the build is only one card and take that one card to place it as normal
      openPiles.canPlace(choosenCard, destPileNumber);
      destPile = openPiles;
    }
    else if (destination.equals(PileType.FOUNDATION)) {
      // todo: check if the build is only one card and take that one card to place it as normal
      foundationPiles.canPlace(choosenCard, destPileNumber);
      destPile = foundationPiles;
    }

    // going to a destination is mostly the same
    // besides in the cascades where the entire build can move




  }

  // retrieves the number of open slots((N+1)*2^K)
  private Double intermediateSlots() {
    double countOpen = 0;
    double countCas = 0;
    double slots = 0;

    countOpen = openPiles.intermediateHelp();
    countCas = cascadePiles.intermediateHelp();

    slots = (countOpen + 1) * (Math.pow(2.0, countCas));
    return slots;
  }

}
