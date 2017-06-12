package anddd7.guava.base;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
import org.testng.annotations.Test;

/**
 * 并发
 */
public class ConcurrenceSample {

  public static List<Callable<String>> getTask() {
    return Arrays.asList(() -> "Go",
        () -> "Back",
        () -> null,
        () -> String.valueOf(1 / 0));
  }

  @Test
  public void testListenableFuture() throws ExecutionException, InterruptedException {
    //建立Executors ,转换为ListeningExecutor
    ListeningExecutorService service = MoreExecutors
        .listeningDecorator(Executors.newFixedThreadPool(10));
    //提交任务并执行
    ListenableFuture explosion = service.submit(getTask().get(1));
    //添加回调
    Futures.addCallback(explosion, new FutureCallback() {
      @Override
      public void onSuccess(@Nullable Object result) {
        System.out.println(Thread.currentThread().getName() + " Success:" + result);
      }

      @Override
      public void onFailure(Throwable t) {
        System.out.println(Thread.currentThread().getName() + " Failure:" + t);
      }
    }, service);
    //主线程等待
    System.out.println(Thread.currentThread().getName() + " is waiting.");
    explosion.get();
    if (explosion.isDone()) {
      System.out.println(Thread.currentThread().getName() + " is finished.");
    }
  }

}
