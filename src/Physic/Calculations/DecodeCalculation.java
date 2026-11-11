package Physic;

class DecodeCalculation implements Calculation {
public:
  void Calculate(Attribute input,
                 Attribute output) {
    int count = input.Describe("codes");
    if (count < 0) {
      // log
      // codes not found for decoding
      return;
    }
    for (int ix = 0; ix < count; ix++) {
      Attribute code = input.Get("codes", ix);
      int id_cnt = code.Describe("id");
      if (id_cnt < 1) {
        // log
        // code missing id
        continue;
      }
      if (id_cnt > 1) {
        // log
        // malformed - multiple IDs
      }
      Object id = code.Get("id", 0);
      if (!(id instanceOf String)) {
        // log
        // malformed - ID must be string
        continue;
      }
      ArrayList<Index> path = Index.Str2Path(id);
      int val_cnt = code.Describe("value");
      if (val_cnt < 1) {
        // log
        // code missing value
        continue;
      }
      if (val_cnt > 1) {
        // log
        // malformed - multiple values
      }
      Object value = code.Get("value", 0);
      output.Set(path, value);
    }
  }
}
