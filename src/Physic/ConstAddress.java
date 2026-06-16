package Physic;

public class ConstAddress implements Address {
    Attribute value;

    @Override
    public void Read(Attribute input, Attribute output) {
        output = value;
    }

    @Override
    public void Write(Attribute input, Attribute output) {
        output = value;
    }
}
