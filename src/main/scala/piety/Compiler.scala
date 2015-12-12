package piety

import java.awt.Color
import java.awt.image.BufferedImage
import java.io._
import javax.imageio.ImageIO

import piety.Hue._

/**
 * Used to turn an image file into ScalaPiet DSL code.
 */
object Compiler {

  val maxCodeBlockSize = 3000
  val methodName = "piet"
  var split = false

  def main(args: Array[String]): Unit = {
    if (args.length == 3) {
      try {
        val inputPath = args(0)
        val outputPath = args(2)
        if (new File(inputPath).exists()) {
          val codelSize = args(1).toInt

          if (codelSize > 0) {
            compileImage(ImageIO.read(new File(inputPath)), codelSize, outputPath)
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
  def compileImage(img: BufferedImage, codelSize: Int, out: String): Unit = {

    val output = new PrintStream(new File(out))
    val arrayColumns = img.getWidth() / codelSize
    val arrayRows = img.getHeight() / codelSize
    val codeBlockSize = (maxCodeBlockSize / arrayColumns) * arrayColumns

    val codeBlocks = (arrayColumns * arrayRows) / codeBlockSize + 1

    if (arrayColumns * arrayRows > maxCodeBlockSize)
      split = true

    var currentCodeBlock = 0
    var currentCodel = 0
    val name = out.substring(out.lastIndexOf("\\") + 1, out.lastIndexOf("."))

    // top of the scala file is always the same
    output.print("import piety._\r\n\r\n")
    output.print("object " + name + " extends PietDSL {\r\n")
    output.print("\tdef main(args: Array[String]): Unit = {\r\n")
    output.print("\t\tBLANK_SPACE\r\n")
    output.print("\t\tROWS(" + arrayRows + ")\r\n")
    output.print("\t\tCOLUMNS(" + arrayColumns + ")\r\n")

    if (split)
      for (i <- 0 until codeBlocks)
        output.print("\t\t" + methodName + i + "()\r\n")

    if (split) {
      output.print("\t\tPIET()\r\n")
      output.print("\t}\t\r\n")
      output.print(methodHeader(currentCodeBlock))
    }

    for (r <- 0 until arrayRows) {
      output.print("\t\t")
      for (c <- 0 until arrayColumns) {
        if (split && currentCodel == codeBlockSize) {
          currentCodel = 0
          currentCodeBlock += 1
          output.print("\r\n\t}\r\n")
          output.print(methodHeader(currentCodeBlock))
        }

        val x = c * codelSize
        val y = r * codelSize
        val codelColor = new Color(img.getRGB(x, y))

        codelColor match {
          case LIGHT_RED     => output.print("lr;")
          case RED           => output.print("nr;")
          case DARK_RED      => output.print("dr;")

          case LIGHT_YELLOW  => output.print("ly;")
          case YELLOW        => output.print("ny;")
          case DARK_YELLOW   => output.print("dy;")

          case LIGHT_GREEN   => output.print("lg;")
          case GREEN         => output.print("ng;")
          case DARK_GREEN    => output.print("dg;")

          case LIGHT_CYAN    => output.print("lc;")
          case CYAN          => output.print("nc;")
          case DARK_CYAN     => output.print("dc;")

          case LIGHT_BLUE    => output.print("lb;")
          case BLUE          => output.print("nb;")
          case DARK_BLUE     => output.print("db;")

          case LIGHT_MAGENTA => output.print("lm;")
          case MAGENTA       => output.print("nm;")
          case DARK_MAGENTA  => output.print("dm;")

          case BLACK         => output.print("bb;")
          case _             => output.print("ww;")
        }
        currentCodel += 1
      }
      output.print("\r\n")
    }
    if (split)
      output.print("\t}\r\n")
    else
      output.print("\t\tPIET()\r\n")
    output.print("}\r\n\r\n")
  }

  def methodHeader(i: Int): String = {
    "\tdef " + methodName + i + "(): Unit = {\r\n\t\t"
  }
}
