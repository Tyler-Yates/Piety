package piety

import scala.math

object Interpreter {
	
	val stack = new PietStack
	var directionPointer: Int = Direction.Right.id
	var codelChooser: Int = 2 - Direction.Left.id
	
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
	  		case 0 => {val temp = stack.pop()
          stack.push(stack.pop() / temp)}
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
		  	case 2 => {if(stack.size > 0) {
          codelChooser = (codelChooser + math.abs(stack.pop()) % 2 * 2 ) % 4
        }
      }
		}
	}
	def hueChange4(lightnessChange: Int, cBValue: Int): Unit = {
	  	lightnessChange match {
	  	    // duplicate
	  		case 0 => {val i = stack.pop()
	  					stack.push(i)
	  					stack.push(i)}
	  		// roll
		  	case 1 =>	{var num = stack.pop()
		  				var depth = stack.pop()
		  				stack.roll(num, depth)}
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
  
  def execute(prog: Program): Unit = {
    stack.clear()
    directionPointer = Direction.Right.id
    codelChooser = 2 - Direction.Left.id
    
    var row = 0
    var col = 0
    var attemptCount = 0
    var rotate = false
    
    while(attemptCount < 8) {
      val currentCodel = prog.getCodel(row, col)
      val edgeCodel = currentCodel.getEdgeCodel(directionPointer, codelChooser)
      var nextRow = edgeCodel.getRow()
      var nextCol = edgeCodel.getColumn()
      
      var white = -1
      
      do{
        if(directionPointer % 2 == 0) {
          nextRow += directionPointer - 1
        }
        else {
          nextCol += 2 - directionPointer
        }
        white += 1
      } while (prog.onBoard(nextRow, nextCol) && prog.getCodel(nextRow, nextCol).getColor().getHue() == Hue.White);
      if(!prog.onBoard(nextRow, nextCol) || prog.getCodel(nextRow, nextCol).getColor().getHue() == Hue.Black) {
        if(rotate) {
          directionPointer += 1
          directionPointer %= 4
          rotate = false
        }
        else {
          codelChooser *= -1
          rotate = true
        }
        attemptCount += 1
      }
      else {
        if(white == 0) {
          var nextCodel: Codel = prog.getCodel(nextRow, nextCol)
          var hueChange: Int = nextCodel.getColor().getHue().id - currentCodel.getColor().getHue().id
          if(hueChange < 0) {
            hueChange += 6
          }
          var lightnessChange: Int = nextCodel.getColor().getLightness().id - currentCodel.getColor().getLightness().id
          if(lightnessChange < 0) {
            lightnessChange += 3
          }
          doInstruction(hueChange, lightnessChange, currentCodel.getParent().getValue())
        }
        row = nextRow
        col = nextCol
        attemptCount = 0
        rotate = false
      }
    }
  }
}