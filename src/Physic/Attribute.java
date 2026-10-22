package Physic;

public object Attribute {
private:
  HashMap<String, ArrayList<Object>> value;
public:
  //construct
  //destruct
  Object Get(ArrayList<Index> path) {
    int plen = path.length();
    if (plen == 0)
      return this;
    String prop = path[0].prop;
    int offset = path[0].offset;
    if (!value.contains(prop)) {
      // log
      // property not found in attribute
      return null;
    }
    if (value.get(prop).length() <= offset) {
      // log
      // index within property array not found
      return null;
    }
    Object item = value.get(prop).[offset];
    if (item instanceOf Attribute)
      return item.Get(path.sublist(1, plen);
    if (plen > 1) {
      // log
      // attempt to get property of non-attribute object
    }
    return item;
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

  int GetSize(ArrayList<Index> path) {
    int plen = path.length();
    if (plen == 0) {
      // log
      // should have gotten this one level up, entities don't have direct insight into sibling data
      return 0;
    }
    if (plen == 1)
      return value.get(path[0].prop).length();
    Object item = Get(path.sublist(0, plen - 1));
    if (item instanceOf Attribute)
      return item.GetSize(path.sublist(plen - 1, plen);
    // log
    // can't get prop size if this isn't an attribute
    return 0;
  }
}
