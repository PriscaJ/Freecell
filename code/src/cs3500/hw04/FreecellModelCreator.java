package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;

/**
 * Sets up the factory to produce different model creations.
 */
public class FreecellModelCreator {

  /**
   * Creates the given game to support multimove or single move.
   *
   * @param type A given Gametype.
   * @return a FreecellOperations() for the game to implement
   */
  public static FreecellOperations<Card> create(GameType type) {
    FreecellOperations<Card> modelType = null;
    if (type.equals(GameType.SINGLEMOVE)) {
      modelType = new FreecellModel();
    }
    else if (type.equals(GameType.MULTIMOVE)) {
      modelType = new FreecellMultiMoveModel();
    }
    return modelType;
  }

  public enum GameType {
    SINGLEMOVE, MULTIMOVE;

    GameType() {
      // constructor for Gametype
    }
  }
}

