Same rules as with Mill!

For example, this set of directives will work for all platforms (note the **double set of `::`**):

```scala
//> using scala "3.1.2"
//> using lib "org.scodec::scodec-bits::1.1.32"
```

(allowing you to run it with `--js` and `--native` flags, or without flags at all)

Whereas this specification will only work on the JVM:

```
//> using scala "3.1.2"
//> using lib "org.scodec::scodec-bits:1.1.32"
```
