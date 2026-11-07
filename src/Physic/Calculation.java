package Physic;

public class Calculation {
  void Calculate(ArrayList<Attribute> input,
                 ArrayList<Attribute> output) {
    for (int ix = 0; ix < input.length(); ix++)
      Calculate(input[ix], output[ix]);
  }

  abstract void Calculate(Attribute input,
                          Attribute output);
}
