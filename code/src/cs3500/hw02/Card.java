package cs3500.hw02;

/**
 * Represents a Card with suit-values.
 */
public class Card {
  Suit suit;
  CardVal value;

  /**
   * Constructor for a card.
   *
   * @param suit  The card Suit.
   * @param value The card CardVal.
   */
  public Card(Suit suit, CardVal value) {
    this.suit = suit;
    this.value = value;
  }

  /**
   * The Card representation in String form.
   *
   * @return String representation of this card
   */
  public String toString() {
    return  value.toString() + suit.toString();
  }
}
