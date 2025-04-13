val scala3Version = "3.6.4"

lazy val root = project
  .in(file("."))
  .settings(
    name := "code-comment-remover",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalatest" %% "scalatest" % "latest.integration" % Test
  )
