package piety

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test

import javax.imageio.ImageIO
import java.io.File
import java.io.ByteArrayOutputStream
import java.awt.Color

class IntegrationTest extends AssertionsForJUnit {

  @Test def testProg1() = {
    val prog1: Program = new Program(ImageIO.read(new File(
      "src" + File.separator + "test" + File.separator + "resources" + File.separator + "hello_world.gif")), 11)
    
    val stream = new ByteArrayOutputStream()
    Console.withOut(stream) {
      Interpreter.execute(prog1)
      assertEquals("Hello, world!", stream.toString().trim())
    }
  }
  
  @Test def testProg2() = {
    var prog2: Program = new Program(ImageIO.read(new File(
      "src" + File.separator + "test" + File.separator + "resources" + File.separator + "hello_world2.gif")), 1)
    
    val stream = new ByteArrayOutputStream()
    Console.withOut(stream) {
      Interpreter.execute(prog2)
      assertEquals("Hello, world!", stream.toString().trim())
    }
  }
}