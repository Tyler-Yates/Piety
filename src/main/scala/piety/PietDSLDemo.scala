package piety

object PietDSLDemo extends PietDSL {
	def main(args: Array[String]): Unit = {
		ROWS (2)
		COLUMNS (2)
		dg;dg
		bb;ww
		PIET
	}
}