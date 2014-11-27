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
    testProg("hello_world.gif", 11, "Hello, world!")
  }
  
  @Test def testProg2() = {
    testProg("hello_world2.gif", 1, "Hello, world!")
  }
  
  @Test def testProg3() = {
    testProg("hello_world3.gif", 1, "Hello, world!")
  }
  
  @Test def testProg4() = {
    testProg("alphabet.gif", 1, "abcdefghijklmnopqrstuvwxyz")
  }
  
  @Test def testProg5() = {
    testProg("piet.gif", 1, "Piet")
  }
  
  def testProg(fileName: String, codelSize: Int, expectedOutput: String) = {
    var prog: Program = new Program(ImageIO.read(new File(
      "src" + File.separator + "test" + File.separator + "resources" + File.separator + fileName)), codelSize)
    
    val stream = new ByteArrayOutputStream()
    Console.withOut(stream) {
      Interpreter.execute(prog)
      assertEquals(expectedOutput, stream.toString().trim())
    }
  }
}