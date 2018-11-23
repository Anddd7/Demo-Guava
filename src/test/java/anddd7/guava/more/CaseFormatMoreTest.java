package anddd7.guava.more;

import static org.fest.assertions.Assertions.assertThat;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.Test;

/**
 * More about CaseFormat : Use the Converter do batch work
 */
public class CaseFormatMoreTest {

  @Test
  public void useConverter() throws Exception {

    Converter<String, String> simpleConverter1 = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

    Converter<String, String> simpleConverter2 = CaseFormat.LOWER_UNDERSCORE
        .converterTo(CaseFormat.UPPER_UNDERSCORE);

    Converter<String, String> multiConverter = CaseFormat.LOWER_HYPHEN.converterTo(CaseFormat.LOWER_CAMEL)
        .andThen(CaseFormat.LOWER_CAMEL.converterTo(
            CaseFormat.UPPER_UNDERSCORE));

    // then
    assertThat(simpleConverter1.convert("helloWorld")).isEqualTo("HelloWorld");
    assertThat(simpleConverter2.convert("hello_world")).isEqualTo("HELLO_WORLD");
    assertThat(multiConverter.convert("hello-world")).isEqualTo("HELLO_WORLD");

    // then reverse the Converter
    assertThat(simpleConverter1.reverse()
        .convert("HelloWorld")).isEqualTo("helloWorld");
    assertThat(simpleConverter2.reverse()
        .convert("HELLO_WORLD")).isEqualTo("hello_world");
    assertThat(multiConverter.reverse()
        .convert("HELLO_WORLD")).isEqualTo("hello-world");
  }

  @Test
  public void useConvertAll() {
    List<String> params = Arrays.asList("helloWord", "niceToMeetYou", "howAreYou");
    Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

    //when
    Iterator<String> results = converter.convertAll(params).iterator();

    // then
    assertThat(results)
        .containsOnly(Arrays.asList("HELLO_WORD", "NICE_TO_MEET_YOU", "HOW_ARE_YOU"));
  }
}
