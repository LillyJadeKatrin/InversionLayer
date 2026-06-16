package Physic;

import java.util.ArrayList;
import java.util.Map;

class SingleAddress implements Address {
    String address;

    @Override
    public void Read(Attribute input, Attribute output) {
        if (input instanceof StructAttribute) {
            Map<String, Attribute> value = ((StructAttribute)input).value;
            if (value.containsKey(address)) {
                output = value.get(address);
            } else {
                //log
            }
        } else if (input instanceof ArrayAttribute) {
            ArrayList<Attribute> value = ((ArrayAttribute)input).value;
            try {
                int index = Integer.parseInt(address);
                if (value.size() > index) {
                    output = value.get(index);
                } else {
                    //log
                }
            } catch (NumberFormatException e) {
                //log
            }
        } else {
            //log
        }
    }

    @Override
    public void Write(Attribute input, Attribute output) {

    }
}
