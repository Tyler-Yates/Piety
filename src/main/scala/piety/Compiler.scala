package piety

import java.awt.Color
import java.awt.image.BufferedImage
import java.io._
import Hue._

object Compiler {

  def compileImage(img: BufferedImage, codelSize: Int, out: String): Unit = {
    val output = new PrintStream(new File(out))
    val arrayColumns = img.getWidth() / codelSize
    val arrayRows = img.getHeight() / codelSize
    
    output.print("import piety._\r\n\r\n")
    output.print("object " + out.substring(out.lastIndexOf("\\")+1, out.lastIndexOf(".")) + " extends PietDSL {\r\n")
    output.print("\tdef main(args: Array[String]): Unit = {\r\n")
    output.print("\t\tROWS(" + arrayRows + ")\r\n")
    output.print("\t\tCOLUMNS(" + arrayColumns + ")\r\n")
    
    for (r <- 0 until arrayRows) {
      output.print("\t\t")
      for (c <- 0 until arrayColumns) {
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
      }
      output.print("\r\n")
    }
    output.print("\t\tPIET\r\n")
    output.print("\t}\r\n")
    output.print("}\r\n\r\n")
  }
}