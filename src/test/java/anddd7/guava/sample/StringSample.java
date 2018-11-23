package anddd7.guava.sample;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

/**
 * @author edliao on 2017/6/12.
 * @description 字符串处理
 */
public class StringSample {

  @Test
  public void testJoiner() {
    System.out.println(
        Joiner.on(";").skipNulls().join("Harry", null, "Ron", "Hermione"));
    System.out.println(
        Joiner.on(",").join(Arrays.asList(1, 5, 7)));
  }

  @Test
  public void testSplitter() {
    String str = " ,a,,b,";

    /**
     * split 拆出来会是长度为4的数组: 空格 a '' b
     */
    String[] arr1 = str.split(",");
    System.out.println("JDK split :" + arr1.length);
    Arrays.asList(arr1).forEach(s -> System.out.print(s));

    System.out.println();

    /**
     * Splitter 拆出来是长度为5的Iterator :空格 a '' b ''
     */
    Iterator<String> it = Splitter.on(",")//基于 字符 或者 正则表达式 拆分
        .trimResults()//消除前导和尾部的 空格 或者 字符
        .omitEmptyStrings()//消除空字符串
        .split(str).iterator();
    int count = 0;
    while (it.hasNext()) {
      count++;
      System.out.print(it.next());
    }
    System.out.println("\nSplitter :" + count);
  }

  @Test
  public void testCharMatcher() {
    String string = String.join("",
        Arrays.asList("  for ", "123", "wqe", "     ", "  sa  ").stream()
            .flatMap(s -> Arrays.asList(s,
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, s)).stream())
            .collect(Collectors.toList()));

    System.out.println(string);
    //移除control字符
    System.out.println(
        CharMatcher.javaIsoControl().removeFrom(string));
    //只保留数字字符
    System.out.println(
        CharMatcher.inRange('0', '9').retainFrom(string));
    //去除两端的空格，并把中间的连续空格替换成单个空格
    System.out.println(
        CharMatcher.whitespace().trimAndCollapseFrom(string, ' '));
    //用*号替换所有数字
    System.out.println(
        CharMatcher.inRange('0', '9').replaceFrom(string, "*"));
    // 只保留数字和小写字母
    System.out.println(
        CharMatcher.inRange('0', '9').or(CharMatcher.javaLowerCase())
            .retainFrom(string));
  }

  @Test
  public void testCharset() {
    Charset c = Charsets.ISO_8859_1;
  }

  @Test
  public void testCaseFormat() {
    System.out.println(
        CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "HYDM_SADF")
    );
  }
}
