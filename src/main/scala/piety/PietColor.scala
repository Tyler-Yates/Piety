package piety

import java.awt.Color

import piety.Hue._
import piety.Lightness._

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
      case LIGHT_RED => Hue.Red
      case RED => Hue.Red
      case DARK_RED => Hue.Red

      case LIGHT_YELLOW => Hue.Yellow
      case YELLOW => Hue.Yellow
      case DARK_YELLOW => Hue.Yellow

      case LIGHT_GREEN => Hue.Green
      case GREEN => Hue.Green
      case DARK_GREEN => Hue.Green

      case LIGHT_CYAN => Hue.Cyan
      case CYAN => Hue.Cyan
      case DARK_CYAN => Hue.Cyan

      case LIGHT_BLUE => Hue.Blue
      case BLUE => Hue.Blue
      case DARK_BLUE => Hue.Blue

      case LIGHT_MAGENTA => Hue.Magenta
      case MAGENTA => Hue.Magenta
      case DARK_MAGENTA => Hue.Magenta

      case BLACK => Hue.Black

      case _ => Hue.White
    }
  }

  /**
   * Given an RGB, returns its Piet lightness.
   */
  def calculateLightness(color: Color): Lightness = {
    color match {
      case LIGHT_RED => Lightness.Light
      case LIGHT_YELLOW => Lightness.Light
      case LIGHT_GREEN => Lightness.Light
      case LIGHT_CYAN => Lightness.Light
      case LIGHT_BLUE => Lightness.Light
      case LIGHT_MAGENTA => Lightness.Light

      case RED => Lightness.Normal
      case YELLOW => Lightness.Normal
      case GREEN => Lightness.Normal
      case CYAN => Lightness.Normal
      case BLUE => Lightness.Normal
      case MAGENTA => Lightness.Normal

      case DARK_RED => Lightness.Dark
      case DARK_YELLOW => Lightness.Dark
      case DARK_GREEN => Lightness.Dark
      case DARK_CYAN => Lightness.Dark
      case DARK_BLUE => Lightness.Dark
      case DARK_MAGENTA => Lightness.Dark

      case _ => Lightness.Normal
    }
  }

  /**
   * Determines whether the given color is one of the defined RGB values
   * for Piet colors. Returns a boolean.
   */
  def isValid(color: Color): Boolean = {
    color match {
      case LIGHT_RED => true
      case RED => true
      case DARK_RED => true
      case LIGHT_YELLOW => true
      case YELLOW => true
      case DARK_YELLOW => true
      case LIGHT_GREEN => true
      case GREEN => true
      case DARK_GREEN => true
      case LIGHT_CYAN => true
      case CYAN => true
      case DARK_CYAN => true
      case LIGHT_BLUE => true
      case BLUE => true
      case DARK_BLUE => true
      case LIGHT_MAGENTA => true
      case MAGENTA => true
      case DARK_MAGENTA => true
      case BLACK => true
      case WHITE => true

      case _ => false
    }
  }

  def getHue: Hue = {
    hue
  }

  def getLightness: Lightness = {
    lightness
  }

  def equals(other: PietColor): Boolean = {
    getHue == other.getHue && getLightness == other.getLightness
  }

  override def toString: String = {
    getLightness + " " + getHue
  }
}