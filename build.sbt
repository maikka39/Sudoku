import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "sudoku"

lazy val root = (project in file("."))
  .settings(
    name := "Sudoku",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.scalamock" %% "scalamock" % "5.2.0" % Test,
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
