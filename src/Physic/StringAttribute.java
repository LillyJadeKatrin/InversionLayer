package Physic;

class StringAttribute implements Attribute {
    String value;

    @Override
    public Attribute get(String index) {
        if (!index.isEmpty()) {
            //log
        }
        return this;
    }

    @Override
    public void set(String index, Attribute new_value) {
        if (!index.isEmpty()) {
            //log
        }
        switch (new_value) {
            case BoolAttribute cast_value:
                this.value = cast_value.value ? "TRUE" : "FALSE";
                break;
            case IntAttribute cast_value:
                this.value = String.valueOf(cast_value.value);
                break;
            case FloatAttribute cast_value:
                this.value = String.valueOf(cast_value.value);
                break;
            case StringAttribute cast_value:
                this.value = cast_value.value;
                break;
            default:
                //log
        }
    }
}
