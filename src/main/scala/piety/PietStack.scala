package piety

import java.util.LinkedList

class PietStack {
  val stack = new LinkedList[Int]()

  def push(value: Int) = {
    stack.add(value)
  }
  
  def pop(): Int = {
    return stack.removeFirst()
  }
  
  def size(): Int = {
    return stack.size()
  }
  
  def clear() = {
    stack.clear()
  }
  
  def roll(numberOfRolls: Int, depth: Int) = {
    if(depth < 0) {
      throw new IllegalArgumentException("Negative depth not allowed")
    }
    
    // TODO implement rolling
  }
}