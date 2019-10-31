package buddd.domain.users;

import lombok.Getter;

import java.util.Objects;

/* 値オブジェクトを作るモチベーション
 * - 存在しない値を存在させない
 * - 間違った代入を防ぐ
 */
public class UserName {
  @Getter private final String value;

  public UserName(String value) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("It cannot be null or empty");
    }
    if (value.length() > 50) {
      throw new IllegalArgumentException("It must be 50 characters or less");
    }
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserName userName = (UserName) o;
    return value.equals(userName.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
