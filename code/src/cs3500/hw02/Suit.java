package cs3500.hw02;

/**
 * An enum for a card-suit 
 */
public enum Suit {


  // suit enums
  clubs (SuitColor.black, "♣"), diamonds (SuitColor.red, "♦"),
  hearts (SuitColor.red, "♥"), spades (SuitColor.black, "♠");


  public enum SuitColor {
    red, black;
  }
  // constructor for the enum
  public final String suitVal;
  public final SuitColor sc;

  Suit(SuitColor sc, String suitVal) {
    this.sc = sc;
    this.suitVal = suitVal;
  }

  public String toString() {
    return this.suitVal;
  }
}
