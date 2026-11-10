package Physic;

class NoCalculation implements Calculation {
public:
  void Calculate(Attribute input,
                 Attribute output) {
    output = input;
  }
}
