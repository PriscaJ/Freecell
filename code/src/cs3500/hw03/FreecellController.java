package cs3500.hw03;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;


/**
 * Class representing the controller for freecell.
 */
public class FreecellController implements IFreecellController {
  private final Readable rd;
  private final Appendable ap;

  /**
   * Constructs the controller for freecell.
   * @param rd The readable to take in user input.
   * @param ap The appendale object that sends output to the user.
   */
  public FreecellController(Readable rd, Appendable ap) {

    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and/or Appendable can not be null");
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(List deck, FreecellOperations model,
                       int numCascades, int numOpens, boolean shuffle)
          throws IOException, IllegalArgumentException {
    // check if the inputs ae null (not depending on the model, must chack on its own
    if (deck == null || model == null) {
      throw new IllegalArgumentException("model and or deck can't be null");
    }
    // are there a valid amount of cards in the piles
    if (numCascades < 4 || numOpens < 1) {
      appendIOCatch("Could not start game.");
      return ;
      //throw new IllegalArgumentException("Not enough piles");
    }
    if (shuffle) {
      Collections.shuffle(deck);
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
      // the three steps that must be passed to run the game
      transmitState(model.getGameState());
      readInput(model);
      wonGame(model);
    }
    catch (IllegalArgumentException e) {
      appendIOCatch("Could not start game.");
    }
  }

  // Appends the gameState from the model.
  private void transmitState(String gameState)throws IOException {
    appendIOCatch(gameState);
  }

  // From the readable validates the inputs.
  private void readInput(FreecellOperations model) throws IOException {
    if (model.isGameOver()) {
      wonGame(model);
    }

    Scanner in = new Scanner(this.rd);

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
          if (srcPileNum == -1) {
            return;
          }
        }
      }
      else if (cardIndex == -1) {
        cardIndex = this.validIndex(curr);
        if (cardIndex == -1) {
          return;
        }
      }

      else if (destPileLetter == null && destPileNum == -1) {
        if (curr.length() == 1) {
          destPileLetter = this.validPile(curr.substring(0, 1));
        }
        else {
          destPileLetter = this.validPile(curr.substring(0, 1));
          destPileNum = this.validIndex(curr.substring(1));
          if (destPileNum == -1) {
            return;
          }
        }
      }
      // check if it is valid
      //validMove(model, srcPileLetter, srcPileNum, cardIndex, destPileLetter, destPileNum);

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
          appendIOCatch("Invalid move. Try again." + e.getMessage());
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
  private int validIndex(String index) throws IOException {
    try {
      int friendlyIndex = Integer.parseInt(index) + 1;
      if (friendlyIndex >= 1) {
        return friendlyIndex;
      }
      else {
        // when the index is out of bounds
        this.appendIOCatch("Game over.");
      }
    }
    catch (NumberFormatException num) {
      this.appendIOCatch("Re-enter Index\n");
    }
    return -1;
  }

  // verifies the source or the destination pile as valid input
  private PileType validPile(String pileLetter) throws IOException {
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
  private void wonGame(FreecellOperations model) throws IOException {
    if (model.getGameState().equals("")) {
      appendIOCatch("");
      return;
    }
    if (!model.isGameOver()) {
      appendIOCatch("Game quit prematurely.");
      return;
    }
  }

  // sends out the try catch condition
  private void appendIOCatch(String message) throws IOException {
      this.ap.append(message);
  }

}