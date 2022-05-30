import $ivy.`com.lihaoyi::scalatags:0.11.1`
import $ivy.`com.github.japgolly.scalacss::ext-scalatags:1.0.0`
import $ivy.`com.indoorvivants::subatomic-builders:0.0.7`
import $ivy.`com.vladsch.flexmark:flexmark-ext-autolink:0.62.2`
import subatomic.builders.SyntaxHighlighting

interp.watch(os.pwd / "pages")

import scalacss.ScalatagsCss._
import subatomic.Markdown
import com.vladsch.flexmark.ast.AutoLink

import com.vladsch.flexmark.ext.autolink.AutolinkExtension

lazy val md = Markdown(
  AutolinkExtension.create()
)

def hash(ident: String) = {
  import scalatags.Text.all._
  a(Styles.hash, href := s"#$ident", "#")
}

case class Tool(name: String, link: String, page: os.Path) {
  import scalatags.Text.all._
  private val safeName = name
    .collect {
      case c if !c.isLetterOrDigit => '-'
      case c                       => c
    }
    .toLowerCase()

  val relPath = page.relativeTo(os.pwd)
  val editPath =
    "https://github.com/indoorvivants/youforgotapercentagesignoracolon.com/blob/main/" + relPath.toString

  def render = div(
    h2(
      id := safeName,
      hash(safeName),
      "I'm using ",
      a(Styles.toolLink, href := link, name)
    ),
    raw(md.renderToString(page)),
    p(a(Styles.editMeLink, href := editPath, "edit me on github"))
  )
}

val tools =
  List(
    Tool("SBT", "https://www.scala-sbt.org", os.pwd / "pages" / "sbt.md"),
    Tool(
      "Mill",
      "https://com-lihaoyi.github.io/mill/mill/Intro_to_Mill.html",
      os.pwd / "pages" / "mill.md"
    ),
    Tool(
      "Scala CLI",
      "https://scala-cli.virtuslab.org",
      os.pwd / "pages" / "scala-cli.md"
    )
  )

val site = {
  import scalatags.Text.all._
  import scalacss.ProdDefaults._

  html(
    head(
      scalatags.Text.tags2.title("You forgot a percentage sign or a colon"),
      meta(charset := "UTF-8"),
      meta(
        name := "viewport",
        attr("content") := "width=device-width, initial-scale=1"
      ),
      scalatags.Text.tags2.style(
        Styles.global.render[String]
      ),
      scalatags.Text.tags2.style(
        Styles.render[String]
      ),
      SyntaxHighlighting.HighlightJS.templateBlock(
        SyntaxHighlighting.HighlightJS.default
      )
    ),
    body(
      div(
        Styles.container,
        h1(Styles.driveItHome, "You forgot a percentage sign or a colon"),
        p(
          b("TL;DR:"),
          " when using Scala.js or Scala Native, " +
            "you must specify your dependencies slightly differently"
        ),
        tools.map(_.render),
        h2(id := "how-to-spot-it", hash("how-to-spot-it"), "How to spot it"),
        raw(md.renderToString(os.pwd / "pages" / "how-to-spot-it.md"))
      )
    )
  )
}

import scalacss.ProdDefaults._

object Styles extends StyleSheet.Inline {
  import dsl._
  import scalacss.internal.Attrs

  val container = style(
    margin.auto,
    maxWidth := 1024.px,
    padding := 10.px,
    height := 100.%%
  )

  val driveItHome = style(
    fontSize(4.rem)
  )

  private val hoverOpacity = mixin(
    color.black,
    opacity := 60.%%,
    textDecorationLine.none,
    &.hover - (
      opacity := 100.%%,
      textDecorationLine.none,
    )
  )

  val hash = style(
    hoverOpacity,
    fontSize(1.5.rem),
    marginRight(8.px)
  )

  val editMeLink = style(
    fontSize.small,
    hoverOpacity
  )

  val toolLink = style(
    &.visited - (textDecorationLine.none, color.black)
  )

  object global extends StyleSheet.Standalone {
    import dsl._
    import scalacss.internal.Attrs
    "html" - (
      height := 100.%%
    )

    "body" - (
      fontFamily :=! "Arial, Helvetica, sans-serif",
      // backgroundColor := c"#f2f6fc",
      color := black,
      fontSize := 1.2.rem
    )
  }

}

@main
def entry(destination: os.Path) =
  os.write.over(destination / "index.html", site.render, createFolders = true)
