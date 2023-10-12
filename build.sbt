ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "measures",
    idePackagePrefix := Some("com.ontometrics.measures")
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-funspec" % "3.2.17" % "test"

scalacOptions ++= Seq(
  "-feature",
  "-language:implicitConversions")