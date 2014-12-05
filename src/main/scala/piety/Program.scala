package piety

import java.awt.image.BufferedImage
import java.awt.Color
import Hue._

/**
 * Represents a single Piet program.
 */
class Program(rows: Int, columns: Int, stack: PietStack = new PietStack()) {
  var codels: Array[Array[Codel]] = Array.ofDim[Codel](rows, columns)

  def addCodel(codel: Codel) = {
    val r = codel.row
    val c = codel.col

    if (!onBoard(r, c)) {
      throw new IllegalArgumentException("Codel is off the board.")
    }

    var aboveCodel: Codel = null
    var leftCodel: Codel = null

    // Since we are reading the image left-to-right top-to-bottom, only the codels above and to the left of
    // the current codel (if they exist) will have been initialized
    if (r > 0) {
      aboveCodel = getCodel(r - 1, c)
    }
    if (c > 0) {
      leftCodel = getCodel(r, c - 1)
    }

    var parent: ColorBlock = null
    // Determine if the codel should be assigned to an existing color block
    if (aboveCodel != null && aboveCodel.hasSameColorAs(codel)) {
      // If both codels are in the same block then we need to combine the two blocks
      if (leftCodel != null && leftCodel.hasSameColorAs(codel)) {
        // Merge the two blocks
        aboveCodel.getParent().mergeColorBlock(leftCodel.getParent())
      }
      parent = aboveCodel.getParent()
    } else if (leftCodel != null && leftCodel.hasSameColorAs(codel)) {
      parent = leftCodel.getParent()
    }

    if (parent == null) {
      parent = new ColorBlock(codel.getColor())
    }
    parent.addCodel(codel)

    codels(r)(c) = codel

    if (r == rows - 1 && c == columns - 1) {
      for (r <- 0 until rows) {
        for (c <- 0 until columns) {
          var currentCodel: Codel = codels(r)(c)
          currentCodel.getParent().findEightExtremes(codels)
        }
      }
    }
  }

  /**
   * Returns the codel at the given row and column in the codel matrix.
   */
  def getCodel(r: Int, c: Int): Codel = {
    if (!onBoard(r, c)) {
      throw new IllegalArgumentException("Row and column not on the board.")
    }

    val codel = codels(r)(c)

    if (codel == null) {
      throw new IllegalStateException("Program has not been fully initialized! A codel is missing from the board.")
    }
    return codel
  }

  def onBoard(r: Int, c: Int): Boolean = {
    if (r > -1 && c > -1 && r < codels.size && c < codels(0).size) {
      return true
    }
    return false
  }
  def getStack(): PietStack = {
    return stack
  }
}
