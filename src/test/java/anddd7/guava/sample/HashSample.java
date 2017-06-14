package anddd7.guava.sample;

import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import org.testng.annotations.Test;

/**
 * @author edliao on 2017/6/14.
 * @description 散列
 */
public class HashSample {

  @Test
  public void basicHash() {
    //String根据内容进行Hash ,相同内容hash一致
    String i1 = String.valueOf(10);
    String i2 = String.valueOf(20);
    String i3 = String.valueOf(10);
    System.out
        .println(i1.hashCode() + "|" + i2.hashCode() + "|" + i3.hashCode());

    //对象使用的内存hash ,不同的对象尽管内容一样 ,但hash不同
    Person p1 = new Person(1, "A");
    Person p2 = new Person(2, "B");
    Person p3 = new Person(1, "A");
    Person p4 = p2;
    System.out
        .println(p1.hashCode() + "|" + p2.hashCode() + "|" + p3.hashCode() + "|" + p4.hashCode());
  }


  @Test
  public void guavaHash() {
    Person p1 = new Person(1, "A");
    Person p2 = new Person(2, "B");
    Person p3 = new Person(1, "A");

    HashFunction hf = Hashing.sha256();
    HashCode hc1 = hf.newHasher()
        .putInt(p1.getId())
        .putString(p1.getName(), Charset.defaultCharset())
        .hash();

    Funnel<Person> personFunnel = (from, into) ->
        into.putInt(from.getId()).putString(from.getName(), Charset.defaultCharset());

    HashCode hc2 = getHashCode(hf, p2, personFunnel);
    HashCode hc3 = getHashCode(hf, p3, personFunnel);

    System.out.println(hc1.asLong() + "|" + hc2.asLong() + "|" + hc3.asLong());
    System.out.println(p1+" AND "+p3 +" IS Equals ? "+ hc1.equals(hc3));
  }

  public <T> HashCode getHashCode(HashFunction hf, T instance, Funnel<? super T> funnel) {
    return hf.newHasher()
        .putObject(instance, funnel)
        .hash();
  }
}
