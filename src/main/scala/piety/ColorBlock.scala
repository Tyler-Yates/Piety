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
    
    if(r < topEdge) {
      topEdge = r
    }
    if(r > bottomEdge) {
      bottomEdge = r
    }
    
    if(c < leftEdge) {
      leftEdge = c
    }
    if(c > rightEdge) {
      rightEdge = c
    }
  }
}