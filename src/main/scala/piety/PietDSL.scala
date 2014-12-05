package piety

import java.lang.IllegalArgumentException
import java.awt.Color

class PietDSL {
	abstract sealed class PietLine
	case class RunPietLine(c: Int, s: String)
	
	var rows = 0
	var cols = 0
	var prog:Program = null
	var stack: PietStack = null
	
	/**
	 * All hail Taylor Swift
	 */
	def BLANK_SPACE {
		var rows = 0
		var cols = 0
		var codels : Array[Array[Codel]] = null
	}
	
	def PALETTE(s: PietStack) {
		stack = s
	}
	
	def ROWS(i: Int) {
		if(i <= 0) 
			throw new IllegalArgumentException("Argument must be positive")
		rows = i
		if (cols != 0)
		  createMatrixAndProgram()
	}
	def COLUMNS(i: Int) {
		if(i <= 0) 
			throw new IllegalArgumentException("Argument must be positive")
		cols = i
		if (rows != 0)
		  createMatrixAndProgram()
	}
  
  def createMatrixAndProgram() {
      if(stack == null) {
        stack = new PietStack()
      }
      prog = new Program(rows, cols, stack)
  }
	
	object PIET {
	  def apply(ignored: Unit) {
		  if(prog == null)
			  throw new IllegalStateException("YOU ARE DUMB")
		  Interpreter.execute(prog)
	  }
	  
	  def apply(c:Int, s:String) {
	    Piety.executeImage(s, c)
	  }
	}
	
	var currentRow = 0
	var currentCol = 0
	
	def addCodel(c: Color): Unit = {
		if (rows <= 0 || cols <= 0)
			throw new IllegalStateException("rows/cols not instantiated")
		var currentCodel = new Codel(c, currentRow, currentCol)
		
    	prog.addCodel(currentCodel)
		
		currentCol += 1
		if (currentCol >= cols) {
			currentCol = 0
			currentRow += 1
		}
	}
	
	def ly {
		addCodel(Hue.LIGHT_YELLOW)
	}
	def ny {
		addCodel(Hue.YELLOW)
	}
	def dy {
		addCodel(Hue.DARK_YELLOW)
	}
	def lr {
		addCodel(Hue.LIGHT_RED)
	}
	def nr {
	  addCodel(Hue.RED)
	}
	def dr {
	  addCodel(Hue.DARK_RED)
	}
	def lg {
	  addCodel(Hue.LIGHT_GREEN)
	}
	def ng {
	  addCodel(Hue.GREEN)
	}
	def dg {
	  addCodel(Hue.DARK_GREEN)
	}	
	def lc {
	  addCodel(Hue.LIGHT_CYAN)
	}
	def nc {
	  addCodel(Hue.CYAN)
	}
	def dc {
	  addCodel(Hue.DARK_CYAN)
	}
	def lb {
	  addCodel(Hue.LIGHT_BLUE)
	}
	def nb {
	  addCodel(Hue.BLUE)
	}
	def db {
	  addCodel(Hue.DARK_BLUE)
	}
	def lm {
	  addCodel(Hue.LIGHT_MAGENTA)
	}
	def nm {
	  addCodel(Hue.MAGENTA)
	}
	def dm {
	  addCodel(Hue.DARK_MAGENTA)
	}
	def ww {
	  addCodel(new Color(255,255,255))
	}
	def bb {
	  addCodel(Hue.BLACK)
	}
}
