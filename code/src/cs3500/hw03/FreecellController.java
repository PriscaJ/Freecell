package cs3500.hw03;

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

    model.startGame(deck, numCascades, numOpens, shuffle);
    Scanner in = new Scanner(this.rd);

    // the three steps that must be passed to run the game
    transmitState(model.getGameState(), ap);
    readInput();
    wonGame();

  }

  private void transmitState(String gameState, Appendable a) {

  }

  private void readInput() {

  }

  private void wonGame() {

  }


}
