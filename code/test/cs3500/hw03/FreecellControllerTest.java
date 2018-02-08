package cs3500.hw03;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;

import static org.junit.Assert.*;

public class FreecellControllerTest {

  IFreecellController fc1;
  FreecellOperations<Card> model;

  @Before
  void initData() {
    //FreecellController fc1 = new FreecellController()

    OutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    StringReader sr = new StringReader("");

    fc1 = new FreecellController(sr, ps);
    model = new FreecellModel();

  }


  @Test(expected = IllegalStateException.class)
  public void nullReadableTest() {
    OutputStream out = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(out);
    FreecellController cont = new FreecellController(null, ps);
  }

  @Test(expected = IllegalStateException.class)
  public void nullAppendableTest() {
    StringReader sr = new StringReader("");
    FreecellController fcont = new FreecellController(sr, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullDeckTest() {
    fc1.playGame(null, model, 4, 3, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullModelTest() {
    fc1.playGame(model.getDeck(), null, 4, 3, false);
  }

  @Test
  public void falseStartTest() {
    fc1.playGame(model.getDeck(), model, 2, 3, false);
    assertEquals("Could not start game.", fc1.getOutput());
  }

  @Test
  public void testInvalidIndex() {

  }

  @Test
  public void testPlayGamePressQ() {

  }

  @Test
  public void testWrongSpotQ() {

  }

  public void testBadMove() {

  }
}