package piety

import scala.math

object Interpreter {
	final var up 	= 0
	final var right = 1
	final var down 	= 2
	final var left 	= 3
	
	val stack = new scala.collection.mutable.Stack[Int]
	var directionPointer = right
	var codelChooser = left
	
	def doInstruction(hueChange: Int, lightnessChange: Int, cBValue: Int): Unit = {
		hueChange match {
		  case 0 => hueChange0(lightnessChange, cBValue)
		  case 1 => hueChange1(lightnessChange, cBValue)
		  case 2 => hueChange2(lightnessChange, cBValue)
		  case 3 => hueChange3(lightnessChange, cBValue)
		  case 4 => hueChange4(lightnessChange, cBValue)
		  case 5 => hueChange5(lightnessChange, cBValue)
		}
	}
	
	def hueChange0(lightnessChange: Int, cBValue: Int): Unit = {
		lightnessChange match {
		    // no-op
		  	case 0 => return
		  	// push
		  	case 1 => stack.push(cBValue)
		  	// pop
		  	case 2 => stack.pop()
		}
	}
	def hueChange1(lightnessChange: Int, cBValue: Int): Unit = {
	  	lightnessChange match {
	  		// add
	  		case 0 => stack.push(stack.pop() + stack.pop())
	  		// subtract
		  	case 1 => stack.push(stack.pop() - stack.pop())
		  	// multiply
		  	case 2 => stack.push(stack.pop() * stack.pop())
		}
	}
	def hueChange2(lightnessChange: Int, cBValue: Int): Unit = {
	  	lightnessChange match {
	  	    // divide
	  		case 0 => stack.push(stack.pop() / stack.pop())
	  		// mod
		  	case 1 => stack.push(stack.pop() % stack.pop())
		  	// negate
		  	case 2 => stack.push(if(stack.pop() != 0) 0 else 1)
		}
	}
	def hueChange3(lightnessChange: Int, cBValue: Int): Unit = {
	  	lightnessChange match {
	  	    // greater
	  		case 0 => stack.push(if(stack.pop() <= stack.pop()) 1 else 0)
	  		// pointer
		  	case 1 => rotateDP(stack.pop())
		  	// switch
		  	case 2 => codelChooser = (codelChooser + math.abs(stack.pop()) % 2 * 2 ) % 4
		}
	}
	def hueChange4(lightnessChange: Int, cBValue: Int): Unit = {
	  	lightnessChange match {
	  	    // duplicate
	  		case 0 => 	val i = stack.pop()
	  					stack.push(i)
	  					stack.push(i)
	  		// roll
		  	case 1 =>	var num = stack.pop()
		  				var depth = stack.pop()
		  				if (depth > 0) {
		  					if (num < 0)
		  						depth = depth * -1
		  					for(i <- 0 until num)
		  						roll(depth)
		  				}
		  				
		  	// in (number)
		  	case 2 => stack.push(readInt())
		}
	}
	def hueChange5(lightnessChange: Int, cBValue: Int): Unit = {
	  	lightnessChange match {
	  		// in (char)
	  		case 0 => stack.push(readChar().asInstanceOf[Int])
	  		// out (number)
		  	case 1 => print(stack.pop())
		  	// out (char)
		  	case 2 => print(stack.pop().asInstanceOf[Char])
		}
	}
	
	def rotateDP(rotation: Int): Unit = {
		directionPointer = (directionPointer + rotation) % 4
		if (directionPointer < 0) {
			directionPointer += 4
		}
	}
	def roll(depth: Int): Unit = {
	    var tempStack = new scala.collection.mutable.Stack[Int]
	    var tempVal = 0
	    if (depth > 0) {
	    	tempVal = stack.pop()
	    	for (i <- 0 until depth) {
	    		tempStack.push(stack.pop())
	    	}
	    	stack.push(tempVal)
	    	for(i <- 0 until depth) {
	    		stack.push(tempStack.pop())
	    	}
	    }
	    if(depth < 0) {
	    	for (i <- 0 until depth) {
	    		tempStack.push(stack.pop())
	    	}
	    	tempVal = stack.pop()
	    	for(i <- 0 until depth) {
	    		stack.push(tempStack.pop())
	    	}
	    	stack.push(tempVal)
	    }
	}
	
	def main(args: Array[String]): Unit = {
    	  println(-14%5)
    }  
  
  def execute(prog: Program): Unit = {
    stack.clear()
    
    
  }
}