package comment_remover

import scala.util.matching.Regex

enum Style:
  case Python, C

object CommentRemover:
  def removeComment(s: String, style: Style): String =
    val regexes: Seq[Regex] = style match
      case Style.Python => Seq("""#.*\s*""".r)
      case Style.C => Seq("""/\*[\S\s]*\*/\s*""".r, """//.*\s*""".r)

    regexes.foldLeft(s)((st, regex) => regex.replaceAllIn(st, "")).trim
