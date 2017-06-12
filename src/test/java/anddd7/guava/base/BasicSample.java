package anddd7.guava.base;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import java.util.Objects;
import javax.annotation.Nullable;
import org.testng.annotations.Test;


public class BasicSample {

  /**
   * @Preconditions 若干前置条件判断 ,通过抛出异常进行控制 <p> 作为基础和其他功能组合使用
   */
  public void preconditions() {
    //判空
    Preconditions.checkNotNull(1);

    //判断
    Preconditions.checkArgument(1 == 1);
    Preconditions.checkState(1 == 1);

    //检查下标超界
    Preconditions.checkElementIndex(1, 1);
    Preconditions.checkPositionIndex(1, 1);
    Preconditions.checkPositionIndexes(0, 2, 3);
  }

  /**
   * @Objects 对象的扩展方法(部分已加入jdk1.7)
   */
  public void objects() {
    //原有obj.equals ,obj如果为空会报错
    print(
        Objects.equals(null, null));
    //序列化计算hash
    print(
        Objects.hash("field1", "text", 1));
    //类似一个appender ,组装String
    print(
        MoreObjects.toStringHelper(this).add("x", 1).toString());
  }

  /**
   * @ComparisonChain 比较链
   * @Ordering 排序器
   */
  public void ordering() {
    //比较对象(多字段比较)
    Person p1 = new Person(1, "张三");
    Person p2 = new Person(2, "李四");
    print(
        ComparisonChain.start()
            .compare(p1.getId(), p2.getId())
            .compare(p1.getName(), p2.getName())
            .result()
    );

    //实现排序器
    Ordering<String> byLength = new Ordering<String>() {
      @Override
      public int compare(@Nullable String left, @Nullable String right) {
        return Ints.compare(left.length(), right.length());
      }
    };

    //链式实现
    Ordering<Person> ordering =
        Ordering.natural().nullsFirst().onResultOf(p -> p.getName());

  }

  /**
   * @Throwables 异常的检查 ,适用性有待实验
   */
  @Test
  public void throwables() throws Exception {
    try {
      int a = 1 / 0;
    } catch (Throwable t) {
      //使用Throwable可以拦截所有Exception和Error ,再使用Throwables转换成Exception抛出
      Throwables.throwIfInstanceOf(t, ArithmeticException.class);
      throw t;
    }

  }

  public void print(Object o) {
    System.out.println(o);
  }
}
