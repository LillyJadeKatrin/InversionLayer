package Physic;

import java.util.ArrayList;
import java.util.Map;

public class Entity {
    enum LocationType {
        HOME, ROOM, LINK
    }
    enum ActionType {
        CREATE, MODIFY, DELETE
    }
    enum StateScope {
        PERSISTENT, // Remains in state as long as entity exists and is saved if entity is saved
        TICK, // Removed from state once state is finished processing
        IMMEDIATE // Removed from state immediately after its next use
    }
    enum OpCode {
        // The first five are +, -, *, /, and % respectively.
        // CMP returns 1 if in1 > in2, -1 if in1 < in2, 0 if in1 == in2.
        // SKP and EXT skip the next instruction and exit the function
        // if in1 == in2.
        ADD, SUB, MUL, DEL, MOD, CMP, SKP, EXT;
    }
    class Operation {
        // Indexes in the calculation table
        int inl, inr, out;
        OpCode opcode;
    }
    class StateItem {
        int current_value;
        StateScope scope;
        // Really unsure about limiting this to ints, but unsure how else
        // to store this
        Map<Integer, Integer> reg;
        ArrayList<Operation> formula;
        void Calculate() {
            // populate reg with external values?
            for (int ix = 0; ix < formula.size(); ix++) {
                Operation op = formula.get(ix);
                switch (op.opcode) {
                    case ADD:
                        reg.put(op.out, reg.get(op.inl) + reg.get(op.inr));
                        break;
                    case SUB:
                        reg.put(op.out, reg.get(op.inl) - reg.get(op.inr));
                        break;
                    case MUL:
                        reg.put(op.out, reg.get(op.inl) * reg.get(op.inr));
                        break;
                    case DEL:
                        reg.put(op.out, reg.get(op.inl) / reg.get(op.inr));
                        break;
                    case MOD:
                        reg.put(op.out, reg.get(op.inl) % reg.get(op.inr));
                        break;
                    case CMP:
                        int l = reg.get(op.inl);
                        int r = reg.get(op.inr);
                        if (l > r)
                            reg.put(op.out, 1);
                        else if (l < r)
                            reg.put(op.out, -1);
                        else reg.put(op.out, 0);
                        break;
                    case SKP:
                        if (reg.get(op.inl).equals(reg.get(op.inr))) ix++;
                        break;
                    case EXT:
                        if (reg.get(op.inl).equals(reg.get(op.inr))) ix = formula.size();
                }
            }
            current_value = reg.get(0);
            reg.clear();
        }
    }
    class Coordinate {
        double x, y;
    }
    class Condition<T> {
        ArrayList<Pair<T>> range;
        boolean is_positive;
    }
    class PossibleReaction {

    }

    // Location
    LocationType location_type;
    Entity location_base_entity;
    Coordinate location_coordinate;
    ArrayList<Entity> location_backreferences;
    // Dimension
    int dimension_order;
    ArrayList<ArrayList<ArrayList<ArrayList<Coordinate>>>> dimensions;
    // State
    // Separating this feels like the dumbest way of doing this but fuck it
    Map<String, String> stateEnums;
    Map<String, Integer> stateInts;
    Map<String, Float> stateFloats;
    // Reaction
    ArrayList<>
}
