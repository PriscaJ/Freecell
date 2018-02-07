package cs3500.hw03;

import java.io.StringReader;
import java.util.List;

import cs3500.hw02.FreecellOperations;

public class FreecellController implements IFreecellController {
  private final Readable rd;
  private final Appendable ap;

  public FreecellController(Readable rd, Appendable ap) {

    if (rd == null || ap == null) {
      throw new IllegalStateException("Readable and/or Appendable can not be null");
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(List deck, FreecellOperations model,
                       int numCascades, int numOpens, boolean shuffle) {






  }
}
