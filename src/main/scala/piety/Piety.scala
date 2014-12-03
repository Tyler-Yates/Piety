package piety

import javax.imageio.ImageIO
import java.io.File
import java.awt.Color

object Piety {

  def main(args: Array[String]): Unit = {
    /**
     * Running the program specifies a file path and codel size. 
     */
    if (args.length == 2) {
      try {
        val filepath = args(0)

        if (new File(filepath).exists()) {
          val codelSize = args(1).toInt

          if (codelSize > 0) {
            processImage(filepath, codelSize)
          } else {
            println("Codel size must be greater than zero")
          }
        } else {
          println("File does not exist")
        }
      } catch {
        case e: NumberFormatException => println("Second argument must be a number representing the codel size of the program")
      }
    } else {
      println("Program requires two input arguments: [program file path] [codel size]")
    }
  }

  /**
   * First converts the file into a readable form, prog, and then
   * executes that program with the Interpreter. 
   */
  def processImage(path: String, codelSize: Int): Unit = {
    val image = ImageIO.read(new File(path))
    val prog = new Program(image, codelSize)

    Interpreter.execute(prog)
  }
}