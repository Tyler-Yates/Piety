package piety

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test
import org.junit.Before

import javax.imageio.ImageIO
import java.io.File
import java.awt.Color

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
    assertSame(codelLowerLeft.getParent(), codelLowerRight.getParent())
    assertSame(codelLowerLeft.getParent(), codelUpperRight.getParent())
    val block2 = codelLowerLeft.getParent()
    assertNotSame(block1, block2)
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
  
  @Test def testMerge() = {
    val block1: ColorBlock = new ColorBlock(new PietColor(Color.RED))
    val block2: ColorBlock = new ColorBlock(new PietColor(Color.RED))
    
    val codel1 = new Codel(Color.RED, 0, 0)
    val codel2 = new Codel(Color.RED, 1, 0)
    val codel3 = new Codel(Color.RED, 0, 1)
    val codel4 = new Codel(Color.RED, 1, 1)
    
    block1.addCodel(codel1)
    block1.addCodel(codel2)
    
    block2.addCodel(codel3)
    block2.addCodel(codel4)
    
    block1.mergeColorBlock(block2)
    assertSame(block1, codel1.getParent())
    assertSame(block1, codel2.getParent())
    assertSame(block1, codel3.getParent())
    assertSame(block1, codel4.getParent())
    assertTrue(block1.children.contains(codel1))
    assertTrue(block1.children.contains(codel2))
    assertTrue(block1.children.contains(codel3))
    assertTrue(block1.children.contains(codel4))
  }
}