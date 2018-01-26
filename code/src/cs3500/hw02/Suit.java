package cs3500.hw02;

/**
 * An enum for a card-suit 
 */
public enum Suit {
  // suit enums
  clubs ("♣"), diamonds ("♦"), hearts ("♥"), spades ("♠");

  // constructor for the enum
  private final String suitVal;
  Suit(String suitVal) {
    this.suitVal = suitVal;
  }

  public String toString() {
    return this.suitVal;
  }
}
