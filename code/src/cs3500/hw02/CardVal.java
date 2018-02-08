package cs3500.hw02;

/**
 * An enum for the card's values with A, 1 -10, J, Q, K.
 */
public enum CardVal {
  // the values in each card
  ace(1, "A"), two(2, "2"), three(3, "3"), four(4, "4"), five(5, "5"), six(6, "6"), seven(7, "7"),
  eight(8, "8"), nine(9, "9"), ten(10, "10"), jack(11, "J"), queen(12, "Q"), king(13, "K");


  private final int value;
  private final String val;

  // the constructor or the values in a card
  CardVal(int value, String val) {
    this.value = value;
    this.val = val;
  }

  /**
   * The string value for this card value.
   *
   * @return String of the card value.
   */
  public String toString() {
    return this.val;
  }

  public int getNumVal() {
    return value;
  }


}
