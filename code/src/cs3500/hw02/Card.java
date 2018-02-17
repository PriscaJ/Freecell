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

  /**
   * Ensures the card is valid.
   * @return boolean.
   */
  public boolean validCard() {
    return CardVal.values().equals(this.value) && Suit.values().equals(this.suit);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Card) {
      Card comp = (Card) obj;
      return comp.value.equals(this.value)
              && comp.suit.equals(this.suit);
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.value.hashCode() * 10000 + this.suit.hashCode();
  }


  // DOCUMENTATION: getters for the suit and the color
  public Suit getSuit() {
    return suit;
  }

  public CardVal getValue() {
    return value;
  }
}

