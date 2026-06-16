package Physic;

import java.util.Map;

class StructAttribute implements Attribute {
    Map<String, Attribute> value;

    @Override
    public Attribute get(String index) {
        if (value.containsKey(index)) {
            return value.get(index);
        } else {
            // log
            return null;
        }
    }

    @Override
    public void set(String index, Attribute new_value) {
        value.put(index, new_value);
    }
}
