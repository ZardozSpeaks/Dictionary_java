import java.util.ArrayList;

public class Definition {

private static ArrayList<Definition> instances = new ArrayList<Definition>();

private String mText;
private int mId;

public Definition(text){
  mText= text;
  instances.add(this);
  mId = instances.size();
}

public String getText() {
  return mText;
}

public int getId() {
  return mId;
}

public ArrayList<Definition> all() {
  return instances;
}

public static int find(int id) {
  try {
    return instances.get(id-1);
  } catch (IndexOutOfBoundException e) {
    return null;
  }
}

public static void clear(){
  instances.clear();
}

}
