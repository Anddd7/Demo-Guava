package anddd7.guava.other;

import com.google.common.base.Charsets;
import com.google.common.primitives.Chars;
import java.nio.charset.Charset;
import org.testng.annotations.Test;

/**
 * @author edliao on 2017/6/13.
 * @description 对字符和字节进行一些测试和区分
 */
public class ByteTest {

  @Test
  public void testByte() {
    printString("c");//99
    printString("C");//67
    printString("abc");//97|98|99|
    printString("你");//ASCII和ISO无法识别中文
    printString("你们");
    printString("我me");

    System.out.println("------------------------------------");
    print("Char to byte:", 'z');//普通char一个byte
    print("Char to byte:", '№');//特殊字符和汉字占2个byte
    print("Char to byte:", '你');
  }

  public void printString(String target) {
    System.out.println("------------------" + target + "------------------");
    printStringByByte(target);
    printStringByChar(target);
  }

  public void printStringByByte(String target) {
    print(Charset.defaultCharset().displayName() + " charset - ", target.getBytes());//default utf-8
    print("ASCII charset - ", target.getBytes(Charsets.US_ASCII));
    print("ISO charset - ", target.getBytes(Charsets.ISO_8859_1));
  }

  public void printStringByChar(String target) {
    print("String to Char to Byte:", target.toCharArray());
  }

  public void print(String desc, byte... bytes) {
    System.out.print(desc + " is :");
    for (byte b : bytes) {
      System.out.print(b + "|");
    }
    System.out.println();
  }

  public void print(String desc, char... chars) {
    System.out.print(desc + " is :");
    for (char c : chars) {
      System.out.print(c + join(Chars.toByteArray(c)));
      System.out.print("|");
    }
    System.out.println();
  }

  public String join(byte... bytes) {
    StringBuilder sb = new StringBuilder("(Char to Bytes:");
    for (int i = 0; i < bytes.length; i++) {
      if (i != 0) {
        sb.append(",");
      }
      sb.append(bytes[i]);
    }
    sb.append(")");
    return sb.toString();
  }
}
