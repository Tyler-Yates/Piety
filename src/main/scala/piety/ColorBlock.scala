package piety

class ColorBlock(pietColor: PietColor) {

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

  def addCodel(codel: Codel) = {
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
      if (rightLeft == null && currentCodel.hasColor(color)) {
        rightLeft = currentCodel
        // There is no easy way to break out of a loop in Scala :/
      }
    }

    for (i <- bottomEdge to topEdge by -1) {
      var currentCodel = codels(i)(rightEdge)
      if (rightRight == null && currentCodel.hasColor(color)) {
        rightRight = currentCodel
      }
    }

    // DP = Down
    for (i <- leftEdge to rightEdge) {
      var currentCodel = codels(bottomEdge)(i)
      if (downRight == null && currentCodel.hasColor(color)) {
        downRight = currentCodel
      }
    }

    for (i <- rightEdge to leftEdge by -1) {
      var currentCodel = codels(bottomEdge)(i)
      if (downLeft == null && currentCodel.hasColor(color)) {
        downLeft = currentCodel
      }
    }

    // DP = Left
    for (i <- topEdge to bottomEdge) {
      var currentCodel = codels(i)(leftEdge)
      if (leftRight == null && currentCodel.hasColor(color)) {
        leftRight = currentCodel
        // There is no easy way to break out of a loop in Scala :/
      }
    }

    for (i <- bottomEdge to topEdge by -1) {
      var currentCodel = codels(i)(leftEdge)
      if (leftLeft == null && currentCodel.hasColor(color)) {
        leftLeft = currentCodel
      }
    }

    // DP = Up
    for (i <- leftEdge to rightEdge) {
      var currentCodel = codels(topEdge)(i)
      if (upLeft == null && currentCodel.hasColor(color)) {
        upLeft = currentCodel
      }
    }

    for (i <- rightEdge to leftEdge by -1) {
      var currentCodel = codels(topEdge)(i)
      if (upRight == null && currentCodel.hasColor(color)) {
        upRight = currentCodel
      }
    }
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