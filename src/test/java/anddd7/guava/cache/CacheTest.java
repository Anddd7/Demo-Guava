package anddd7.guava.cache;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import anddd7.guava.cache.Repository.Key;
import anddd7.guava.cache.Repository.Value;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

public class CacheTest {

  private Repository repo = mock(Repository.class);

  @Test
  public void createCache() throws ExecutionException {
    LoadingCache<Key, Value> cache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(10, TimeUnit.MINUTES)
//        .removalListener(MY_LISTENER)
        .build(
            new CacheLoader<Key, Value>() {
              @Override
              public Value load(Key key) {
                return repo.get(key);
              }
            }
        );

    when(repo.get(any(Key.class))).thenReturn(new Value(UUID.randomUUID()));

    Key key1 = new Key(UUID.randomUUID());
    Key key2 = new Key(UUID.randomUUID());

    cache.get(key1);
    cache.get(key2);
    cache.get(key1);

    verify(repo,times(2)).get(any(Key.class));
  }
}
