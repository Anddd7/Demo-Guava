package anddd7.guava.base;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import java.util.List;

/**
 * Created by edliao on 2017/5/18.
 */
public class CollectionExtSample {

  /**
   * @Forwarding 装饰器 ,是一系列装饰类 <p> 通过虚函数#delegate 指定返回的对象 ,其他操作都基于这个函数的返回值 ,做一个代理 还可以重写其他方法实现更细致化的设置
   * <p> 例如:List#add时记录日志
   */

  public class LoggingList<T> extends ForwardingList<T> {

    final List<T> target = Lists.newArrayList();

    @Override
    protected List<T> delegate() {
      return target;
    }

    @Override
    public boolean add(T element) {
      System.out.println("Logging - add :");
      return super.add(element);
    }
  }

  /**
   * @PeekingIterator 可以查看下一个元素而不移动iterator的指针位置
   */
  public void peekingIterator() {
    PeekingIterator<Integer> iterator = Iterators.peekingIterator(
        Lists.newArrayList(1, 2, 3, 3, 4, 5).iterator()
    );
    while (iterator.hasNext()) {
      Integer current = iterator.next();
      while (iterator.hasNext() && iterator.peek().equals(current)) {
        System.out.println(iterator.next());
      }
    }
  }


}
