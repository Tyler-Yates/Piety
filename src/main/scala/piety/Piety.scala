package piety

import javax.imageio.ImageIO
import java.io.File
import java.awt.Color

object Piety {

  def main(args: Array[String]): Unit = {
    processImage("src"+File.separator+"main"+File.separator+"resources"+File.separator+"hello_world.gif", 11)
  }
  
  def processImage(path: String, codelSize: Int): Unit = {
    val image = ImageIO.read(new File(path))
    val prog = new Program(image, codelSize)
    
    Interpreter.execute(prog)
  }
}