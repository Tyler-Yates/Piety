Piety
=====

CS 345H Final Project to implement the [Piet](http://en.wikipedia.org/wiki/Esoteric_programming_language#Piet) programming language in Scala. A [presentation](https://docs.google.com/presentation/d/1hhwky73h1fhp_I9xM_D9acZCuDa9-0si0MR9dX6ohdk/edit?usp=sharing) about this project is hosted on Google Drive.

##Requirements

[Scala 2.11.4](http://www.scala-lang.org/download/)

[JDK 8u25](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

##Compiling and Running

####Compiling
* Open a terminal in the Piety root directory
* Use `scalac -d [where to put compiled files] src/main/scala/piety/*.scala`
 
####Running
Open a terminal in the directory where the compiled files were saved

#####External DSL (Image -> Program Execution)
* Use `scala piety.Piety [program file path] [codel size]` to run the program

#####Generating Internal DSL (Image -> Piet DSL Text)
* Use `scala piety.Compiler [program file path] [codel size] [output file path]` to compile image to a scala file

#####Internal DSL (Piet DSL Text -> Program Execution)
* Use `scala [name of .scala file]` to run the Internal DSL text
* The compiled scala file must be in the same directory as the PietDSL class

####Valid Programs
* Programs passed to Piety must conform to the Piet [specification](http://www.dangermouse.net/esoteric/piet.html).
* Piety accepts programs in PNG and GIF formats. Other formats may work but these are the only ones explicitly supported.
* PNG images should NOT contain alpha as this alters the colors defined in the specification
