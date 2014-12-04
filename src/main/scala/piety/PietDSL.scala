package piety

class PietDSL {
	abstract sealed class PietLine
	case class RunPietLine(c: Int, s: String)
	
	def PIET(c: Int, s: String) {
		Piety.processImage(s, c)
	}
}