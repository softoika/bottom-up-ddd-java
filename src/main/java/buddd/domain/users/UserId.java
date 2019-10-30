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

  public boolean equals(UserId other) {
    if (Objects.equals(other, null)) return false;
    if (Objects.equals(other, this)) return true;
    return value.equals(other.value);
  }
}
