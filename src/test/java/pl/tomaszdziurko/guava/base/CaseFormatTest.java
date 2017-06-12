package pl.tomaszdziurko.guava.base;

import static org.fest.assertions.Assertions.assertThat;

import com.google.common.base.CaseFormat;
import org.testng.annotations.Test;

/**
 * CaseFormat features : To convert String , like 'lower' -> 'UPPER' , '-hyphen' -> '_underscore'
 * ,'lowerCamel' -> 'lowernormal'
 */
public class CaseFormatTest {

  @Test
  public void shouldConvertToUpperUnderscore() throws Exception {

    // then
    assertThat(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "HelloWorld"))
        .isEqualTo("HELLO_WORLD");
  }

  @Test
  public void shouldConvertToLowerCamel() throws Exception {

    // then
    assertThat(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "HELLO_WORLD"))
        .isEqualTo("helloWorld");
  }

  @Test
  public void shouldConvertToLowerHyphen() throws Exception {

    // then
    assertThat(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "helloWorld"))
        .isEqualTo("hello-world");
  }
}
