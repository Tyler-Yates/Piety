package piety

import java.awt.Color

/**
 * Represents a single codel in a Piet program.
 * A codel is the functional equivalent of a pixel in a picture
 * that has been enlarged for easier viewing.
 */
class Codel(val color: Color, val row: Int, val col: Int) {
  var pietColor = new PietColor(color)

  var r: Int = row
  var c: Int = col

  // The color block that this codel is part of.
  var parent: ColorBlock = null

  def getRow(): Int = {
    return r
  }

  def getColumn(): Int = {
    return c
  }

  def getColor(): PietColor = {
    return pietColor
  }

  /**
   * Returns true if the current codel has the given color.
   */
  def hasColor(color: PietColor): Boolean = {
    return pietColor.equals(color)
  }

  override def toString(): String = {
    return "Row: " + r + " Column: " + c + " Color: " + pietColor.toString()
  }
  
  def hasSameColorAs(other: Codel): Boolean = {
    return pietColor.equals(other.getColor())
  }

  def equals(other: Codel): Boolean = {
    return pietColor.equals(other.getColor()) && r == other.r && c == other.c
  }

  def setParent(par: ColorBlock) = {
    parent = par
  }

  def getParent(): ColorBlock = {
    if (parent == null) {
      setParent(new ColorBlock(pietColor))
    }

    return parent
  }
  
  /**
   * Returns the Codel at the edge of this Codel's ColorBlock
   */
  def getEdgeCodel(dp: Int, cc: Int): Codel = {
    return parent.getCodel(dp, cc)
  }
}