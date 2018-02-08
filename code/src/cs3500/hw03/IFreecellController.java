package cs3500.hw03;

import java.util.List;

import cs3500.hw02.FreecellOperations;

/**
 * an interface to abstract the behavior of a controller
 * @param <Card> parametrized over a Card
 */
public interface IFreecellController<Card> {

  /**
   * Begins a new Freecell games with the given parameters.
   *
   * @param deck        the given deck.
   * @param model       the model that will be controlled.
   * @param numCascades the starting number of cascade piles.
   * @param numOpens    the starting number of open piles
   * @param shuffle     determines if the deck has been shuffled or not
   */
  void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                int numOpens, boolean shuffle);


  String getOutput();
}
