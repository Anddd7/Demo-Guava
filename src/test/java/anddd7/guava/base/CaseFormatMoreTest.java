package anddd7.guava.base;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * More about CaseFormat : Use the Converter do batch work
 */
public class CaseFormatMoreTest {
    @Test
    public void useConverter() throws Exception {

        Converter simpleConverter1 = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

        Converter simpleConverter2 = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_UNDERSCORE);

        Converter multiConverter = CaseFormat.LOWER_HYPHEN.converterTo(CaseFormat.LOWER_CAMEL)
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
    public void useConvertAll() throws Exception {
        List params = Arrays.asList("helloWord", "niceToMeetYou", "howAreYou");
        Converter converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

        //when
        Iterator results = converter.convertAll(params).iterator();

        // then
        assertThat(results).containsOnly(Arrays.asList("HELLO_WORD", "NICE_TO_MEET_YOU", "HOW_ARE_YOU"));
    }
}
