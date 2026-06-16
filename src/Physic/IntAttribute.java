package Physic;

class IntAttribute implements Attribute {
    int value;

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
                this.value = cast_value.value ? 1 : 0;
                break;
            case IntAttribute cast_value:
                this.value = cast_value.value;
                break;
            case FloatAttribute cast_value:
                this.value = (int)cast_value.value;
                break;
            case StringAttribute cast_value:
                try {
                    this.value = Integer.parseInt(cast_value.value);
                } catch (NumberFormatException e) {
                    //log
                }
                break;
            default:
                //log
        }
    }
}
