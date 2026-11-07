package Physic;

class Index {
  String prop;
  int offset;

  Index(String p_prop, int p_offset) {
    prop = p_prop;
    offset = p_offset;
  }

  static ArrayList<Index> Str2Path(String propname, int offset) {
    return new ArrayList<Index>(new Index(propname, offset));
  }
}
