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

  public boolean equals(UserName other) {
    if (Objects.equals(other, null)) return false;
    if (Objects.equals(other, this)) return true;
    return value.equals(other.value);
  }
}
