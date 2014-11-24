package piety

import java.awt.Color

/**
 * Represents a single codel in a Piet program.
 */
class Codel(val col: Color) {
  var color: Color = col
  var parent: ColorBlock = null

  /**
   * Returns the RGB color value of the current codel.
   */
  def getColorValue(): Int = {
    return color.getRGB()
  }
  
  override def toString(): String = {
    return Integer.toString(getColorValue())
  }
  
  def equals(other: Codel): Boolean = {
    return getColorValue() == other.getColorValue()
  }
  
  def setParent(par: ColorBlock) = {
    parent = par
  }
  
  def getParent(): ColorBlock = {
    if(parent == null) {
      setParent(new ColorBlock())
    }
    
    return parent
  }
}