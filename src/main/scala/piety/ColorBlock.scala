package piety

import scala.collection.mutable.ListBuffer

/**
 * A ColorBlock is a group of codels, functionally a shape.
 * It is of one congruent color and can contain other color blocks
 * within it.
 *
 * For reference: DP = Direction Pointer; CC = Codel Chooser
 */
class ColorBlock(pietColor: PietColor) {

  var children: ListBuffer[Codel] = new ListBuffer[Codel]()

  var color: PietColor = pietColor

  var topEdge: Int = Integer.MAX_VALUE
  var bottomEdge: Int = Integer.MIN_VALUE
  var leftEdge: Int = Integer.MAX_VALUE
  var rightEdge: Int = Integer.MIN_VALUE

  // Read these in two parts: DP CC
  var rightLeft: Codel = null
  var rightRight: Codel = null
  var downLeft: Codel = null
  var downRight: Codel = null
  var leftLeft: Codel = null
  var leftRight: Codel = null
  var upLeft: Codel = null
  var upRight: Codel = null

  def getColor: PietColor = {
    color
  }

  /**
   * Adds a codel to this color block. The codel's
   * coordinates are read in to determine whether the
   * color block's edges need to be updated.
   */
  def addCodel(codel: Codel) = {
    children += codel
    codel.setParent(this)

    val r = codel.getRow
    val c = codel.getColumn

    if (r < topEdge) {
      topEdge = r
    }
    if (r > bottomEdge) {
      bottomEdge = r
    }

    if (c < leftEdge) {
      leftEdge = c
    }
    if (c > rightEdge) {
      rightEdge = c
    }
  }

  /**
   * Merges the given color block into the current color block.
   */
  def mergeColorBlock(otherBlock: ColorBlock) {
    // Only blocks with the same color can be merged
    if (!color.equals(otherBlock.getColor)) {
      throw new IllegalArgumentException("Color blocks must have same color to merge")
    }

    // Don't merge the same block
    if (otherBlock eq this) {
      return
    }

    // Update the parent pointer of the other block's codels
    for (child <- otherBlock.children) {
      child.setParent(this)
    }

    // Add the other block's codels to the current block
    children ++= otherBlock.children

    // Update the edges of the current block
    if (otherBlock.topEdge < topEdge) {
      topEdge = otherBlock.topEdge
    }
    if (otherBlock.bottomEdge > bottomEdge) {
      bottomEdge = otherBlock.bottomEdge
    }
    if (otherBlock.leftEdge < leftEdge) {
      leftEdge = otherBlock.leftEdge
    }
    if (otherBlock.rightEdge > rightEdge) {
      rightEdge = otherBlock.rightEdge
    }
  }

  /**
   * Calculates the eight codel "extremes" of the current color block. The codels do not have to be distinct.
   * This method should only be called after all codels have been added to the current color block.
   *
   * This method assumes that all of the extremes are set to null.
   */
  def findEightExtremes(codels: Array[Array[Codel]]): Unit = {
    // If the eight extremes have already been calculated, don't recalculate them
    if (rightLeft != null) {
      return
    }

    // DP = Right
    for (i <- topEdge to bottomEdge) {
      val currentCodel = codels(i)(rightEdge)
      if (rightLeft == null && currentCodel.getParent == this) {
        rightLeft = currentCodel
        // There is no easy way to break out of a loop in Scala :/
      }
    }

    for (i <- bottomEdge to topEdge by -1) {
      val currentCodel = codels(i)(rightEdge)
      if (rightRight == null && currentCodel.getParent == this) {
        rightRight = currentCodel
      }
    }

    // DP = Down
    for (i <- leftEdge to rightEdge) {
      val currentCodel = codels(bottomEdge)(i)
      if (downRight == null && currentCodel.getParent == this) {
        downRight = currentCodel
      }
    }

    for (i <- rightEdge to leftEdge by -1) {
      val currentCodel = codels(bottomEdge)(i)
      if (downLeft == null && currentCodel.getParent == this) {
        downLeft = currentCodel
      }
    }

    // DP = Left
    for (i <- topEdge to bottomEdge) {
      val currentCodel = codels(i)(leftEdge)
      if (leftRight == null && currentCodel.getParent == this) {
        leftRight = currentCodel
        // There is no easy way to break out of a loop in Scala :/
      }
    }

    for (i <- bottomEdge to topEdge by -1) {
      val currentCodel = codels(i)(leftEdge)
      if (leftLeft == null && currentCodel.getParent == this) {
        leftLeft = currentCodel
      }
    }

    // DP = Up
    for (i <- leftEdge to rightEdge) {
      val currentCodel = codels(topEdge)(i)
      if (upLeft == null && currentCodel.getParent == this) {
        upLeft = currentCodel
      }
    }

    for (i <- rightEdge to leftEdge by -1) {
      val currentCodel = codels(topEdge)(i)
      if (upRight == null && currentCodel.getParent == this) {
        upRight = currentCodel
      }
    }
  }

  override def equals(other: Any): Boolean = {
    this.hashCode() == other.hashCode()
  }

  /**
   * Given a DP and CC, return the corresponding neighbor color block.
   */
  def getCodel(dp: Int, cc: Int): Codel = {
    if (dp == Direction.Up.id) {
      if (cc == (2 - Direction.Left.id)) {
        upLeft
      } else {
        upRight
      }
    } else if (dp == Direction.Right.id) {
      if (cc == (2 - Direction.Left.id)) {
        rightLeft
      } else {
        rightRight
      }
    } else if (dp == Direction.Down.id) {
      if (cc == (2 - Direction.Left.id)) {
        downLeft
      } else {
        downRight
      }
    } else {
      if (cc == (2 - Direction.Left.id)) {
        leftLeft
      } else {
        leftRight
      }
    }
  }

  def getValue: Int = {
    children.size
  }

  override def toString: String = {
    "Color: " + color.toString() + "\nExtremes:\n" +
      rightLeft.toString() + "\n" +
      rightRight.toString() + "\n" +
      downLeft.toString() + "\n" +
      downRight.toString() + "\n" +
      leftLeft.toString() + "\n" +
      leftRight.toString() + "\n" +
      upLeft.toString() + "\n" +
      upRight.toString()
  }
}