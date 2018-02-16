package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that represents the model for the Freecell game,
 * containing the three types of piles (Open, Foundation, Cascade) to manipulate during a game.
 */
public class FreecellModel implements FreecellOperations<Card> {
  private Cascade cascadePiles;
  private Foundation foundationPiles;
  private Open openPiles;

  /**
   * Constructor for the model in freecell.
   */
  public FreecellModel() {
    this.cascadePiles = new Cascade();
    this.foundationPiles = new Foundation();
    this.openPiles = new Open();
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>();
    for (Suit s: Suit.values()) {
      for (CardVal cv: CardVal.values()) {
        Card curr = new Card(s, cv);
        deck.add(curr);
      }
    }
    return deck;
  }

  /**
   * Verifies that the deck is valid.
   * @param deck The given deck of cards.
   * @return Boolean if it is a valid deck.
   */
  private boolean isValid(List<Card> deck) {
    ArrayList<Card> seen = new ArrayList<Card>();
    if (deck.size() != 52) {
      return false;
    }
    for (Card c: deck) {
      if (seen.contains(c)) {
        throw new IllegalArgumentException("Bad Deck");
      }
      seen.add(c);
    }
    return true;
  }

  // todo: is this not reall mutating???
  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
          throws IllegalArgumentException {
    // check the deck
    if (!isValid(deck)) {
      throw new IllegalArgumentException("Bad deck");
    }
    // are there a valid amount of cards in the piles
    if (numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalArgumentException("Not enough piles");
    }
    // shuffling the cards
    if (shuffle) {
      Collections.shuffle(deck);
      this.startGame(deck, numCascadePiles, numOpenPiles, false);
    }
    // Set up the cards in the cascade and open piles
    ArrayList<ArrayList<Card>> currCasPile = new ArrayList<ArrayList<Card>>();
    cascadePiles.setPiles(currCasPile);
    // openPiles.pile = currPile;
    cascadePiles.setPileNum(numCascadePiles);
    openPiles.setPileNum(numOpenPiles);
    for (int i = 0; i < openPiles.getPileNum(); i++) {
      openPiles.getPiles().add(new ArrayList<Card>());
    }

    cascadePiles.roundRobin(deck);

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {

    if ((source == null) || (destination == null)) {
      throw new IllegalArgumentException("source or destination can not be null");
    }
    if (source.equals(PileType.FOUNDATION)) {
      throw new IllegalArgumentException("Cannot move from a foundation");
    }

    // the variable from where the card is moving based on what piletype it is
    Card movingCard = null;
    Pile srcPile = null;
    Pile destPile = null;

    // checks where it is coming from
    if (source.equals(PileType.CASCADE)) {
      movingCard = cascadePiles.getPiles().get(pileNumber).get(cardIndex);
      srcPile = cascadePiles;
    }
    else if (source.equals(PileType.OPEN)) {
      movingCard = openPiles.getPiles().get(pileNumber).get(cardIndex);
      srcPile = openPiles;
    }

    // check if it is valid to take the card
    if (!srcPile.canTake(movingCard, srcPile.getPiles(), pileNumber)) {
      throw new IllegalArgumentException("Can't place card");
    }

    // see where the card is going to
    //boolean answer;
    if (destination.equals(PileType.CASCADE)) {
      cascadePiles.canPlace(movingCard, destPileNumber);

    }
    else if (destination.equals(PileType.OPEN)) {
      openPiles.canPlace(movingCard, destPileNumber);
      destPile = openPiles;
    }
    else if (destination.equals(PileType.FOUNDATION)) {
      foundationPiles.canPlace(movingCard, destPileNumber);
      destPile = foundationPiles;
    }

    // mutate to make the move
    srcPile.getPiles().get(pileNumber).remove(cardIndex);
    destPile.getPiles().get(destPileNumber).add(movingCard);

  }

  @Override
  public boolean isGameOver() {
    // check if the foundation piles contain all the cards
    // Foundation foundPiles = new Foundation();
    return foundationPiles.allCards();
  }

  @Override
  public String getGameState() {
    String workString = "";
    String trim = "";
    if (cascadePiles.getPiles().size() == 0) {
      return workString;
    }
    else {
      if (!isGameOver()) {
        workString = foundationPiles.gameStateHelp()
                + openPiles.gameStateHelp() + cascadePiles.gameStateHelp();
        trim = workString.trim();
      }
    }
    return trim;
  }
}
