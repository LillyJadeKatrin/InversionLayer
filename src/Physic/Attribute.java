package Physic;

public object Attribute {
private:
  HashMap<String, ArrayList<Object>> value;

  static Attribute Combine(String lname,
                           Attribute left,
                           String rname,
                           Attribute right) {
    Attribute new_att;
    new_att.Set(lname, 0, left);
    new_att.Set(rname, 0, right);
    return new_att;
  }
public:
  //construct
  //destruct
  Object Get(ArrayList<Index> path, bool array) {
    int plen = path.length();
    if (plen == 0)
      return this;
    if (plen == 1)
      return array ? Get(path[0].prop) : Get(path[0]);
    Object item = Get(path[0]);
    if (item instanceOf Attribute)
      return item.Get(path.sublist(1, plen), array);
    if (plen > 1) {
      // log
      // attempt to get property of non-attribute object
    }
    return item;
  }

  Object Get(ArrayList<Index> path) {
    return Get(path, false);
  }

  ArrayList<Object> Get(String prop) {
    if (!value.contains(prop)) {
      // log
      // property not found in attribute
      return new ArrayList<Object>(0);
    }
    return value.get(prop)
  }

  Object Get(Index index) {
    return Get(index.prop, index.offset);
  }

  Object Get(String prop, int offset) {
    ArrayList<Object> property = Get(prop);
    if (property.length() <= offset) {
      // log
      // index within property array not found
      return null;
    }
    return property.[offset];
  }

  void Set(ArrayList<Index> path, Object new_value) {
    int plen = path.length();
    if (plen == 0) {
      // log
      // this should be caught before now and done a reference copy instead of a deep copy
      value = new_value.value;
      type = new_value.type;
      id = new_value.id;
      return;
    }
    String prop = path[0].prop;
    int offset = path[0].offset;
    if (!value.contains(prop)) {
      value.put(new ArrayList<Object>(offset + 1);
    } else if (value.get(prop).length() <= offset) {
      value.get(prop).resize(offset + 1);
    }
    Object item = value.get(prop)[offset];
    if (plen == 1) {
      if (item.getClass() != new_value.getClass()) {
        // log
        // item is changing datatypes unexpectedly
      }
      item = new_value;
      return;
    }
    if (!(item instanceOf Attribute)) {
      // log
      // attempting to set property of non-attribute, creates a new attribute, SUPER dangerous path really please don't do this
      value.get(prop)[offset] = new Attribute();
      value.get(prop)[offset].Set(path.sublist(0, 1), item);
      item = value.get(prop)[offset];
    }
    item.Set(path.sublist(1, plen), new_value);
  }

  void Set(String prop,
           int offset,
           Object new_value) {
    Set(Index.Str2Index(prop, offset), new_value);
  }

  HashMap<String, int> Describe() {
    return value.stream().map(ArrayList::length)
      .collect(Collectors.toCollection(HashMap<String, int>::new));
  }

  int Describe(String prop) {
    if (!value.contains(prop))
      return -1;
    return value.get(prop).length();
  }

  int Describe(ArrayList<Index> path) {
    return Get(path, true).length();
  }

  void Act(String calcname,
           Attribute input,
           Attribute output) {
    Calculation calc = Global.CalcLookup(calcname);
    if (calc == null) {
      // log
      // global CalcLookup should handle this but better safe than sorry
      calc = new NoCalculation();
    }
    Attribute in = Combine("input", input,
                           "this", this);
    Attribute out = Combine("output", output,
                            "this", this);
    calc.Calculate(in, out);
  }

  void Act(String calcname,
           ArrayList<Attribute> input,
           ArrayList<Attribute> output) {
    Attribute out;
    for (int ix = 0; ix < input.length(); ix++) {
      Act(calcname, input[ix], out);
      output[ix] = out;
    }
  }

  void Act(ArrayList<Index> calcpath,
           Attribute input,
           Attribute output) {
    Object calcname = Get(calcpath);
    if (!(calcname instanceOf String)) {
      // log
      // not a valid calculation name
      return;
    }
    Act(Get(calcpath), input, output);
  }

  void Act(ArrayList<Index> calcpath,
           ArrayList<Attribute> input,
           ArrayList<Attribute> output) {
    Object calcname = Get(calcpath);
    if (!(calcname instanceOf String)) {
      // log
      // not a valid calculation name
      return;
    }
    Act(Get(calcpath), input, output);
  }
}
