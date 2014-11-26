package piety

object Interpreter {
	final var up 	= 0
	final var right = 1
	final var down 	= 2
	final var left 	= 3

	var directionPointer = right
	var codelChooser = left
	
	def execute(prog: Program) = {
		// Piet execution begins in the upper left codel
		var startCodel = prog.getCodel(0, 0)
	}
	
	def doInstruction(hueChange: Int, lightnessChange: Int) {
	  
	}
	
	def main(args: Array[String]): Unit = {
    	  println(directionPointer)
    }
}