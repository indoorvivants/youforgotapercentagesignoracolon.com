With Scala.js or Scala Native modules, you need to make sure that your dependency 
specification contains **two sets of `::`**.

For example, this will work for all the platforms:

```scala 
def ivyDeps = Agg(ivy"com.lihaoyi::scalatags::0.6.7")
```

but this will work only for JVM:

```scala 
def ivyDeps = Agg(ivy"com.lihaoyi::scalatags:0.6.7")
```
