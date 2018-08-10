organization := "de.arraying"
version := "0.1"
scalaVersion := "2.12.6"

lazy val Scale = (project in file("."))
  .settings(
    name := "Scale",
    resolvers += Resolver.JCenterRepository,
    libraryDependencies += "net.dv8tion" % "JDA" % "3.7.1_392"
  )