Keys.`package` := {
  (library / Compile / Keys.`package`).value
  (examples / Compile / Keys.`package`).value
}

lazy val commonSettings = Seq(
  version := "0.0.1",
  scalaVersion := "2.12.13",
)

lazy val library = (project in file("library")).
  settings(commonSettings: _*).
  settings(
    name := "library",
  )

lazy val examples = (project in file("examples")).
  dependsOn(library).
  settings(commonSettings: _*).
  settings(
    name := "examples"
  )

