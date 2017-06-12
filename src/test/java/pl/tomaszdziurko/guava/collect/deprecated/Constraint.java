package pl.tomaszdziurko.guava.collect.deprecated;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public interface Constraint<E> {

  E checkElement(E element);

  @Override
  String toString();
}
