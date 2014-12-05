package piety

import java.awt.image.BufferedImage
import java.awt.Color
import java.io.PrintStream
import Hue._

/**
 * Represents a single Piet program.
 */
class Program(img: BufferedImage, codelSize: Int) {
  // The program is composed of a matrix of codels which abstracts away the pixel size of the program's image.
  var codels = processImage(img, codelSize)

  def compileImage(img: BufferedImage, codelSize: Int, output: PrintStream): Unit = {
	val arrayColumns = img.getWidth() / codelSize
    val arrayRows = img.getHeight() / codelSize

    for (r <- 0 until arrayRows) {
      for (c <- 0 until arrayColumns) {
        val x = c * codelSize
        val y = r * codelSize
    	val codelColor = new Color(img.getRGB(x, y))
   
        codelColor match {
          case LIGHT_RED     => output.print("lr ")
	      case RED           => output.print("nr ")
	      case DARK_RED      => output.print("dr ")
	
	      case LIGHT_YELLOW  => output.print("ly ")
	      case YELLOW        => output.print("ny ")
	      case DARK_YELLOW   => output.print("dy ")
	
	      case LIGHT_GREEN   => output.print("lg ")
	      case GREEN         => output.print("ng ")
	      case DARK_GREEN    => output.print("dg ")
	
	      case LIGHT_CYAN    => output.print("lc ")
	      case CYAN          => output.print("nc ")
	      case DARK_CYAN     => output.print("dc ")
	
	      case LIGHT_BLUE    => output.print("lb ")
	      case BLUE          => output.print("nb ")
	      case DARK_BLUE     => output.print("db ")
	
	      case LIGHT_MAGENTA => output.print("lm ")
	      case MAGENTA       => output.print("nm ")
	      case DARK_MAGENTA  => output.print("dm ")
	
	      case BLACK         => output.print("bb ")
	
	      case _             => output.print("ww ")
        }
      }
      output.print("\r\n")
    }
  }
  
  
  
  /**
   * Processes an image representing a Piet program with the given codel size.
   */
  def processImage(	img: BufferedImage, codelSize: Int): Array[Array[Codel]] = {
    
    val arrayColumns = img.getWidth() / codelSize
    val arrayRows = img.getHeight() / codelSize
    var cod = Array.ofDim[Codel](arrayRows, arrayColumns)

    // Build the codel matrix
    for (r <- 0 until arrayRows) {
      for (c <- 0 until arrayColumns) {
        val x = c * codelSize
        val y = r * codelSize

        var aboveCodel: Codel = null
        var leftCodel: Codel = null

        // Since we are reading the image left-to-right top-to-bottom, only the codels above and to the left of
        // the current codel (if they exist) will have been initialized
        if (r > 0) {
          aboveCodel = cod(r - 1)(c)
        }
        if (c > 0) {
          leftCodel = cod(r)(c - 1)
        }

        var currentCodel: Codel = new Codel(new Color(img.getRGB(x, y)), r, c)

        var parent: ColorBlock = null
        // Determine if the codel should be assigned to an existing color block
        if (aboveCodel != null && aboveCodel.hasSameColorAs(currentCodel)) {
          // If both codels are in the same block then we need to combine the two blocks
          if (leftCodel != null && leftCodel.hasSameColorAs(currentCodel)) {
            // Merge the two blocks
            aboveCodel.getParent().mergeColorBlock(leftCodel.getParent())
          }
          parent = aboveCodel.getParent()
        } else if (leftCodel != null && leftCodel.hasSameColorAs(currentCodel)) {
          parent = leftCodel.getParent()
        }
        
        if (parent == null) {
          parent = new ColorBlock(currentCodel.getColor())
        }
        parent.addCodel(currentCodel)

        cod(r)(c) = currentCodel
      }
    }

    // Calculate the extremes of the color blocks
    for (r <- 0 until arrayRows) {
      for (c <- 0 until arrayColumns) {
        var currentCodel: Codel = cod(r)(c)
        currentCodel.getParent().findEightExtremes(cod)
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
  
  def onBoard(r: Int, c: Int): Boolean = {
    if(r > -1 && c > -1 && r < codels.size && c < codels(0).size) {
      return true
    }
    return false
  }
}