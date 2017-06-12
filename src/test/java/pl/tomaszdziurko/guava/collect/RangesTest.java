package pl.tomaszdziurko.guava.collect;

import static org.fest.assertions.Assertions.assertThat;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.Set;
import org.testng.annotations.Test;

/**
 * Range and Range showcase
 */
public class RangesTest {

  @Test
  public void shouldCheckThatElementIsInRange() throws Exception {

    // given
    Range<Integer> range = Range.closed(2, 20);
    Range<Integer> rangeWithRightOpen = Range.closedOpen(2, 20);

    // then
    assertThat(range.contains(20)).isTrue();
    assertThat(rangeWithRightOpen.contains(20)).isFalse();
  }

  @Test
  public void shouldCheckThatRangeIsEnclosedInAnotherOne() throws Exception {

    // given
    Range<Long> range = Range.openClosed(10L, 20L);
    Range<Long> smallerRange = Range.closed(10L, 15L);

    // then
    assertThat(range.encloses(smallerRange)).isFalse();
  }

  @Test
  public void shouldCheckThatAllElementAreInRange() throws Exception {

    // given
    Range<Integer> range = Range.closed(2, 20);

    // then
    assertThat(range.containsAll(Lists.newArrayList(3, 4, 5, 6, 7, 8, 9, 10))).isTrue();
  }

  @Test
  public void shouldGenerateSetOfElementsInRange() throws Exception {

    // given
    Range<Integer> range = Range.open(2, 20);

    // when

    Set<Integer> integersInRange = ContiguousSet.create(range, DiscreteDomain.integers());

    // then
    assertThat(integersInRange).contains(3);
    assertThat(integersInRange).contains(19);
    assertThat(integersInRange).excludes(2, 20);
  }

  @Test
  public void shouldCreateRangeForGivenNumbers() throws Exception {

    // given
    ArrayList<Integer> numbers = Lists.newArrayList(4, 3, 10, 30, 20);

    // when
    Range<Integer> range = Range.encloseAll(numbers);

    // then
    assertThat(range.lowerEndpoint()).isEqualTo(3);
    assertThat(range.upperEndpoint()).isEqualTo(30);
  }
}
