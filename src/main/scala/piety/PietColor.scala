package piety

import java.awt.Color
import Hue._
import Lightness._

/**
 * Defines a Piet color for use in the interpreter.
 */
class PietColor(val color: Color) {

  var hue: Hue = calculateHue(color)
  var lightness: Lightness = calculateLightness(color)
  var valid: Boolean = isValid(color)

/**
 * Given an RGB, returns its hue.
 */
  def calculateHue(color: Color): Hue = {
    color match {
      case LIGHT_RED     => return Hue.Red
      case RED           => return Hue.Red
      case DARK_RED      => return Hue.Red

      case LIGHT_YELLOW  => return Hue.Yellow
      case YELLOW        => return Hue.Yellow
      case DARK_YELLOW   => return Hue.Yellow

      case LIGHT_GREEN   => return Hue.Green
      case GREEN         => return Hue.Green
      case DARK_GREEN    => return Hue.Green

      case LIGHT_CYAN    => return Hue.Cyan
      case CYAN          => return Hue.Cyan
      case DARK_CYAN     => return Hue.Cyan

      case LIGHT_BLUE    => return Hue.Blue
      case BLUE          => return Hue.Blue
      case DARK_BLUE     => return Hue.Blue

      case LIGHT_MAGENTA => return Hue.Magenta
      case MAGENTA       => return Hue.Magenta
      case DARK_MAGENTA  => return Hue.Magenta

      case BLACK         => return Hue.Black

      case _             => return Hue.White
    }
  }

  /**
   * Given an RGB, returns its Piet lightness.
   */
  def calculateLightness(color: Color): Lightness = {
    color match {
      case LIGHT_RED     => return Lightness.Light
      case LIGHT_YELLOW  => return Lightness.Light
      case LIGHT_GREEN   => return Lightness.Light
      case LIGHT_CYAN    => return Lightness.Light
      case LIGHT_BLUE    => return Lightness.Light
      case LIGHT_MAGENTA => return Lightness.Light

      case RED           => return Lightness.Normal
      case YELLOW        => return Lightness.Normal
      case GREEN         => return Lightness.Normal
      case CYAN          => return Lightness.Normal
      case BLUE          => return Lightness.Normal
      case MAGENTA       => return Lightness.Normal

      case DARK_RED      => return Lightness.Dark
      case DARK_YELLOW   => return Lightness.Dark
      case DARK_GREEN    => return Lightness.Dark
      case DARK_CYAN     => return Lightness.Dark
      case DARK_BLUE     => return Lightness.Dark
      case DARK_MAGENTA  => return Lightness.Dark

      case _             => return Lightness.Normal
    }
  }
  
  /**
   * Determines whether the given color is one of the defined RGB values
   * for Piet colors. Returns a boolean.
   */
  def isValid(color: Color): Boolean = {
    color match {
      case LIGHT_RED     => return true
      case RED           => return true
      case DARK_RED      => return true
      case LIGHT_YELLOW  => return true
      case YELLOW        => return true
      case DARK_YELLOW   => return true
      case LIGHT_GREEN   => return true
      case GREEN         => return true
      case DARK_GREEN    => return true
      case LIGHT_CYAN    => return true
      case CYAN          => return true
      case DARK_CYAN     => return true
      case LIGHT_BLUE    => return true
      case BLUE          => return true
      case DARK_BLUE     => return true
      case LIGHT_MAGENTA => return true
      case MAGENTA       => return true
      case DARK_MAGENTA  => return true
      case BLACK         => return true
      case WHITE         => return true

      case _             => return false
    }
  }

  def getHue(): Hue = {
    return hue
  }

  def getLightness(): Lightness = {
    return lightness
  }

  def equals(other: PietColor): Boolean = {
    return getHue() == other.getHue() && getLightness() == other.getLightness()
  }

  override def toString(): String = {
    return getLightness() + " " + getHue()
  }
}