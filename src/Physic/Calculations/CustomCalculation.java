package Physic;

import java.util.ArrayList;

class CustomCalculation implements Calculation {
  ArrayList<Operation> steps;
  void Calculate(Attribute input,
                 Attribute output) {
    Attribute step_in, step_out;
    for (Operation step : steps) {
      if (step.condition == null ||
          input.Get(step.condition)) {
        step.input.Translate(input,
                             step_in);
        Attribute owner = input.Get("this", 0);
        if (owner == null) {
          // log
          // calculate called without a this, not sure yet how you did this so bravo
          owner = new Attribute();
        }
        owner.Act(step.calcname, step_in, step_out);
        step.output.Translate(step_out,
                              output);
      }
    }
  }
}
