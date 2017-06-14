package anddd7.guava.sample;

/**
 * Created by edliao on 2017/5/18.
 */
public class Person {

  int id;
  String name;

  public Person(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return id + " : " + name;
  }

}


