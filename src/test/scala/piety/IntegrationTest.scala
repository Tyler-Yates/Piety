package piety

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test

import javax.imageio.ImageIO
import java.io.File
import java.io.ByteArrayInputStream
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
    testProg("hello_world3.png", 1, "Hello world!")
  }
  
  @Test def testProg4() = {
    testProg("alpha_filled.png", 1, "abcdefghijklmnopqrstuvwxyz")
  }
  
  @Test def testProg5() = {
    testProg("hello_world4.png", 1, "Hello, world!")
  }
  
  @Test def testProg6() = {
    testProgWithInput("factorial.png", 10, "0", "1")
    testProgWithInput("factorial.png", 10, "1", "1")
    testProgWithInput("factorial.png", 10, "2", "2")
    testProgWithInput("factorial.png", 10, "3", "6")
    testProgWithInput("factorial.png", 10, "4", "24")
    testProgWithInput("factorial.png", 10, "5", "120")
  }
  
  @Test def testProg7() = {
    testProgWithInput("primetest.gif", 24, "2", "Y")
    testProgWithInput("primetest.gif", 24, "3", "Y")
    testProgWithInput("primetest.gif", 24, "4", "N")
    testProgWithInput("primetest.gif", 24, "5", "Y")
    testProgWithInput("primetest.gif", 24, "7817", "Y")
    testProgWithInput("primetest.gif", 24, "7819", "N")
  }
  
  @Test def testProg8() = {
    testProg("fibo.gif", 32, "0\n1\n1\n2\n3\n5\n8\n13\n21\n34\n55\n89\n144\n233\n377\n610\n987")
  }
  
  /*@Test def testProg5() = {
    testProg("piet.gif", 1, "Piet")
  }*/
  
  def testProg(fileName: String, codelSize: Int, expectedOutput: String) = {
    var prog: Program = new Program(ImageIO.read(new File(
      "src" + File.separator + "test" + File.separator + "resources" + File.separator + fileName)), codelSize)
    
    val stream = new ByteArrayOutputStream()
    Console.withOut(stream) {
      Interpreter.execute(prog)
      assertEquals(expectedOutput, stream.toString().trim())
    }
  }
  
  def testProgWithInput(fileName: String, codelSize: Int, input: String, expectedOutput: String) = {
    Console.withIn(new ByteArrayInputStream(input.getBytes())) {
      testProg(fileName, codelSize, expectedOutput)
    }
  }
}