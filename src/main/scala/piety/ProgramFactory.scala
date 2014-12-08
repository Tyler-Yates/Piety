package piety

import java.awt.image.BufferedImage
import java.awt.Color

object ProgramFactory {

  def createProgramFromImage(img: BufferedImage, codelSize: Int): Program = {
    if (img.getWidth() % codelSize != 0 || img.getHeight() % codelSize != 0)
      throw new IllegalArgumentException("Image has invalid dimensions relative to codel size")
    val arrayColumns = img.getWidth() / codelSize
    val arrayRows = img.getHeight() / codelSize

    val prog = new Program(arrayRows, arrayColumns)

    // Build the program codel by codel
    for (r <- 0 until arrayRows) {
      for (c <- 0 until arrayColumns) {
        val x = c * codelSize
        val y = r * codelSize
        var color = new Color(img.getRGB(x, y))
        var pietColor = new PietColor(color)
        if (!pietColor.valid)
          throw new IllegalArgumentException("Image contains invalid colors at (" + x + "," + y + "): " + color.toString())
        var currentCodel: Codel = new Codel(new Color(img.getRGB(x, y)), r, c)
        prog.addCodel(currentCodel)
      }
    }

    return prog
  }
}