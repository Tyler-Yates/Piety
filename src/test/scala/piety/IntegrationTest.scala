package piety

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import org.junit.After

import javax.imageio.ImageIO
import java.io.File
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.awt.Color

class IntegrationTest extends AssertionsForJUnit {
  
  val outContent: ByteArrayOutputStream = new ByteArrayOutputStream()

  var prog1: Program = _
  var prog2: Program = _

  @Before def initialize() = {
    prog1 = new Program(ImageIO.read(new File(
      "src" + File.separator + "test" + File.separator + "resources" + File.separator + "hello_world.gif")), 11)
    prog2 = new Program(ImageIO.read(new File(
      "src" + File.separator + "test" + File.separator + "resources" + File.separator + "hello_world2.gif")), 1)
    
    System.setOut(new PrintStream(outContent));
  }
  
  @After def teardown() = {
    System.setOut(null);
  }

  @Test def testProg1() = {
    Interpreter.execute(prog1)
    assertEquals("Hello, world!", outContent.toString().trim())
  }
  
  @Test def testProg2() = {
    Interpreter.execute(prog2)
    assertEquals("Hello, world!", outContent.toString().trim())
  }
}