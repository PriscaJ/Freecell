package cs3500.hw02;

/**
 * An enum for the card's values with A, 1 -10, J, Q, K
 */
public enum CardVal {
  // the values in each card
  ace("A"), two("2"), three("3"), four("4"), five("5"), six("6"),
  seven("7"), eight("8"), nine("9"), ten("10"), jack ("J"), queen ("Q"),  king ("K");


  private final String val;
  // the constructor or the values in a card
  CardVal(String val) {
    this.val = val;
  }

  /**
   * The string value for this card value
   * @return String of the card value
   */
  public String toString() {
   return this.val;
  }



}
