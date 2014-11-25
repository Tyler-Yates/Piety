package piety

class ColorBlock {

  // right-most edge - upper
  var rightLeft: Codel = null
  // right-most edge - lower
  var rightRight: Codel = null

  var downLeft: Codel = null
  var downRight: Codel = null
  var leftLeft: Codel = null
  var leftRight: Codel = null
  var upLeft: Codel = null
  var upRight: Codel = null

  def addCodel(codel: Codel) = {
    if (rightLeft == null) {
      rightLeft = codel
      rightRight = codel
      downLeft = codel
      downRight = codel
      leftLeft = codel
      leftRight = codel
      upLeft = codel
      upRight = codel
    } else {
      // right-most edge
      if (codel.getColumn() > rightLeft.getColumn()) {
        rightLeft = codel
        rightRight = codel
      } else if (codel.getColumn() == rightLeft.getColumn()) {
        if (codel.getRow() < rightLeft.getRow()) {
          rightLeft = codel
        }
        if (codel.getRow() > rightRight.getRow()) {
          rightRight = codel
        }
      }

      //TODO add the other directions
    }
  }
}