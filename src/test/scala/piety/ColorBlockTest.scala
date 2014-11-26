package piety

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test
import org.junit.Before

import javax.imageio.ImageIO
import java.io.File

class ColorBlockTest extends AssertionsForJUnit {

  var prog: Program = _

  @Before def initialize() = {
    prog = new Program(ImageIO.read(new File(
      "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test1.gif")), 1)
  }

  @Test def testExtremes() = {
    /*
     * RBR
     * BBR
     * RRR
     */
    val codelUpperLeft = prog.codels(0)(0)
    val block1 = codelUpperLeft.getParent()
    // Test extremes
    assertEquals(codelUpperLeft, block1.leftLeft)
    assertEquals(codelUpperLeft, block1.leftRight)
    assertEquals(codelUpperLeft, block1.downLeft)
    assertEquals(codelUpperLeft, block1.downRight)
    assertEquals(codelUpperLeft, block1.rightLeft)
    assertEquals(codelUpperLeft, block1.rightRight)
    assertEquals(codelUpperLeft, block1.upLeft)
    assertEquals(codelUpperLeft, block1.upRight)

    val codelLowerLeft = prog.codels(2)(0)
    val codelLowerRight = prog.codels(2)(2)
    val codelUpperRight = prog.codels(0)(2)
    // Test that the codels are in the same color block
    assertEquals(codelLowerLeft.getParent(), codelLowerRight.getParent())
    assertEquals(codelLowerLeft.getParent(), codelUpperRight.getParent())
    val block2 = codelLowerLeft.getParent()
    assertNotEquals(block1, block2)
    // Test extremes
    assertEquals(codelLowerLeft, block2.leftLeft)
    assertEquals(codelLowerLeft, block2.leftRight)
    assertEquals(codelLowerRight, block2.downLeft)
    assertEquals(codelLowerLeft, block2.downRight)
    assertEquals(codelUpperRight, block2.rightLeft)
    assertEquals(codelLowerRight, block2.rightRight)
    assertEquals(codelUpperRight, block2.upLeft)
    assertEquals(codelUpperRight, block2.upRight)
  }
}