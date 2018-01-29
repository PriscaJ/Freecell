package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FreecellModel implements FreecellOperations<Card> {

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

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
          throws IllegalArgumentException {
    // are there a valid amount of cards in the piles
    if (numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalArgumentException("Not enough piles");
    }

    if (shuffle) {
      Collections.shuffle(deck);
    }


    // Cascade cascadePiles;
    // distribute in a round robin fashion in the cascade class
    ArrayList<ArrayList<Card>> currPile = new ArrayList<ArrayList<Card>>();
    Cascade CasPiles = new Cascade(currPile, numCascadePiles);
    CasPiles.roundRobin(deck);



  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {
    return null;
  }
}
