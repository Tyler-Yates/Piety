package piety

import java.awt.image.BufferedImage
import java.awt.Color

/**
 * Represents a single Piet program.
 */
class Program(img: BufferedImage, codelSize: Int) {
  // The program is composed of a matrix of codels which abstracts away the pixel size of the program's image.
  var codels = processImage(img, codelSize)

  /**
   * Processes an image representing a Piet program with the given codel size.
   */
  def processImage(img: BufferedImage, codelSize: Int): Array[Array[Codel]] = {
    val arrayColumns = img.getWidth() / codelSize
    var arrayRows = img.getHeight() / codelSize
    var cod = Array.ofDim[Codel](arrayRows, arrayColumns)

    for (r <- 0 until arrayRows) {
      for (c <- 0 until arrayColumns) {
        val x = c * codelSize
        val y = r * codelSize

        var aboveCodel: Codel = null
        var leftCodel: Codel = null

        if (r > 0) {
          aboveCodel = cod(r - 1)(c)
        }
        if (c > 0) {
          leftCodel = cod(r)(c - 1)
        }

        var currentCodel: Codel = new Codel(new Color(img.getRGB(x, y)), r, c)

        var parent: ColorBlock = null
        if (aboveCodel != null && aboveCodel.equals(currentCodel)) {
          currentCodel.setParent(aboveCodel.getParent())
        } else if (leftCodel != null && leftCodel.equals(currentCodel)) {
          currentCodel.setParent(leftCodel.getParent())
        }

        cod(r)(c) = currentCodel
      }
    }

    return cod
  }

  /**
   * Returns the codel at the given row and column in the codel matrix.
   */
  def getCodel(r: Int, c: Int): Codel = {
    return codels(r)(c)
  }
}