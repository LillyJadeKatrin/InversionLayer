package Physic;

public class Address {
private:
  class AddressIterator {
  public:
    ArrayList<Index> start;
    int org_inc, dest_inc, count;
    Address nest;
  }

  HashMap<ArrayList<Index>, AddressIterator> var_map;
  HashMap<ArrayList<Index>, Object> const_map;
public:
  // construct
  // destruct
  void Translate(Attribute input, Attribute output) {
    var_map.forEach((key, value) -> {
      if (const_map.contains(key)) {
        // log
        // value will be immediately overwritten, bad design
        break;
      }
      if (value.org_inc == 0 && count == 0) {
        // log
        // this will create an infinitely growing list
        break;
      }
      if (dest_inc == 0 && count != 1)
        // log
        // this will repeatedly overwrite the same value
        // not strictly wrong, but wasteful and pointless
      if (count == 1) {
        // technically covered by the loop but more efficient for a common use case
        if (value.nest == null) {
          output.Set(key, input.Get(value.start));
        } else {
          value.nest.Translate(input.Get(value.start), output.Get(key));
        }
      } else { // if count != 1
        int prop_size = input.GetSize(value.start);
        org_offset = value.start(value.start.length() - 1).offset;
        if (prop_size <= org_offset) {
          // log
          // start of range is outside array
          break;
        }
        int max_count = count;
        if (org_inc > 0) {
          max_count = (prop_size - org_offset) / org_inc + 1;
        } else if (org_inc < 0) {
          max_count = -org_offset / org_inc + 1;
        }
        if (count != 0 && count < max_count)
          max_count = count;
        ArrayList<Index> org_itr = new ArrayList<Index>(value.start);
        ArrayList<Index> dest_itr = new ArrayList<Index>(key);
        for (int ix = 0; ix < max_count; ix++) {
          if (value.nest == null) {
            output.Set(key, input.Get(value.start));
          } else {
            value.nest.Translate(input.Get(value.start), output.Get(key));
          }
          org_itr[org_itr.length() - 1].offset += org_inc;
          dest_itr[dest_itr.length() - 1].offset += dest_inc;
        }
      }
    }
    const_map.forEach((key, value) -> {
      output.Set(key, value);
    }
  }
}
