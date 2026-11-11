package Physic;

class EncodeCalculation implements Calculation {
private:
  static HashMap<ArrayList<Index>, 
                 Object> TFMap(Attribute item) {
    HashMap<ArrayList<Index>, Object> output;
    HashMap<String, int> desc = item.Describe();
    for (String key : desc.keySet()) {
      int propsize = desc.get(key);
      for (int ix = 0; ix < propsize; ix++) {
        Index index = Str2Index(entry.getKey(), ix);
        Object value = item.Get(index);
        if (value instanceOf Attribute) {
          HashMap<ArrayList<Index>,
                  Object> propmap = TFMap(value);
          for (ArrayList<Index> inkey :
               propmap.keySet()) {
            Object invalue = propmap.get(inkey);
            inkey.add(index, 0);
            output.put(inkey, invalue);
          }
        } else { // value is not an attribute
          output.put(index, value);
        }
      }
    }
    return output;
  }
public:
  void Calculate(Attribute input,
                 Attribute output) {
    HashMap<ArrayList<Index>,
            Object> codemap = TFMap(input);
    int ix = 0;
    for (ArrayList<Index> path : 
         codemap.keySet()) {
      Attribute code;
      code.Set("id", 0, path);
      code.Set("value", 0, codemap.get(path));
      output.Set("codes", ix, code);
    }
  }
}
