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
    val codelUpperLeft = prog.codels(0)(0)
    val block1 = codelUpperLeft.getParent()

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
    assertEquals(codelLowerLeft.getParent(), codelLowerRight.getParent())
    assertEquals(codelLowerLeft.getParent(), codelUpperRight.getParent())
    val block2 = codelLowerLeft.getParent()
    assertNotEquals(block1, block2)
  }
}