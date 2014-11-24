package piety

import java.awt.image.BufferedImage
import java.awt.Color

/**
 * Represents a single Piet program.
 */
class Program(img: BufferedImage, codelSize: Int) {
  // The program is composed of a matrix of codels which abstracts away the pixel size of the program's image.
  var codels = processImage(img, codelSize)
  
  def processImage(img: BufferedImage, codelSize: Int): Array[Array[Codel]] = {
    val arraySize = img.getWidth()/codelSize
    var cod = Array.ofDim[Codel](arraySize, arraySize)
    
    var r = 0
    var c = 0
    for(r <- 0 to arraySize - 1) {
      for(c <- 0 to arraySize - 1) {
        val x = c * codelSize
        val y = r * codelSize
        cod(r)(c) = new Codel(new Color(img.getRGB(x, y)))
      }
    }
    
    return cod
  }
}