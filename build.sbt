import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "dev.maik"
ThisBuild / organizationName := "maik.dev"

lazy val root = (project in file("."))
  .settings(
    name := "Sudoku",
    libraryDependencies ++= Seq(
      scalaTest                 % Test,
      "org.scalamock"          %% "scalamock" % "5.2.0" % Test,
    )
  )
