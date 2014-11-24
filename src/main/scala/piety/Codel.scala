package piety

import java.awt.Color

/**
 * Represents a single codel in a Piet program.
 */
class Codel(val col: Color) {
  var color: Color = col

  /**
   * Returns the RGB color value of the current codel.
   */
  def getColorValue(): Int = {
    return color.getRGB()
  }
  
  override def toString(): String = {
    return Integer.toString(getColorValue())
  }
}