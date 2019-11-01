package buddd.domain.users;

import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

/** エンティティの特徴である「同じ属性でも区別される」を実現するための、識別子となるオブジェクトを定義 */
public class UserId {
  @Getter private final String value;

  public UserId(@NonNull String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserId userId = (UserId) o;
    return value.equals(userId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
