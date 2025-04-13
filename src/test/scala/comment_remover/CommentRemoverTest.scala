package comment_remover

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should

class CommentRemoverTest extends AnyFunSpec with should.Matchers {
  describe("CommentRemover"):
    it("Should remove a python-style comment in a code string"):
      val code: String = """# this is a comment
        |
        |l = [x for x in range(10)] # this is also a comment""".stripMargin

      val expectedCode: String =
        """
          |
          |l = [x for x in range(10)] """.stripMargin

      val actualCode: String = CommentRemover.removeComment(code)
      actualCode shouldEqual expectedCode
}
