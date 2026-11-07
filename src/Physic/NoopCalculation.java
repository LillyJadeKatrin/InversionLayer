package Physic;

class NoopCalculation implements Calculation {
public:
  void Calculate(Attribute input,
                 Attribute output) {
    output = input;
  }
}
