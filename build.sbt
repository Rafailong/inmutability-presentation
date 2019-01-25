import sbt._

val commonSettings = Seq(
  scalaVersion := "2.12.6",
  organization := "me.rafaavila",
  scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions"),
  scalacOptions in Compile ++= Seq("-Ywarn-unused-import", "-Ypartial-unification", "-Xlint:_,-unused"),
  libraryDependencies ++= Seq(
    "io.github.stanch" %% "reftree" % "1.3.0",
    "com.lihaoyi" %% "ammonite" % "1.4.2" cross CrossVersion.full
  )
)

lazy val core = (project in file("core"))
  .settings(
    name := "core"
  )
  .settings(commonSettings)
