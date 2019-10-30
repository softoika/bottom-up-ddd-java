package buddd.domain.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullNameTest {
  @Test
  @DisplayName("FullNameはnullで初期化できない")
  public void testNonNull() {
    assertThrows(NullPointerException.class, () -> new FullName(null, ""));
    assertThrows(NullPointerException.class, () -> new FullName("", null));
  }

  @Test
  public void testEquals() {
    {
      var a = new FullName("taro", "tanaka");
      var b = new FullName("taro", "tanaka");
      assertTrue(a.equals(b));
    }
    {
      var a = new FullName("taro", "tanaka");
      var b = new FullName("taro", "yamada");
      assertFalse(a.equals(b));
      assertFalse(a.equals(null));
      var c = new FullName("jiro", "tanaka");
      assertFalse(a.equals(c));
    }
    {
      var a = new FullName("taro", "tanaka");
      var b = new UserName("ttaro");
      assertFalse(a.equals(b));
    }
  }
}
