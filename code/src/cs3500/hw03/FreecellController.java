package cs3500.hw03;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;


/**
 * Class representing the controller for freecell
 */
public class FreecellController implements IFreecellController {
  private final Readable rd;
  private final Appendable ap;

  public FreecellController(Readable rd, Appendable ap) {

    if (rd == null || ap == null) {
      throw new IllegalStateException("Readable and/or Appendable can not be null");
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(List deck, FreecellOperations model,
                       int numCascades, int numOpens, boolean shuffle) {
    // check if the inputs ae null (not depending on the model, must chack on its own
    if (deck == null || model == null) {
      throw new IllegalArgumentException("model and or deck can't be null");
    }
    // are there a valid amount of cards in the piles
    if (numCascades < 4 || numOpens < 1) {
      throw new IllegalArgumentException("Not enough piles");
    }
    if (shuffle) {
      Collections.shuffle(deck);
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    }
    catch (IllegalArgumentException e) {
      appendIOCatch("Could not start game.");
    }
    //Scanner in = new Scanner(this.rd);

    // the three steps that must be passed to run the game
    transmitState(model.getGameState());
    readInput(model);
    wonGame(model);
  }

  // Appends the gameState from the model.
  private void transmitState(String gameState) {
    appendIOCatch(gameState);
  }

  // From the readable validates the inputs.
  private void readInput(FreecellOperations model) {
    if(model.isGameOver()) {
      wonGame(model);
    }

    Scanner in = new Scanner(this.rd);

    // tokens to look for;
    /**String src = null;
    int cardIndex = -1;
    String dest = null; **/

    PileType srcPileLetter = null;
    int srcPileNum = -1;
    int cardIndex = -1;
    PileType destPileLetter = null;
    int destPileNum = -1;

    while (in.hasNext()) {
      String curr = in.next();

      if (curr.equals("Q") || curr.equals("q")) {
        appendIOCatch("Game quit prematurely.");
      }

      // check which token is needed
      if (srcPileLetter == null && srcPileNum == -1) {
        if (curr.length() == 1) {
          srcPileLetter = this.validPile(curr.substring(0, 1));
        }
        else {
          srcPileLetter = this.validPile(curr.substring(0, 1));
          // todo: determine which pile it came from or does the model take care of that?
          srcPileNum = this.validIndex(curr.substring(1));
        }
      }
      else if (cardIndex == -1) {
        cardIndex = this.validIndex(curr);
      }

      else if (destPileLetter == null && destPileNum == -1) {
        if (curr.length() == 1) {
          destPileLetter = this.validPile(curr.substring(0, 1));
        }
        else {
          destPileLetter = this.validPile(curr.substring(0, 1));
          destPileNum = this.validIndex(curr.substring(1));
        }
      }
      // check if it is valid
      else {
        try {
          model.move(srcPileLetter, srcPileNum, cardIndex, destPileLetter, destPileNum);
          // reset for next move
          srcPileLetter = null;
          srcPileNum = -1;
          cardIndex = -1;
          destPileLetter = null;
          destPileNum = -1;
        }
        catch (IllegalArgumentException e) {
          appendIOCatch("Could not start game.");
          // reset for another move
          srcPileLetter = null;
          srcPileNum = -1;
          cardIndex = -1;
          destPileLetter = null;
          destPileNum = -1;
        }
      }

    }
  }

  // checks if the index is the correct in the pile
  private int validIndex(String index) {
    int friendlyIndex = Integer.parseInt(index) + 1;
    if (friendlyIndex < 1) {
      this.appendIOCatch("Re-enter Index\n");
      return -1;
    }
    else {
      // todo: check if there are enough indices or no?
      return friendlyIndex;

    }
  }

  // verifies the source or the destination pile as valid input
  private PileType validPile(String pileLetter) {
    PileType pickPile = null;

    switch (pileLetter) {
      case "C":
        pickPile = PileType.CASCADE;
        break;
      case "F":
        pickPile = PileType.FOUNDATION;
        break;
      case "O":
        pickPile = PileType.OPEN;
        break;
      default:
        appendIOCatch("Re-enter Pile Type\n");
        break;
    }
    return pickPile;
  }

  // determines if the game is complete
  private void wonGame(FreecellOperations model) {
    if (!model.isGameOver()) {
      appendIOCatch("Game not over...");
    }
    else {
      appendIOCatch("\nGame over");
    }
  }

  // sends out the try catch condition
  private void appendIOCatch(String message) {
    try {
      this.ap.append(message);
    } catch (IOException io) {
      // do nothing
    }
  }

}