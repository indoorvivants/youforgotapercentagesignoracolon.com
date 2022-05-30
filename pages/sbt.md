When using Scala.js or Scala Native, make sure your dependencies are specified using `%%%`, for 
example 

```
// Works for JVM, Scala.js and Scala Native
libraryDependencies += "org.scodec" %%% "scodec-bits" % "1.1.32"
```

Whereas in JVM projects you would see dependencies specified with `%%`:

```
// JVM ONLY!
libraryDependencies += "org.scodec" %% "scodec-bits" % "1.1.32"
```

_Hot tip_: if you have Scala.js or Scala Native in _any_ of your SBT modules, you can use `%%%`
for **every** dependency. This way both JVM and SJS/SN dependencies will work perfectly.
