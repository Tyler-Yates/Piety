package piety

import java.lang.IllegalArgumentException
import java.awt.Color

class PietDSL {
	abstract sealed class PietLine
	case class RunPietLine(c: Int, s: String)
	
	var rows = 0
	var cols = 0
	var codels : Array[Array[Codel]] = null
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
		  codels = Array.ofDim[Codel](rows, cols)
	}
	def COLUMNS(i: Int) {
		if(i <= 0) 
			throw new IllegalArgumentException("Argument must be positive")
		cols = i
		if (rows != 0)
		  codels = Array.ofDim[Codel](rows, cols) 
	}
	
	object PIET {
	  def apply() {
		  if(codels == null)
			  throw new IllegalStateException("YOU ARE DUMB")
		  Piety.processArray(codels)
	  }
	  def apply(c:Int, s:String) {
	    Piety.processImage(s, c)
	  }
	}
	
	var currentRow = 0
	var currentCol = 0
	
	def addCodel(c: Color): Unit = {
		if (rows <= 0 || cols <= 0)
			throw new IllegalStateException("rows/cols not instantiated")
		var currentCodel = new Codel(c, currentRow, currentCol)
		codels(currentRow)(currentCol) = currentCodel
		
		var aboveCodel: Codel = null
        var leftCodel: Codel = null

        // Since we are reading the image left-to-right top-to-bottom, only the codels above and to the left of
        // the current codel (if they exist) will have been initialized
        if (currentRow > 0) {
          aboveCodel = codels(currentRow - 1)(currentCol)
        }
        if (currentCol > 0) {
          leftCodel = codels(currentRow)(currentCol - 1)
        }
		
		var parent: ColorBlock = null
        // Determine if the codel should be assigned to an existing color block
        if (aboveCodel != null && aboveCodel.hasSameColorAs(currentCodel)) {
          // If both codels are in the same block then we need to combine the two blocks
          if (leftCodel != null && leftCodel.hasSameColorAs(currentCodel)) {
            // Merge the two blocks
            aboveCodel.getParent().mergeColorBlock(leftCodel.getParent())
          }
          parent = aboveCodel.getParent()
        } else if (leftCodel != null && leftCodel.hasSameColorAs(currentCodel)) {
          parent = leftCodel.getParent()
        }
        
        if (parent == null) {
          parent = new ColorBlock(currentCodel.getColor())
        }
        parent.addCodel(currentCodel)
		
		currentCol += 1
		if (currentCol >= rows) {
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