
import org.junit.Test;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw04.FreecellModelCreator;
import cs3500.hw04.FreecellMultiMoveModel;

import static org.junit.Assert.*;

public class FreecellMultiMoveModelTest {
  FreecellModelCreator modelCreator;
  FreecellOperations<Card> singleModel;
  FreecellOperations<Card> multiModel;

  public void initData() {
    modelCreator = new FreecellModelCreator();
    singleModel = new FreecellModel();
    multiModel = new FreecellMultiMoveModel();
  }

  @Test
  public void invalidBuild() {

  }

  @Test
  public void correctModel() {
    this.initData();
    FreecellOperations<Card> testSingleModel =
            modelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE);
    FreecellOperations<Card> testMultiModel =
            modelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);

    assertEquals(new FreecellModel(), testSingleModel);
    assertEquals(new FreecellMultiMoveModel(), testMultiModel);
  }

  @Test
  public void sameOutputModel() {
    this.initData();
    multiModel.startGame(multiModel.getDeck(), 4, 3, false);
    singleModel.startGame(singleModel.getDeck(), 4, 3, false);
    assertEquals(multiModel.getGameState(), singleModel.getGameState());
  }

  @Test
  public void notEnoughSpaces() {
    this.initData();
    multiModel.startGame(multiModel.getDeck(), 6, 10, false);



  }

  @Test
  public void goodMultiMove() {

  }

  @Test
  public void singleInMultiMove() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void multiInSingleMove() {

  }

  @Test
  public void controlMulti() {

  }

  @Test
  public void emptyCascadeMove() {
    //multiModel.startGame(multiModel.getDeck(), );
  }

  @Test
  public void emptyOpenMove() {

  }


}