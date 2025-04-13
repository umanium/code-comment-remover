package comment_remover

import scala.util.matching.Regex

object CommentRemover:
  def removeComment(s: String): String =
    val commentRegex: Regex = """#.*""".r
    commentRegex.replaceAllIn(s, "")
