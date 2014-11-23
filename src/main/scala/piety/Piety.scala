package piety

import javax.imageio.ImageIO
import java.io.File

object Piety {

  def main(args: Array[String]): Unit = {
    processImage("src"+File.separator+"main"+File.separator+"resources"+File.separator+"scala-logo.jpg")
  }
  
  def processImage(path: String): Unit = {
    val image = ImageIO.read(new File(path))
    
    println(image)
  }
}