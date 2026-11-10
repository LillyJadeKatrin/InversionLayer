package Physic;

class Index {
  String prop;
  int offset;

  Index(String p_prop, int p_offset) {
    prop = p_prop;
    offset = p_offset;
  }

  static ArrayList<Index> Str2Index(String propname,
                                    int offset) {
    return new ArrayList<Index>(new Index(propname,
                                          offset));
  }

  static String Index2Str(Index index) {
    return index.prop + '.' + index.offset;
  }

  static ArrayList<Index> Str2Path(String pathstr) {
    String[] tokens = pathstr.split(".");
    int tc = tokens.length();
    ArrayList<Index> output;
    for (int ix = 0; ix < tc; ix++) {
      int offset = 0;
      if (ix < tc - 1) {
        try {
          offset = Integer.parseInt(tokens[ix + 1]);
        } catch (Exception e) {
          // this is okay - just offset zero
        }
      }
      output.add(Str2Index(tokens[ix], offset));
      if (offset != 0)
        ix++;
    }
  }

  static String Path2Str(ArrayList<Index> path) {
    if (path.length() == 0)
      return "";
    String output = "";
    for (int ix = 0; ix < path.length(); ix++) {
      output += path[ix].prop
             += '.'
             += path[ix].offset
             += '.';
    }
    return output.substring(0, output.length() - 1);
  }
}
