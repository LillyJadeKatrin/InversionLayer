package Physic;

import java.util.ArrayList;
import java.util.Map;

Map<String, Entity> ENTITY_DIRECTORY;
Map<String, Calculaton> CALCULATION_DIRECTORY;

interface Attribute {};
class BoolAttribute implements Attribute {
    boolean value;
}
class IntAttribute implements Attribute {
    int value;
}
class FloatAttribute implements Attribute {
    float value;
}
class StringAttribute implements Attribute {
    String value;
}
class EntityAttribute implements Attribute {
    Entity value;
}
class ArrayAttribute implements Attribute {
    ArrayList<Attribute> value;
}
class StructAttribute implements Attribute {
    Map<String, Attribute> value;
}

interface Address {}
class ConstAddress {
    Attribute value;
}
class SingleAddress {
    String address;
}
class ArrayAddress {
    ArrayList<Address> address;
}
class StructAddress {
    Map<String, Address> address;
}

class Operation {
    BoolAttribute condition;
    Address input;
    Address output;
    Calculation calculation;
}
interface Calculation {
    void Calculate(Attribute input, Attribute output);
}
class CustomCalculation implements Calculation {
    ArrayList<Operation> steps;
    void Calculate(Attribute input, Attribute output) {
        Attribute step_input, step_output;
        for (Operation step : steps) {
            if (step.condition.value) {
                step.input.Read(input, step_input);
                step.calculation.Calculate(step_input, step_output);
                step.output.Write(output, step_output);
            }
        }
    }
}

public class Entity {
    Attribute data;
}
