package cs3500.hw03;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import cs3500.hw02.FreecellOperations;


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
    Scanner in = new Scanner(this.rd);

    // the three steps that must be passed to run the game
    transmitState(model.getGameState());
    readInput(model, numCascades, numOpens, shuffle);
    wonGame(model);

  }


  private void transmitState(String gameState) {
    appendIOCatch(gameState);
  }

  private void readInput(FreecellOperations model,
                         int numCascades, int numOpens, boolean shuffle) {
    if(model.isGameOver()) {
      wonGame(model);
    }

    Scanner in = new Scanner(this.rd);

    // tokens to look for;
    String src = null;
    int cardIndex = -1;
    String dest = null;

    /**String srcPileLetter = null;
    int srcPileNum = -1;
    int cardIndex = -1;
    String destPileLetter = null;
    int destPileNum = -1; **/

    while (in.hasNext()) {
      String curr = in.next();
      // check what token we need and if it's the src or dest split
      if (src == null) {
        splitToken(curr);
      }
      else if (cardIndex == -1) {
        validIndex(curr);
      }

      else if (dest == null) {
        splitToken(curr);
      }

      // check if it is valid

    }
  }

  private void splitToken(String token) {

  }

  // checks if the index is the correct in the pile
  private int validIndex(String index) {
    try {
      if (Integer.parseInt(index) >= 1) {
        return Integer.parseInt(index);
      }
      else {
        this.appendIOCatch("Re-enter Index\n");
      }
    } catch (NumberFormatException num) {
      this.appendIOCatch("Re-enter Index\n");
    }
    return -1;
  }

  // verifies the source or the destination pile as valid input
  /**private PileType validPile(String PileLetter) {
    Character isPile = pile.charAt(0);
    PileType pickPile = null;

    switch (isPile) {
      case 'C':
        pickPile = PileType.CASCADE;
        break;
      case 'F':
        pickPile = PileType.FOUNDATION;
        break;
      case 'O':
        pickPile = PileType.OPEN;
        break;
      case 'Q':
        tryCatch("Game quit prematurely.");
        break;
      case 'q':
        this.tryCatch("Game quit prematurely.");
        break;
      default:
        this.tryCatch("Re-enter Pile Type\n");
        break;
    }
    return pickPile;
  } **/

  private void wonGame(FreecellOperations model) {
    if (!model.isGameOver()) {
      appendIOCatch("Game not over...");
    }
    else {
      appendIOCatch("\nGame over");
    }
  }

  public String getOutput() {
    return ap.toString();
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
