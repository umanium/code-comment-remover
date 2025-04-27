package comment_remover

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should

class CommentRemoverTest extends AnyFunSpec with should.Matchers:
  describe("CommentRemover"):
    it("Should remove a python-style comment in a code string"):
      val code: String = """import abc # noqa: E731
        |# this is a comment
        |
        |l = [x for x in range(10)] # this is also a comment""".stripMargin

      val expectedCode: String =
        """import abc # noqa: E731
          |l = [x for x in range(10)]""".stripMargin

      val actualCode: String = CommentRemover.removeComment(code, Style.Python)
      actualCode shouldEqual expectedCode

    it("Should remove a C-style comment in a code string"):
      val code: String =
        """/*
          | * Calculate the factorial of n
          | */
          |function factorial(n) {
          |  // check n is valid
          |  if (typeof n !== 'number' || !Number.isInteger(n) || n < 0) {
          |    throw new Error('n must be a non-negative integer');
          |  }
          |
          |  // base case
          |  if ((n === 1) || (n === 0)) {
          |    return 1;
          |  }
          |  // other cases
          |  return n * factorial(n - 1)
          |}""".stripMargin

      val expectedCode: String =
        """function factorial(n) {
          |  if (typeof n !== 'number' || !Number.isInteger(n) || n < 0) {
          |    throw new Error('n must be a non-negative integer');
          |  }
          |
          |  if ((n === 1) || (n === 0)) {
          |    return 1;
          |  }
          |  return n * factorial(n - 1)
          |}""".stripMargin

      val actualCode: String = CommentRemover.removeComment(code, Style.C)
      actualCode shouldEqual expectedCode

    it("should remove comments but leave Go doc comment"):
      val code: String =
        """// This is a package
          |package os
          |
          |/*
          | * Calculate the factorial of n
          | */
          |func factorial(n int) { //go:build ignore
          |  // check n is valid
          |  if (typeof n !== 'number' || !Number.isInteger(n) || n < 0) {
          |    throw new Error('n must be a non-negative integer');
          |  }
          |}""".stripMargin

      val expectedCode: String =
        """// This is a package
          |package os
          |
          |/*
          | * Calculate the factorial of n
          | */
          |func factorial(n int) { //go:build ignore
          |  if (typeof n !== 'number' || !Number.isInteger(n) || n < 0) {
          |    throw new Error('n must be a non-negative integer');
          |  }
          |}""".stripMargin

      val actualCode: String = CommentRemover.removeComment(code, Style.Go)
      actualCode shouldEqual expectedCode
