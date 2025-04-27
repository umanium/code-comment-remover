package comment_remover

import scala.util.matching.Regex

enum Style:
  case Python, C, Go

object CommentRemover:
  def removeComment(s: String, style: Style): String =
    val regexes: Seq[Regex] = style match
      case Style.Python => Seq("""#(?!\s*noqa).*(\z|\n+\s*)""".r)
      case Style.C => Seq("""/\*[\S\s]*\*/(\z|\n+\s*)""".r, """//.*(\z|\n+\s*)""".r)
      case Style.Go => Seq("""/\*[\S\s]*\*/(\z|\n+\s*)(?!package|const|func|type|var)""".r, """//(?!\s*go:).*(\z|\n+\s*)(?!package|const|func|type|var)""".r)

    regexes.foldLeft(s)((st, regex) => regex.replaceAllIn(st, "")).trim
