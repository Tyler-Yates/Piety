package piety

import javax.imageio.ImageIO
import java.io.File
import java.awt.Color
import java.io.PrintStream

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
    } 
    else if (args.length == 3) {
      try {
        val inputPath = args(0)
        val outputPath = args(2)
        if (new File(inputPath).exists()) {
          val codelSize = args(1).toInt

          if (codelSize > 0) {
            compileImage(inputPath, codelSize, outputPath)
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
    else {
      println("Program requires two input arguments: [program file path] [codel size]")
    }
  }
  
  def compileImage(inputPath: String, codelSize: Int, outputPath: String): Unit = {
    val image = ImageIO.read(new File(inputPath))
    val prog = new Program(image, codelSize)
    Compiler.compileImage(image, codelSize, new PrintStream(new File(outputPath)))
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