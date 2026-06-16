package Physic;

public class BoolAttribute implements Attribute {
    boolean value;    @Override

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
                this.value = cast_value.value;
                break;
            case IntAttribute cast_value:
                this.value = cast_value.value > 0;
                break;
            case FloatAttribute cast_value:
                this.value = cast_value.value > 0;
                break;
            case StringAttribute cast_value:
                switch (cast_value.value) {
                    case "TRUE":
                    case "true":
                    case "T":
                    case "t":
                        this.value = true;
                        break;
                    case "FALSE":
                    case "false":
                    case "F":
                    case "f":
                        this.value = false;
                        break;
                    default:
                        //log
                }
                break;
            default:
                //log
        }
    }
}
