package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;

/**
 * Sets up the factory to produce different model creations
 */
public class FreecellModelCreator {

  public enum GameType {
    SINGLEMOVE("Single Move"), MULTIMOVE("Multi move");

    GameType(String game) {
      // constructor for Gametype
    }

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
  }
}
