package Physic;

import java.util.ArrayList;

class CustomCalculation implements Calculation {
    ArrayList<Operation> steps;
    void Calculate(Attribute input, Attribute output) {
        Attribute step_input, step_output;
        for (Operation step : steps) {
            if (step.condition.value) {
                step.input.Read(input, step_input);
                step.calculation.Calculate(step_input, step_output);
                step.output.Write(step_output, output);
            }
        }
    }
}
