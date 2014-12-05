package piety

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test

import java.io.ByteArrayOutputStream

class DSLTest extends AssertionsForJUnit {

  @Test def testHelloWorld(): Unit = {
    testDSL(HelloWorldDSL, "Hello, world!")
  }

  @Test def testAlphabet(): Unit = {
    testDSL(AlphabetDSL, "abcdefghijklmnopqrstuvwxyz")
  }

  def testDSL(dsl: DSL, expectedOutput: String) = {
    val stream = new ByteArrayOutputStream()
    Console.withOut(stream) {
      dsl.run()
      assertEquals(expectedOutput, stream.toString().trim())
    }
  }
}

trait DSL extends PietDSL {
  def run(): Unit
}

object HelloWorldDSL extends DSL {
  def run(): Unit = {
    BLANK_SPACE
    ROWS(25)
    COLUMNS(83)
    nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; dg; ng; ng;
    nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng;
    nc; nc; bb; nc; nc; dc; ng; ng; bb; ng; ng; dg; ny; ny; bb; ny; ny; dy; nr; nr; bb; nr; nr; dr; nm; nm; bb; nm; nm; dm; nb; nb; bb; nb; nb; db; nc; nc; bb; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; bb; ny; ny; dy; nr; nr; bb; nr; nr; dr; nm; nm; bb; nm; nm; dm; nb; nb; bb; nb; nb; db; nc; nc; bb; nc; nc; dc; dg; dg; ng; dg; dg;
    nc; bb; ny; bb; nc; dc; ng; bb; ny; bb; ng; dg; ny; bb; dy; bb; ny; dy; nr; bb; ny; bb; nr; dr; nm; bb; ny; bb; nm; dm; nb; bb; ny; bb; nb; db; nc; bb; ny; bb; nc; dc; ng; ng; ng; ng; ng; dg; ny; bb; dy; bb; ny; dy; nr; bb; nr; bb; nr; dr; nm; bb; ny; bb; nm; dm; nb; bb; ny; bb; nb; db; nc; bb; ny; bb; nc; dc; ng; bb; dg; bb; ng;
    nc; ny; dy; ny; nc; dc; ng; ny; dy; ny; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; ny; dy; ny; nb; db; nc; ny; dy; ny; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; ny; dy; ny; nb; db; nc; ny; dy; ny; nc; dc; bb; ny; ny; ny; bb;
    nc; bb; ny; bb; nc; dc; ng; bb; ny; bb; ng; dg; ny; bb; dy; bb; ny; dy; nr; bb; ny; bb; nr; dr; nm; bb; nm; bb; nm; dm; nb; bb; ny; bb; nb; db; nc; bb; ny; bb; nc; dc; ng; bb; ng; bb; ng; dg; ny; bb; ny; bb; ny; dy; nr; bb; nr; bb; nr; dr; nm; bb; ny; bb; nm; dm; nb; bb; ny; bb; nb; db; nc; bb; ny; bb; nc; dc; ng; bb; ny; bb; ng;
    nc; nc; bb; nc; nc; dc; ng; ng; bb; ng; ng; dg; ny; ny; bb; ny; ny; dy; nr; nr; bb; nr; nr; dr; nm; nm; bb; nm; nm; dm; nb; nb; bb; nb; nb; db; nc; nc; bb; nc; nc; dc; ng; ng; bb; ng; ng; dg; ny; ny; bb; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; bb; nm; nm; dm; nb; nb; bb; nb; nb; db; nc; nc; bb; nc; nc; dc; ng; ng; bb; ng; ng;
    nc; nb; nc; nb; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng;
    nc; dc; nb; dc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; db; nb; nb; db; nc; nb; nc; nb; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nb; nc; nb; nc; dc; ng; ng; ng; ng; ng;
    nc; nb; nc; nb; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nb; dc; dc; dc; nb; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nb; dc; nc; dc; nb; dc; ng; ng; ng; ng; ng;
    nc; dc; nb; dc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nc; nb; nb; db; nc; nb; dc; nb; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; db; nb; nb; db; nc; nb; dc; nb; nc; dc; ng; ng; ng; ng; ng;
    nc; nb; nc; nb; nc; dc; ng; ng; dg; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nc; nb; nc; nb; db; nc; nc; nb; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nb; nc; nc; dc; ng; ng; ng; ng; ng;
    nc; nc; nb; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; dy; ny; ny; dy; nr; nr; dr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nc; nb; dc; nb; nc; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; dr; nr; nr; dr; nm; nm; dm; nm; nm; dm; nb; nb; db; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng;
    nc; nb; nc; nb; nc; dc; ng; ng; dg; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nc; nb; nc; nb; db; nc; nc; nb; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nb; nc; nc; dc; ng; ng; ng; ng; ng;
    nc; dc; nb; dc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nc; nb; nb; db; nc; nb; dc; nb; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; db; nb; nb; db; nc; nb; dc; nb; nc; dc; ng; ng; ng; ng; ng;
    nc; nb; nc; nb; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nb; dc; dc; dc; nb; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nb; dc; nc; dc; nb; dc; ng; ng; ng; ng; ng;
    nc; dc; nb; dc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; db; nb; nb; db; nc; nb; nc; nb; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nb; nc; nb; nc; dc; ng; ng; ng; ng; ng;
    nc; nb; nc; nb; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng;
    nc; nc; bb; nc; nc; dc; ng; ng; bb; ng; ng; dg; ny; ny; bb; ny; ny; dy; nr; nr; bb; nr; nr; dr; nm; nm; bb; nm; nm; dm; nb; nb; bb; nb; nb; db; nc; nc; bb; nc; nc; dc; ng; ng; bb; ng; ng; dg; ny; ny; bb; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; bb; nm; nm; dm; nb; nb; bb; nb; nb; db; nc; nc; bb; nc; nc; dc; ng; ng; bb; ng; ng;
    nc; bb; ny; bb; nc; dc; ng; bb; ny; bb; ng; dg; ny; bb; dy; bb; ny; dy; nr; bb; ny; bb; nr; dr; nm; bb; nm; bb; nm; dm; nb; bb; ny; bb; nb; db; nc; bb; ny; bb; nc; dc; ng; bb; ng; bb; ng; dg; ny; bb; ny; bb; ny; dy; nr; bb; nr; bb; nr; dr; nm; bb; ny; bb; nm; dm; nb; bb; ny; bb; nb; db; nc; bb; ny; bb; nc; dc; ng; bb; ny; bb; ng;
    nc; ny; dy; ny; nc; dc; ng; ny; dy; ny; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; ny; dy; ny; nb; db; nc; ny; dy; ny; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; ny; dy; ny; nb; db; nc; ny; dy; ny; nc; dc; bb; ny; ny; ny; bb;
    nc; bb; ny; bb; nc; dc; ng; bb; ny; bb; ng; dg; ny; bb; dy; bb; ny; dy; nr; bb; ny; bb; nr; dr; nm; bb; ny; bb; nm; dm; nb; bb; ny; bb; nb; db; nc; bb; ny; bb; nc; dc; ng; ng; ng; ng; ng; dg; ny; bb; dy; bb; ny; dy; nr; bb; nr; bb; nr; dr; nm; bb; ny; bb; nm; dm; nb; bb; ny; bb; nb; db; nc; bb; ny; bb; nc; dc; ng; bb; dg; bb; ng;
    nc; nc; bb; nc; nc; dc; ng; ng; bb; ng; ng; dg; ny; ny; bb; ny; ny; dy; nr; nr; bb; nr; nr; dr; nm; nm; bb; nm; nm; dm; nb; nb; bb; nb; nb; db; nc; nc; bb; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; bb; ny; ny; dy; nr; nr; bb; nr; nr; dr; nm; nm; bb; nm; nm; dm; nb; nb; bb; nb; nb; db; nc; nc; bb; nc; nc; dc; dg; dg; ng; dg; dg;
    nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng;
    nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; ng; ng; ng; dg; ny; ny; ny; ny; ny; dy; nr; nr; nr; nr; nr; dr; nm; nm; nm; nm; nm; dm; nb; nb; nb; nb; nb; db; nc; nc; nc; nc; nc; dc; ng; ng; dg; ng; ng;
    PIET()
  }
}

