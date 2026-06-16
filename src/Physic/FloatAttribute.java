package Physic;

class FloatAttribute implements Attribute {
    float value;

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
                this.value = cast_value.value ? 1f : -1f;
                break;
            case IntAttribute cast_value:
                this.value = cast_value.value;
                break;
            case FloatAttribute cast_value:
                this.value = cast_value.value;
                break;
            case StringAttribute cast_value:
                try {
                    this.value = Float.parseFloat(cast_value.value);
                } catch (NumberFormatException e) {
                    //log
                }
                break;
            default:
                //log
        }
    }
}
