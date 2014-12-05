package piety

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.PrintStream
import java.io.File
import javax.imageio.ImageIO
import Hue._

/**
 * Used to turn an image file into ScalaPiet DSL code.
 */
object Compiler {
  
  def main(args: Array[String]): Unit = {
    if (args.length == 3) {
      try {
        val inputPath = args(0)
        val outputPath = args(2)
        if (new File(inputPath).exists()) {
          val codelSize = args(1).toInt

          if (codelSize > 0) {
            compileImage(ImageIO.read(new File(inputPath)), codelSize, new PrintStream(new File(outputPath)))
          } else {
            println("Codel size must be greater than zero")
          }
        } else {
          println("File does not exist")
        }
      } catch {
        case e: Exception => println(e)
      }
    }
  }

  /**
   * Takes in an image and codel size and prints out the corresponding ScalaPiet DSL
   * code to the given PrintStream.
   */
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
}