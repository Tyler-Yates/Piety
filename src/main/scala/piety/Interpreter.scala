package piety

object Interpreter {
	final var up 	= 0
	final var right = 1
	final var down 	= 2
	final var left 	= 3
	
	val stack = new scala.collection.mutable.Stack[Int]
	var directionPointer = right
	var codelChooser = left
	
	def doInstruction(hueChange: Int, lightnessChange: Int) {
	   
	}
	
	def main(args: Array[String]): Unit = {
    	  println(directionPointer)
    }
  
  
  def execute(prog: Program): Unit = {
    stack.clear()
    
    
  }
}