import sbt._
import Keys._

object MyProjectBuild extends Build {

  val buildSettings = Defaults.defaultSettings ++ Seq(
    name := "Spellchecker",
    version := "1.0",
    organization := "org.spellchecker",
    scalaVersion := "2.9.0-1",
    libraryDependencies ++= Seq(jazzy)
  )

  val jazzy =  "net.sf.jazzy" % "jazzy-core" % "0.5.2"
  val spellchecker = Project("Spellchecker", file("."), settings = buildSettings)

}
