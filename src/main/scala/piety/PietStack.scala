package piety

class PietStack {
  var stack = new scala.collection.mutable.MutableList[Int]()

  def push(value: Int) = {
    stack += value
  }
  
  def pop(): Int = {
    val ret = stack.last
    stack = stack.init
    return ret
  }
  
  def size(): Int = {
    return stack.length
  }
  
  def clear() = {
    stack.clear()
  }
  
  def roll(numberOfRolls: Int, depth: Int) = {
    if(depth < 0) {
      throw new IllegalArgumentException("Negative depth not allowed")
    }
    
    if(numberOfRolls > 0) {
      for (i <- 0 until numberOfRolls) {
        val first = stack.dropRight(depth)
        val top = stack.takeRight(depth)
        val mid = top.last
        val last = top.dropRight(1)
        stack = (first :+ mid) ++ last
      }
    }
    else if(numberOfRolls < 0) {
      for (i <- 0 until (-1 * numberOfRolls)) {
        val first = stack.dropRight(depth)
        val top = stack.takeRight(depth)
        val mid = top.head
        val last = top.drop(1)
        stack = (first :+ mid) ++ last
      }
    }
  }
  
  override def toString(): String = {
    return stack.toString()
  }
}