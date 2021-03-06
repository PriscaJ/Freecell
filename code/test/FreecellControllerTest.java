

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;
import cs3500.hw03.FreecellController;
import cs3500.hw03.IFreecellController;

import static org.junit.Assert.assertEquals;

public class FreecellControllerTest {

  IFreecellController fc1;
  FreecellOperations<Card> model;

  /**
   * Method that initializes test data.
   */
  @Before
  public void initData() {
    //FreecellController fc1 = new FreecellController()

    OutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    StringReader sr = new StringReader("");

    fc1 = new FreecellController(sr, ps);
    model = new FreecellModel();

  }


  @Test(expected = IllegalArgumentException.class)
  public void nullReadableTest() {
    OutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    FreecellController cont = new FreecellController(null, ps);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullAppendableTest() {
    StringReader sr = new StringReader("");
    FreecellController fcont = new FreecellController(sr, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullDeckTest() throws IOException {
    fc1.playGame(null, model, 4, 3, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullModelTest() throws IOException {
    fc1.playGame(model.getDeck(), null, 4, 3, false);
  }

  @Test
  public void falseStartTest() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    StringReader sr = new StringReader("");

    FreecellController fc = new FreecellController(sr, ps);
    fc.playGame(model.getDeck(), model, 2, 3, false);
    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void testInvalidIndex() throws IOException {

    String input = "C1 R F2";
    OutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    StringReader sr = new StringReader(input);

    FreecellModel model = new FreecellModel();
    List<Card> d = model.getDeck();

    FreecellController fc = new FreecellController(sr, ps);
    fc.playGame(d, model, 5, 2, false);

    assertEquals("Re-enter Index\nRe-enter Index\n", out.toString());

  }

  @Test
  public void testPlayGamePressQ() throws IOException {
    String input = "Q";
    OutputStream out = new ByteArrayOutputStream();
    StringReader sr = new StringReader(input);
    PrintStream ps = new PrintStream(out);

    FreecellModel model = new FreecellModel();
    List<Card> d = model.getDeck();

    FreecellController fc = new FreecellController(sr, ps);
    fc.playGame(d, model, 4, 1, false);

    assertEquals("Game quit prematurely.", out.toString());

    String input2 = "C4 Q O1";
    StringReader sr2 = new StringReader(input2);
    FreecellController fc2 = new FreecellController(sr2, ps);
    fc2.playGame(d, model, 4, 1, false);
    assertEquals("Game quit prematurely.", out.toString());

    String input3 = "C4 3 q1";
    StringReader sr3 = new StringReader(input2);
    FreecellController fc3 = new FreecellController(sr2, ps);
    fc2.playGame(d, model, 4, 1, false);
    assertEquals("Game quit prematurely.", out.toString());
  }

  @Test
  public void testBadMove() throws IOException {
    String input = "";
    OutputStream out2 = new ByteArrayOutputStream();
    StringReader sr = new StringReader(input);
    PrintStream ps = new PrintStream(out2);

    FreecellModel model = new FreecellModel();
    List<Card> d = model.getDeck();

    FreecellController fc = new FreecellController(sr, ps);
    fc.playGame(d, model, 4, 1, false);
    model.startGame(model.getDeck(), 6, 4, false);
    model.move(PileType.CASCADE, 3, 8, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 3, 7, PileType.OPEN, 0);
    // assertEquals("Invalid move. Try again.". );

  }
}