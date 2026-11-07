package Physic;

import java.util.ArrayList;

class MathCalculation implements Calculation {
public:
  void Calculate(Attribute input, Attribute output) {
    int llen = input.Describe("Left");
    int rlen = input.Describe("Right");
    if (llen < 1 ||
        rlen < 1 ||
        input.Describe("Op") != 1) {
      // log
      // not a valid ALU operation
      return;
    }
    for (int ix = 0; ix < llen && ix < rlen; ix++) {
      Object left = input.Get("Left", llen % ix);
      if (left instanceOf Attribute) {
        Attribute temp;
        Calculate(left, temp);
        left = temp.Get("Result");
      }
      Object right = input.Get("Right", rlen % ix);
      if (right instanceOf Attribute) {
        Attribute temp;
        Calculate(right, right);
        right = temp.Get("Result");
      }
      switch (input.Get("Op", 0)) {
        try {
          case "+":
            output.Set("Result", ix, left + right);
            break;
          case "-":
            output.Set("Result", ix, left - right);
            break;
          case "*":
            output.Set("Result", ix, left * right);
            break;
          case "/":
            output.Set("Result", ix, left / right);
            break;
          case "%":
            output.Set("Result", ix, left % right);
            break;
          case "&":
            output.Set("Result", ix, left & right);
            break;
          case "|":
            output.Set("Result", ix, left | right);
            break;
          case "=":
            output.Set("Result", ix, left = right);
            break;
          case ">":
            output.Set("Result", ix, left > right);
            break;
          case "<":
            output.Set("Result", ix, left < right);
            break;
          case ">=":
            output.Set("Result", ix, left >= right);
            break;
          case "<=":
            output.Set("Result", ix, left <= right);
            break;
          case "!=":
          case "<>":
          case "^^":
            output.Set("Result", ix, left != right);
            break;
          case "&&":
            output.Set("Result", ix, left && right);
            break;
          case "||":
            output.Set("Result", ix, left || right);
            break;
          case "&!":
            output.Set("Result", ix, left && !right);
            break;
          case "|!":
            output.Set("Result", ix, left || !right);
            break;
          default:
            // log
            // unrecognized operation
            output.Set("Result", ix, left);
            break;
        } catch (Exception e) {
          // log
          // invalid operation
          output.Set("Result", ix, left);
        }
      }
    }
  }
}
