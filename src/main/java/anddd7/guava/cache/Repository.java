package anddd7.guava.cache;

import java.util.UUID;

public interface Repository {

  Value get(Key key);

  class Key {

    private UUID uuid;

    public Key(UUID uuid) {
      this.uuid = uuid;
    }
  }

  class Value {

    private UUID uuid;

    public Value(UUID uuid) {
      this.uuid = uuid;
    }
  }
}
