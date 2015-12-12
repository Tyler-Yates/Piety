package piety

/**
 * Defines the stack used in Piet programs.
 * It is currently implemented as a list, this is subject to change.
 */
class PietStack {
  var stack = new scala.collection.mutable.MutableList[Int]()

  def push(value: Int) = {
    stack += value
  }

  def pop(): Int = {
    val ret = stack.last
    stack = stack.init
    ret
  }

  def size(): Int = {
    stack.length
  }

  def clear() = {
    stack.clear()
  }

  /**
   * Takes the top of the stack and buries it by the depth.
   * A depth of 0 is no change.
   * Number of rolls is the number of times this is done.
   */
  def roll(numberOfRolls: Int, depth: Int) = {
    if (depth < 0) {
      throw new IllegalArgumentException("Negative depth not allowed")
    }

    if (numberOfRolls > 0) {
      for (i <- 0 until numberOfRolls) {
        val first = stack.dropRight(depth)
        val top = stack.takeRight(depth)
        val mid = top.last
        val last = top.dropRight(1)
        stack = (first :+ mid) ++ last
      }
    } else if (numberOfRolls < 0) {
      for (i <- 0 until (-1 * numberOfRolls)) {
        val first = stack.dropRight(depth)
        val top = stack.takeRight(depth)
        val mid = top.drop(1)
        val last = top.head
        stack = first ++ (mid :+ last)
      }
    }
  }

  override def toString: String = {
    stack.toString()
  }
}