Piety
=====

CS 345H Final Project to implement the [Piet](http://en.wikipedia.org/wiki/Esoteric_programming_language#Piet) programming language in Scala.

##Requirements

[Scala 2.11.4](http://www.scala-lang.org/download/)

[JDK 8u25](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

##Compiling and Running

####Compiling
* Open a terminal in the Piety root directory
* Use `scalac -d [where to put compiled files] src/main/scala/piety/*.scala`
 
####Running
* Open a terminal in the directory where the compiled files were saved
* Use `scala piety.Piety [program file path] [codel size]` to run the program

####Valid Programs
* Programs passed to Piety must conform to the Piet [specification](http://www.dangermouse.net/esoteric/piet.html).
* Piety accepts programs in PNG and GIF formats. Other formats may work but these are the only ones explicitly supported.
* PNG images should NOT contian alpha as this alters the colors defined in the specification
