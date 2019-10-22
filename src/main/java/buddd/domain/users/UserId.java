package buddd.domain.users;

import java.util.Objects;

/** エンティティの特徴である「同じ属性でも区別される」を実現するための、識別子となるオブジェクトを定義 */
public class UserId {
  private final String id;

  public UserId(String id) {
    // equals(UserId)のときにNPEになってしまうのでnullチェックを追加
    this.id = Objects.requireNonNull(id);
  }

  public String getValue() {
    return id;
  }

  public boolean equals(UserId other) {
    if (Objects.equals(other, null)) return false;
    if (Objects.equals(other, this)) return true;
    return id.equals(other.id);
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.equals(obj, null)) return false;
    if (Objects.equals(obj, this)) return true;
    if (!getClass().equals(obj.getClass())) return false;
    return equals((UserId) obj);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
