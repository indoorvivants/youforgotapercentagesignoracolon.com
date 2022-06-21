## On Scala.js 

Exceptions like this during linking phase:

```
Referring to non-existent class scodec.bits.ByteVector
  called from test$package$.hello()void
  called from static hello.main([java.lang.String)void
  called from core module module initializers
involving instantiated classes:
  test$package$
Referring to non-existent class scodec.bits.ByteVector
  called from test$package$.bytes()scodec.bits.ByteVector
  called from test$package$.hello()void
  called from static hello.main([java.lang.String)void
  called from core module module initializers
involving instantiated classes:
  test$package$
Referring to non-existent class scodec.bits.ByteVector
  called from test$package$.hello()void
  called from static hello.main([java.lang.String)void
  called from core module module initializers
involving instantiated classes:
  test$package$

...
```

## On Scala Native

Exceptions like this during **linking** phase:

```
[error] Found 5 missing definitions while linking
[error] Not found Top(scodec.bits.Bases$HexAlphabet)
[error] 	at /private/var/folders/my/drt5584s2w59ncgxgxpq7v040000gn/T/tmp.V0mIJQWj/test.scala:6
[error] Not found Top(scodec.bits.ByteVector)
[error] 	at /private/var/folders/my/drt5584s2w59ncgxgxpq7v040000gn/T/tmp.V0mIJQWj/test.scala:8
[error] Not found Top(scodec.bits.ByteVector$)
[error] 	at /private/var/folders/my/drt5584s2w59ncgxgxpq7v040000gn/T/tmp.V0mIJQWj/test.scala:1
[error] Not found Member(Top(scodec.bits.ByteVector$),D12fromValidHexL16java.lang.StringL29scodec.bits.Bases$HexAlphabetL22scodec.bits.ByteVectorEO)
[error] 	at /private/var/folders/my/drt5584s2w59ncgxgxpq7v040000gn/T/tmp.V0mIJQWj/test.scala:6
[error] Not found Member(Top(scodec.bits.ByteVector$),D22fromValidHex$default$2L29scodec.bits.Bases$HexAlphabetEO)
[error] 	at /private/var/folders/my/drt5584s2w59ncgxgxpq7v040000gn/T/tmp.V0mIJQWj/test.scala:6
Exception in thread "main" scala.scalanative.linker.LinkingException: Undefined definitions found in reachability phase
	at scala.scalanative.linker.Reach.fail(Reach.scala:976)
	at scala.scalanative.linker.Reach.reportMissing(Reach.scala:971)
```

## When Running Tests

```
sbt:cats-effect-testing> scalatestJS/test
[success] Total time: 0 s, completed Jun 21, 2022, 2:47:26 PM
```

Yes that's right! Your tests **won't run** and it will claim **success**!!
