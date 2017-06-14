package anddd7.guava.sample;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

/**
 * Created by edliao on 2017/5/18.
 */
public class CacheSample {

  /**
   * build a cache
   */
  public LoadingCache tempCache = CacheBuilder.newBuilder()
      //.maximumSize(1000) //缓存数
      .expireAfterAccess(10, TimeUnit.MINUTES)//时间内没有被读写
      .maximumWeight(1000) //权重 ,根据权重计算规则计算总重 ,决定是否清除
      .weigher((Weigher<Integer, String>) (key, value) -> key) //权重计算规则
      .removalListener(// 缓存移除的监听
          notification ->
              System.out.println("[" + notification.getCause() + "] 目标被移除:"
                  + notification.getKey() + " - " + notification.getValue())
      )
      .build(
          new CacheLoader<Integer, String>() {
            @Override
            public String load(Integer key) throws Exception {
              System.out.println("Loading : " + key);
              return "Key is " + key;
            }
          });

  /**
   * sample get
   */
  @Test
  public void useCache() {
    try {
      System.out.println(tempCache.get(1));
      System.out.println(tempCache.getAll(Lists.newArrayList(1, 2, 3)));
      System.out.println(tempCache.get(4, () -> "Special Four"));

      //put 是原子性的
      tempCache.put(2, "TwoTwoTwo");//=tempCache.asMap().put()

      //逼近权重
      System.out.println(tempCache.get(570));
      System.out.println(tempCache.get(570));
      System.out.println(tempCache.get(2));

      //清除缓存
      tempCache.invalidate(2);

      //刷新 异步的
      tempCache.refresh(4);//使用load重新加载 ,其他线程还可以读到旧值
      System.out.println(tempCache.get(4));


    } catch (Exception e) {
      System.out.println(e);
    }
  }


}
