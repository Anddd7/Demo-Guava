package pl.tomaszdziurko.guava.base;

import static com.google.common.base.CharMatcher.inRange;
import static org.fest.assertions.Assertions.assertThat;

import com.google.common.base.CharMatcher;
import org.testng.annotations.Test;

/**
 * Class to learn some features of CharMatcher
 */
public class CharMatcherTest {

  @Test
  public void shouldMatchChar() {
    assertThat(CharMatcher.anyOf("gZ").matchesAnyOf("anything")).isTrue();
  }

  @Test
  public void shouldNotMatchChar() {
    assertThat(CharMatcher.noneOf("xZ").matchesAnyOf("anything")).isTrue();
  }

  @Test
  public void shouldMatchAny() {
    assertThat(CharMatcher.any().matchesAllOf("anything")).isTrue();
  }

  @Test
  public void shouldMatchBreakingWhitespace() {
    assertThat(CharMatcher.breakingWhitespace().matchesAllOf("\r\n\r\n")).isTrue();
  }

  @Test
  public void shouldMatchDigits() {
    assertThat(inRange('0', '9').matchesAllOf("1231212")).isTrue();
  }

  @Test
  public void shouldMatchDigitsWithWhitespace() {
    assertThat(inRange('0', '9').matchesAnyOf("1231 aa212")).isTrue();
  }

  @Test
  public void shouldRetainOnlyDigits() {
    assertThat(inRange('0', '9').retainFrom("Hello 1234 567")).isEqualTo("1234567");
  }

  @Test
  public void shouldRetainDigitsOrWhiteSpaces() {
    assertThat(inRange('0', '9').or(CharMatcher.whitespace()).retainFrom("Hello 1234 567"))
        .isEqualTo(" 1234 567");
  }

  @Test
  public void shouldRetainNothingAsConstrainsAreExcluding() {
    assertThat(inRange('0', '9').and(inRange('a', 'z').or(inRange('A', 'Z'))).retainFrom("Hello 1234 567"))
        .isEqualTo("");
  }

  @Test
  public void shouldRetainLettersAndDigits() {
    assertThat(inRange('0', '9').or(inRange('a', 'z').or(inRange('A', 'Z'))).retainFrom("Hello 1234 567"))
        .isEqualTo("Hello1234567");
  }

  @Test
  public void shouldCollapseAllDigitsByX() {
    assertThat(inRange('0', '9').collapseFrom("Hello 1234 567", 'x')).isEqualTo("Hello x x");
  }

  @Test
  public void shouldReplaceAllDigitsByX() {
    assertThat(inRange('0', '9').replaceFrom("Hello 1234 567", 'x')).isEqualTo("Hello xxxx xxx");
  }

  @Test
  public void shouldCountDigits() {
    assertThat(inRange('0', '9').countIn("Hello 1234 567")).isEqualTo(7);
  }

  @Test
  public void shouldReturnFirstIndexOfFirstWhitespace() {
    assertThat(CharMatcher.whitespace().indexIn("Hello 1234 567")).isEqualTo(5);
  }

  @Test
  public void shouldReturnLastIndexOfFirstWhitespace() {
    assertThat(CharMatcher.whitespace().lastIndexIn("Hello 1234 567")).isEqualTo(10);
  }

  @Test
  public void shouldRemoveDigitsBetween3and6() {
    assertThat(inRange('3', '6').removeFrom("Hello 1234 567")).isEqualTo("Hello 12 7");
  }

  @Test
  public void shouldRemoveAllExceptDigitsBetween3and6() {
    assertThat(inRange('3', '6').negate().removeFrom("Hello 1234 567"))
        .isEqualTo("3456");
  }

  @Test
  public void shouldRemoveStartingAndEndingDollarsAndKeepOtherUnchanged() {
    assertThat(CharMatcher.is('$').trimFrom("$$$ This is a $ sign $$$"))
        .isEqualTo(" This is a $ sign ");
  }

  @Test
  public void shouldRemoveOnlyStartingDollarsAndKeepOtherUnchanged() {
    assertThat(CharMatcher.is('$').trimLeadingFrom("$$$ This is a $ sign $$$"))
        .isEqualTo(" This is a $ sign $$$");
  }

  @Test
  public void shouldRemoveStartingEndEndingDollarsAndReplaceOthersWithX() {
    assertThat(CharMatcher.is('$').trimAndCollapseFrom("$$$This is a $ sign$$$", 'X'))
        .isEqualTo("This is a X sign");
  }

  @Test
  public void shouldRemoveOnlyEndingDollarsAndKeepOtherUnchanged() {
    assertThat(CharMatcher.is('$').trimTrailingFrom("$$$ This is a $ sign $$$"))
        .isEqualTo("$$$ This is a $ sign ");
  }

  @Test
  public void shouldRemoveStartingAndEndingDollarsOrWhitespaceAndKeepOtherUnchanged() {
    assertThat(CharMatcher.is('$').or(CharMatcher.is(' ')).trimFrom("$$$ This is a $ sign $$$"))
        .isEqualTo("This is a $ sign");
  }

}
