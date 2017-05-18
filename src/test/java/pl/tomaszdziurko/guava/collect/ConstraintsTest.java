package pl.tomaszdziurko.guava.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;
import pl.tomaszdziurko.guava.collect.deprecated.Constraints;

import java.util.List;

import static org.testng.Assert.fail;

/**
 * Constraints showcase
 * has @Deprecated ,we encourage you to check your preconditions explicitly instead of leaving that work to the collection implementation
 */
public class ConstraintsTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowExceptionOnNullAdd() throws Exception {

        // given
        List<Integer> numbers = Constraints.constrainedList(Lists.newArrayList(1, 2, 3),
                element -> Preconditions.checkNotNull(element));

        // when
        numbers.add(null);

        // then
        fail("Should throw a NullPointerException");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionOnInvalidAdd() throws Exception {

        // given
        List<Integer> userAgesList = Constraints.constrainedList(Lists.newArrayList(1, 2, 3), element -> {
            Preconditions.checkNotNull(element);
            Preconditions.checkArgument(element.intValue() > 0);
            return element;
        });

        // when
        userAgesList.add(-2);

        // then
        fail("Should throw a IllegalArgumentException");
    }
}