object AlphabetDSL extends DSL {
  def run(): Unit = {
    BLANK_SPACE
    ROWS(12)
    COLUMNS(45)
    dr; dr; dr; dr; dr; dr; lr; lr; nr; nr; nr; dr; dy; dy; dy; dg; lg; lc; ly; lm; db; db; db; db; db; db; db; db; lb; lg; lc; lc; nc; db; dy; nc; dr; nr; dr; nr; dr; lr; nc; dc; nc;
    dr; dr; dr; dr; dr; dr; lr; lr; nr; nr; lg; dr; dy; dy; dy; nm; nb; lb; db; lc; db; db; db; db; db; db; db; db; dm; lb; lc; lc; dc; lg; dg; ny; lr; lg; dg; ny; lr; bb; lr; bb; dc;
    dr; dr; dr; dr; dr; dr; lr; lr; nr; nr; nb; dr; dy; dy; dy; nm; lb; db; lc; dc; db; db; db; db; db; db; db; db; lb; db; lc; lc; lg; dg; ly; dy; nr; ly; dy; nr; bb; lb; lb; lb; bb;
    dr; dr; dr; dr; dr; dr; lr; lr; dc; bb; bb; bb; bb; dc; nb; ww; nm; lb; db; lc; db; db; db; db; db; db; db; db; dm; lb; lc; bb; dc; lg; dg; ny; ly; dm; lb; lc; bb; bb; lb; bb; dg;
    dr; dr; dr; dr; dr; lr; lr; lr; bb; bb; nc; nc; bb; bb; dc; nb; lb; db; lc; dc; db; db; db; db; db; db; db; db; lb; db; nc; dc; lg; dg; ly; dy; ny; lg; dg; ly; dy; ny; bb; dg; nb;
    dr; dr; dr; dr; dr; lr; lr; lr; bb; nc; bb; bb; nc; bb; nc; dc; nb; lb; db; lc; db; db; db; db; db; db; db; db; dm; lb; db; lc; dc; lg; dg; ly; lg; bb; bb; bb; bb; bb; bb; bb; dc;
    dr; dr; dr; dr; dr; lr; lr; lr; bb; nc; bb; bb; nc; bb; dg; nc; lb; db; lc; dc; db; db; db; db; db; db; db; db; lb; db; lc; dc; lg; dg; ly; dy; ng; bb; nc; nc; nc; nc; nc; bb; nc;
    dr; dr; dr; dr; dr; lr; lr; lb; bb; nc; nc; nc; nc; bb; ng; dg; nc; lb; db; lc; db; db; db; db; db; db; db; db; dm; lb; db; lc; dc; lg; dg; ly; lc; lr; bb; bb; bb; nc; bb; bb; dg;
    dr; dr; dr; dr; dr; lr; lr; nc; bb; nc; bb; bb; nc; bb; dy; ng; lb; db; lc; dc; lg; dg; ly; dy; lr; dr; lm; dm; lb; db; lc; dc; lg; dg; ly; dy; nc; nm; bb; bb; nc; bb; bb; dg; ng;
    dr; dr; dr; dr; dr; lr; lr; lb; bb; nc; bb; bb; nc; bb; ny; dy; ng; lb; db; lc; dc; lg; dg; ly; dy; lr; dr; lm; dm; lb; db; lc; dc; lg; dg; ly; lb; bb; bb; nc; bb; bb; bb; bb; dy;
    dr; dr; dr; dr; dr; lr; lb; ng; bb; nc; bb; bb; nc; bb; nb; ny; lb; db; lc; dc; lg; dg; ly; dy; lr; dr; lm; dm; lb; db; lc; dc; lg; dg; ly; dy; ny; bb; nc; nc; nc; nc; nc; bb; ny;
    dr; dr; dr; dr; dr; lr; ng; lb; bb; bb; bb; bb; bb; bb; dc; nb; ny; lb; db; lc; dc; lg; dg; ly; dy; lr; dr; lm; dm; lb; db; lc; dc; lg; dg; ly; dy; bb; bb; bb; bb; bb; bb; bb; nb;
    PIET()
  }
}