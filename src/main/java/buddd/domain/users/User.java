package buddd.domain.users;

import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;
import java.util.UUID;

/* エンティティの特徴
 * - 可変
 * - 同じ属性でも区別される
 * - 同一性を持つ
 *
 * Userオブジェクトは識別子(UserId)を持つ
 */
public class User {
  @Getter private final UserId id;

  @Getter private UserName userName;

  @Getter private FullName name;

  /** データベース等から読み取ったときに利用するコンストラクタ */
  public User(@NonNull UserId id, @NonNull UserName userName, @NonNull FullName name) {
    this.id = id;
    this.userName = userName;
    this.name = name;
  }

  public void changeUserName(@NonNull UserName userName) {
    this.userName = userName;
  }

  public void changeName(@NonNull FullName newName) {
    this.name = newName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
