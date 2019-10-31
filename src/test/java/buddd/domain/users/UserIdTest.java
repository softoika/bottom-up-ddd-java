package buddd.domain.users;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {
  private class UserMock {
    @Getter private UserId id;

    public UserMock(UserId id) {
      this.id = id;
    }
  }

  @Test
  void testHashCode() {
    var a = new UserId("user");
    var b = new UserId("user");
    assertEquals(a.hashCode(), b.hashCode());
  }

  @Test
  void testEqualsWithMap() {
    var id = new UserId("user");
    var user = new UserMock(id);
    Map<UserId, UserMock> map = new HashMap<>();
    map.put(id, user);
    assertEquals(user, map.get(id));
  }
}
