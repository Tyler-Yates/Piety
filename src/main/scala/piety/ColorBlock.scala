package piety

import scala.collection.mutable.ListBuffer

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
  
  def getColor(): PietColor = {
    return color
  }

  def addCodel(codel: Codel) = {
    children += codel
    codel.setParent(this)

    val r = codel.getRow()
    val c = codel.getColumn()

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
    if(!color.equals(otherBlock.getColor())) {
      throw new IllegalArgumentException("Color blocks must have same color to merge")
    }
    
    // Don't merge the same block
    if(otherBlock eq this) {
      return
    }
    
    // Update the parent pointer of the other block's codels
    for(child <- otherBlock.children) {
      child.setParent(this)
    }
    
    // Add the other block's codels to the current block
    children ++= otherBlock.children
    
    // Update the edges of the current block
    if(otherBlock.topEdge < topEdge) {
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
      var currentCodel = codels(i)(rightEdge)
      if (rightLeft == null && currentCodel.getParent() == this) {
        rightLeft = currentCodel
        // There is no easy way to break out of a loop in Scala :/
      }
    }

    for (i <- bottomEdge to topEdge by -1) {
      var currentCodel = codels(i)(rightEdge)
      if (rightRight == null && currentCodel.getParent() == this) {
        rightRight = currentCodel
      }
    }

    // DP = Down
    for (i <- leftEdge to rightEdge) {
      var currentCodel = codels(bottomEdge)(i)
      if (downRight == null && currentCodel.getParent() == this) {
        downRight = currentCodel
      }
    }

    for (i <- rightEdge to leftEdge by -1) {
      var currentCodel = codels(bottomEdge)(i)
      if (downLeft == null && currentCodel.getParent() == this) {
        downLeft = currentCodel
      }
    }

    // DP = Left
    for (i <- topEdge to bottomEdge) {
      var currentCodel = codels(i)(leftEdge)
      if (leftRight == null && currentCodel.getParent() == this) {
        leftRight = currentCodel
        // There is no easy way to break out of a loop in Scala :/
      }
    }

    for (i <- bottomEdge to topEdge by -1) {
      var currentCodel = codels(i)(leftEdge)
      if (leftLeft == null && currentCodel.getParent() == this) {
        leftLeft = currentCodel
      }
    }

    // DP = Up
    for (i <- leftEdge to rightEdge) {
      var currentCodel = codels(topEdge)(i)
      if (upLeft == null && currentCodel.getParent() == this) {
        upLeft = currentCodel
      }
    }

    for (i <- rightEdge to leftEdge by -1) {
      var currentCodel = codels(topEdge)(i)
      if (upRight == null && currentCodel.getParent() == this) {
        upRight = currentCodel
      }
    }
  }
  
  override def equals(other: Any): Boolean = {
    return this.hashCode() == other.hashCode()
  }

  override def toString(): String = {
    return "Color: " + color.toString() + "\nExtremes:\n" +
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