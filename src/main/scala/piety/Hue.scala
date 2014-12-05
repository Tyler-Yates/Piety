package piety

import java.awt.Color

/**
 * Defines all the colors that are used in Piet based on RGB
 */
object Hue extends Enumeration {
  type Hue = Value
  val Red, Yellow, Green, Cyan, Blue, Magenta, White, Black = Value

  val LIGHT_RED = new Color(255, 192, 192)
  val RED = new Color(255, 0, 0)
  val DARK_RED = new Color(192, 0, 0)
  //
  val LIGHT_YELLOW = new Color(255, 255, 192)
  val YELLOW = new Color(255, 255, 0)
  val DARK_YELLOW = new Color(192, 192, 0)
  //
  val LIGHT_GREEN = new Color(192, 255, 192)
  val GREEN = new Color(0, 255, 0)
  val DARK_GREEN = new Color(0, 192, 0)
  //
  val LIGHT_CYAN = new Color(192, 255, 255)
  val CYAN = new Color(0, 255, 255)
  val DARK_CYAN = new Color(0, 192, 192)
  //
  val LIGHT_BLUE = new Color(192, 192, 255)
  val BLUE = new Color(0, 0, 255)
  val DARK_BLUE = new Color(0, 0, 192)
  //
  val LIGHT_MAGENTA = new Color(255, 192, 255)
  val MAGENTA = new Color(255, 0, 255)
  val DARK_MAGENTA = new Color(192, 0, 192)
  //
  val BLACK = new Color(0, 0, 0)
  //
  val WHITE = new Color(255, 255, 255)
}