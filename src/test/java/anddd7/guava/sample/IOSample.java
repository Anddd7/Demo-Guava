package anddd7.guava.sample;


import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.testng.annotations.Test;

/**
 * @author edliao on 2017/6/12.
 * @description 简单实用的IO操作
 */
public class IOSample {

  static String ORIGINAL = "io/local/original.txt";
  static String COPY = "io/local/copy.txt";
  static String MOVE = "io/remote/move.txt";

  static String BIGFILE = "io/local/big.txt";
  static Charset charset = Charsets.UTF_8;

  @Test
  public void checkPath() {
    try {
      Files.createParentDirs(new File(ORIGINAL));
      Files.createParentDirs(new File(COPY));
      Files.createParentDirs(new File(MOVE));
      Files.createParentDirs(new File(BIGFILE));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void write() {
    String content = "Welcome to Guava.\nYou can do many things with it.\n"
        + "欢迎使用Guava\n他可以帮助你完成多种任务\n";
    try {
      Files.write(content.getBytes(charset), new File(ORIGINAL));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void append() {
    String content = "TRY IT!!!";
    try {
      Files.asCharSink(new File(ORIGINAL), charset, FileWriteMode.APPEND).write(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void operate() {
    try {
      Files.copy(new File(ORIGINAL), new File(COPY));
      Files.move(new File(COPY), new File(MOVE));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void read() {
    try {
      Files.readLines(new File(MOVE), charset).forEach(s -> System.out.println(s));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 大文件不能把行存储下来,只能逐行处理
   */
  @Test
  public void readBigFile() {
    try {
      Integer count = Files.asCharSource(new File(ORIGINAL), charset)
          .readLines(new CountLineProcessor());
      System.out.println("File rows is :" + count);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void compareFile() {
    try {
      System.out.println("File is same? " + Files.equal(new File(ORIGINAL), new File(MOVE)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  class CountLineProcessor implements LineProcessor<Integer> {

    Integer count = 0;

    @Override
    public boolean processLine(String line) throws IOException {
      count++;
      System.out.println(line);
      return !Strings.isNullOrEmpty(line);
    }

    @Override
    public Integer getResult() {
      return count;
    }
  }
}
